# 🚢배포

### - AWS ec2, Docker, Jenkins

## 1. Nginx

Nginx를 설치한다

```
sudo apt-get install nginx
```

### 1-1. SSL 인증서 적용

Let's Encrypt로부터 무료 SSL 인증서를 발급받기 위해 letsencrypt를 설치한다

```
sudo apt-get install letsencrypt
```

도메인에 알맞게 인증서를 발급 받는다

```
sudo letsencrypt certonly --standalone -d [도메인]
ls /etc/letsencrypt/live/[도메인]
```

파일 생성 후 nginx 설정을 채워준다.

```
sudo vim /etc/nginx/sites-available/[적절한파일명].conf
```

도메인이 *j8a703.p.ssafy.io*인 경우 다음과 같다.

```nginx
server {

  location /{
    proxy_pass http://localhost:3000;
  }

  location /assets/ {
    proxy_pass http://localhost:3000/assets/;
  }

  location /api {
    location /api/member {
      if ($request_method = 'OPTIONS') {
        add_header 'Access-Control-Allow-Origin' '$http_origin';
        add_header 'Access-Control-Allow-Credentials' 'true';
        add_header 'Access-Control-Allow-Methods' 'GET, POST, PUT, DELETE, PATCH, OPTIONS';
        add_header 'Access-Control-Allow-Headers' 'Content-Type, Access-Token';
        return 204;
      }
      proxy_hide_header 'Access-Control-Allow-Origin';
      add_header 'Access-Control-Allow-Origin' '$http_origin' always;
      add_header 'Access-Control-Allow-Credentials' 'true' always;
      add_header 'Access-Control-Expose-Headers' 'Set-Cookie';

      proxy_pass http://localhost:9999/api/member;
    }

    if ($request_method = 'OPTIONS') {
    add_header 'Access-Control-Allow-Origin' '*';
    add_header 'Access-Control-Allow-Methods' 'GET, POST, PUT, DELETE, PATCH, OPTIONS';
    add_header 'Access-Control-Allow-Headers' 'Content-Type, Access-Token';
    add_header 'Access-Control-Max-Age' 86400;
    return 204;
    }

    # 1. hide the Access-Control-Allow-Origin from the serve response
    proxy_hide_header 'Access-Control-Allow-Origin';
    # 2. add a new custom header that allows all * origin instead
    add_header 'Access-Control-Allow-Origin' '*' always;

    proxy_pass http://localhost:9999/api;
  }

  location /jenkins {
    proxy_pass http://localhost:8000/jenkins;
  }

  location /swagger-ui/ {
    proxy_pass http://localhost:9999/swagger-ui/index.html;
  }

  listen 443 ssl; # managed by Certbot
  ssl_certificate /etc/letsencrypt/live/j8a703.p.ssafy.io/fullchain.pem; # managed by Certbot
  ssl_certificate_key /etc/letsencrypt/live/j8a703.p.ssafy.io/privkey.pem; # managed by Certbot
}

server {

  if ($host = j8a703.p.ssafy.io) {
    return 301 https://$host$request_uri;
  } # managed by Certbot

  listen 80;
  server_name j8a703.p.ssafy.io;
}
```

nginx 설정을 링크하고 테스트한 다음 다시 실행하면 SSL이 적용된다

```
sudo ln -s /etc/nginx/sites-available/[파일명].conf /etc/nginx/sites-enabled/[파일명].conf
sudo nginx -t
sudo nginx -s reload
```

## 2. MySQL

MySQL 서버를 설치

```
sudo apt install mysql-server
```

root 계정으로 접속한다

```
sudo mysql -u root
```

백엔드에서 사용할 계정을 만들어준다

```sql
use mysql;
CREATE USER 'ssafy'@'%' identified by mysql_native_password 'ssafy';
FLUSH PRIVILEGES;
```

데이터베이스 생성 후 권한을 허용해준다

```sql
create database soundbridge;
GRANT ALL PRIVILEGES ON dearmybaby.* to 'ssafy'@'%';
FLUSH PRIVILEGES;
```

MySQL 설정 파일을 연다

```
sudo vim /etc/mysql/mysql.conf.d/mysqld.cnf
```

외부에서의 접속도 허용하기 위해 bind-address 값을 0.0.0.0으로 바꿔준다

```
bind-address = 0.0.0.0
```

MySQL을 재실행한다

```
sudo service mysql restart
```

## 3. Docker

https://docs.docker.com/engine/install/ubuntu/
공식 문서에 나온대로 Docker 클라이언트와 데이몬을 설치해준다.

```
sudo apt-get remove docker docker-engine docker.io containerd runc
```

```
sudo apt-get update
sudo apt-get install \
  ca-certificates \
  curl \
  gnupg \
  lsb-release
```

```
sudo mkdir -m 0755 -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
```

```
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

```
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

## 4. Docker Image 만들기

소스코드를 클론 한다

```
git clone [gitLab 주]
```

빌드에 필요한 자바와 gradle, nodejs를 설치한다

```
java (11버전 기준)
sudo apt-get install openjdk-11-jdk

gradle
sudo apt install gradle

nodejs (16버전 기준)
curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash -
sudo apt-get install -y nodejs
```

프론트엔드 soundbridge-frontend:latest 이미지를 만들어준다.
빌드 과정에서 API_BASE_URL, KAKAO_API_KEY 환경 변수를 제공해줘야 한다.

```
cd ./frontend
npm install
(export API_BASE_URL=https://j8a703.p.ssafy.io; npm run build)
docker build -t soundbridge-frontend:latest .
cd ..
```

soundbridge-frontend:latest 이미지로 soundbridge-frontend 컨테이너를 만들어 실행한다.

```
sudo docker run -d -p 3000:8080 --name soundbridge-frontend soundbridge-frontend:latest
```

Gradle Wrapper 스크립트를 실행 가능한 파일로 설정

```
chmod +x ./gradlew
```

백엔드 soundbridge-backend:latest 이미지를 만들어준다

```
cd ./backend
./gradlew bootBuildImage --imageName=soundbridge-backend:latest
cd ..
```

soundbridge-backend:latest 이미지로 soundbridge-backend 컨테이너를 만들어 실행한다. 이때 환경 변수로 DATABASE_URL, DATABASE_ID, DATABASE_PASSWORD, SERVER_ENV, FILE_DIR를 주입해줘야 한다.

```
sudo docker run -d -p 8080:8080 --env DATABASE_URL=172.17.0.1 --env DATABASE_ID=ssafy --env DATABASE_PASSWORD=ssafy --env SERVER_ENV=production --env FILE_DIR=/workspace/files --name soundbridge-backend soundbridge-backend:latest
```

## 5. CI/CD 설정하기

젠킨스 설치

```
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/

# jenkins 재시작
sudo service jenkins restart

# 정상여부 확인
sudo systemctl status jenkins

```

Docker 이미지로 실행하기

```
# jenkins 실행
docker run \
  --name jenkins-docker \
  -p 8080:8080 -p 50000:50000 \
  -v /home/jenkins:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /usr/bin/docker:/usr/bin/docker \
  -u root \
  -d \
  jenkins/jenkins:lts

# jenkins 컨테이너 접속
docker exec -it [jenkins 컨테이너ID] bin/bash

# jenkins 컨테이너 log 확인
docker logs [jenkins 컨테이너ID]
```
