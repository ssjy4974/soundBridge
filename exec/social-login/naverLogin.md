# Naver Login 설정

[https://developers.naver.com/apps/#/register](https://developers.naver.com/apps/#/register) 접속

애플리케이션 이름 및 사용할 API 선택 -> 네이버 로그인

![카카오 로그인 1](../social-login/login_assets/naver-login1.png)

제공 정보 선택

![카카오 로그인 2](../social-login/login_assets/naver-login2.png)

1. PC 웹으로 환경 추가
2. 서비스 URL 추가
3. callback URL 추가. domain뒤에 /login/oauth2/code/naver 필수 입력

![카카오 로그인 3](../social-login/login_assets/naver-login3.png)

클라이언트 ID와 Secret 확인

![카카오 로그인 4](../social-login/login_assets/naver-login4.png)

application.yml 에 naver api 정보 입력

![카카오 로그인 5](../social-login/login_assets/naver-login5.png)

![카카오 로그인 6](../social-login/login_assets/naver-login6.png)
