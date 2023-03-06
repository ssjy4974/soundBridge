# Git

## Git Flow~

```
💡 우리가 사용하는 Branch

master : 제품으로 출시될 수 있는 브랜치 ⇒ 마지막에 merge 할듯

develop : 다음 출시 버전을 개발하는 브랜치 ⇒ merge할때마다 자동배포

feature : 기능을 개발하는 브랜치 → EPIC과 연동
```

</aside>

### Hotfix 수정사항을 작업중인 branch에 적용시킬때

```
💡 feature에서 기능 개발 중일때 

**hotfix를 통해 급하게 중요한 부분이 develop에 변경되었을때**

현재 feat/be/sng에서 작업중이라고 가정
1. local develop branch의 변경사항을 최신화 시킨다
 ⇒ git switch develop
   git pull

2. sng 브랜치를 develop 브랜치와 merge 시켜 최신화 시키고 작업한다.
  ⇒ git switch feature/be/sng
    git merge --no-ff develop
```

</aside>

### 기능개발 후에 develop에 merge 시킬때 (local에서 rebase방법)

```
💡 feature에서 기능 개발 후 develop에 머지 시킬때
   *현재 feat/be/sng에서 작업완료라고 가정
   **(만약 develop에 아직 merge된게 없을 경우 3번부터 시작하면 된다!!)

  1. local 에서 remote의 변경사항을 인지 시킨다
   ⇒ git fetch

  2. sng 브랜치를 develop 브랜치와 rebase 시킨다.
   ⇒ git rebase origin/develop

  3. rebase 중 충돌이 발생한다면 3번 과정을 반복하며 충돌을 해결한다.
   ⇒ git status 를 통해 충돌이 난 file을 찾아서 충돌을 해결한다.
     git add 를 통해 충돌 해결한 부분을 add 한다.
     git rebase —continue를 통해 rebase를 다시 진행시킨다.


  3.5 기능테스트를 해본다.

  4. rebase된 sng 브랜치를 push 한다
    ⇒ git push —force

  5. remote에서 merge request를 보내 팀의 리더가 확인한 후 merge 시킨다

  6. remote develop이 merge 됬으면 내가 생각했을때 가장 편한 방법은
     local develop branch를 지우고 최신 변경사항을 인지시킨후 
     다시 switch로 가져오는것
    ⇒ git branch -D develop
      git fetch
      git switch develop
```

</aside>

### 기능개발 후에 develop에 merge 시킬때 (remote 에서 rebase)

```
💡 feature에서 기능 개발 후 develop에 머지 시킬때
   *현재 feat/be/sng에서 작업완료라고 가정

  1. git status를 통해 commit할 내역을 확인한 후에 add,commit,push
   ⇒ git status
     git add .
     git commit -m “feat : ~" 
     git push origin feat/be/sng

  2. remote에서 merge request를 보내면 rebase가 필요할때 rebase 버튼이 생성된다.
   ⇒ gitLab에 따로 설정하겠습니다. 충돌이 있다면 remote에서 해결

  3. remote develop이 merge 됬으면 내가 생각했을때 가장 편한 방법은
     local develop branch를 지우고 최신 변경사항을 인지시킨후 
     다시 switch로 가져오는것
   ⇒ git branch -D develop
     git fetch
     git switch develop
```

</aside>

- 참고블로그
  
  [충돌 없는 Git을 위해 Git Flow에 대해 알아보자](https://velog.io/@couchcoding/충돌-없는-Git을-위해-Git-Flow에-대해-알아보자)
  
  [우린 Git-flow를 사용하고 있어요 | 우아한형제들 기술블로그](https://techblog.woowahan.com/2553/)

## 

## Branch Convention

```
💡 ex) feat/be/login
       feat/fe/login
```

## Commit Convention

| 커밋 타입    | 리스트                                     |
| -------- | --------------------------------------- |
| feat     | 기능 (새로운 기능)                             |
| fix      | 버그 (버그 수정)                              |
| design   | CSS 등 사용자 UI 디자인 변경                     |
| refactor | 리팩토링                                    |
| style    | 스타일 (코드 형식, 세미콜론 추가 : 비즈니스 로직에 변경 없음)   |
| docs     | 문서 (문서 추가, 수정, 삭제)                      |
| test     | 테스트 (테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경 없음) |
| chore    | 기타 변경사항 (빌드 스크립트 수정 등)                  |
| post     | 블로그 포스트 추가 (신규 포스트 작성 및 수정)             |
| rename   | 파일 혹은 폴더명을 수정하거나 옮기는 작업만 하는 경우          |
| remove   | 파일을 삭제하는 작업만 수행한 경우                     |

```
💡 ex) feat : ~~기능 구현
```

</aside>

## Git Convention

```
💡 참고사항

1. 에픽이 완료되어 merge할 경우 브랜치를 삭제시키는 것이 가장 좋다.

2. 혹시 충돌이 발생한다면 혼자 해결하려 하지 말고 
   최소한 충돌난 부분을 작성한 사람과 함께 해결한다.

3. branch는 remote에서 판다. develop에서 뻗어나올 수 있도록 주의한다.

4. merge는 remote에서 한다.

5. merge request 보낼때 master에 보내지 않도록 주의한다.
```

</aside>

### Merge 정책

💡 ****[Merge commit with semi-linear history](https://iseunghan.tistory.com/330#2.%20Merge%20commit%20with%20semi-linear%20history-1)****

## ✔️ **Jira 사용법**

### 이슈생성

- 월요일에 주 단위 스프린트 이슈들을 생성
- 일주일에 40시간 이상 이슈 생성 및 처리
- 에픽은 기능이 이어진다면 새로 생성하지 않고 스토리를 작성하여 사용

### 구조

- **에픽 = 기능별 Branch → 자신의 Branch와 연동 필요(연동방법은?, FE은 페이지별로 나눌지, 기능별로 나눌지 개발하면서 최종 결정)**
  - **스토리 :** 각 기능의 세부 작업 단위 → 스토리 완료시 최종 commit 반영

### **명명 규칙**

- 에픽
  - 대분류: 기획, 개발, 배포, 발표
    - 개발은 -BE, -FE 병기
    - ex) [개발-BE] USER API
- 스토리
  - 기능 : 하위기능 - (세부기능)
  - ex)  `로그인: 토큰 - (토큰발급 로직 구현)`