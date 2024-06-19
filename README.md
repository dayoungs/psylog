## PSYLog
#### 일기장 속 친구 : 감정 이해와 조언의 동반자
![initial](https://github.com/dayoungs/psylog/assets/113420912/42984dae-87a5-4cee-8e58-491ee28dfc10)
  <br>

### 프로젝트 소개
![initial](https://github.com/dayoungs/psylog/assets/113420912/626c1b76-c8cb-440f-8d64-8c0cef402ac0)
**PSYLog**는 자신의 감정을 숨기려고 하는 현대인들을 타겟으로 두어 타인에게 쉽게 털어놓을 수 없는 이야기를 아무도 듣거나 보지 않는 곳에 털어놓고, 이에 대해 적합한 공감 및 조언을 제공해주는 서비스입니다.
### 코드 소개
```
  📂java                               ▶︎ 백엔드 서버 실행에 필요한 source code의 최상위 폴더
  ├─ 📂capston
  │  ├─📂psylog
  │  │  ├─ 📂 member                   ▶︎ Psylog 서비스 회원 관련 source code의 상위 폴더
  │  │  │  ├─ 📂 configuration         ▶︎ 회원 보안 관련 사항 (비밀번호 암호화 등)
  │  │  │  ├─ 📂 controller            ▶︎ 회원 관련 웹 서비스 페이지 매핑
  │  │  │  ├─ 📂 dto                   ▶︎ 회원 객체에 직접 접근하는 것을 막기 위한 데이터 전송 객체
  │  │  │  ├─ 📂 entity                ▶︎ 회원 객체에 필요한 속성들 정의
  │  │  │  ├─ 📂 repository            ▶︎ jpa 사용을 위한 데이터베이스 저장소
  │  │  │  └─ 📂service                ▶︎ 로그인, 회원가입, 회원 객체 반환 등 서비스 제공에 필요한 모듈을 작성한 source code 폴더
  │  │  ├─ 📂 post                     ▶︎ Psylog 서비스 일기 게시글 관련 source code의 상위 폴더
  │  │  │  ├─ 📂 controller            ▶︎ 일기 게시글 관련 웹 서비스 페이지 매핑
  │  │  │  ├─ 📂 dto                   ▶︎ 일기 객체에 직접 접근하는 것을 막기 위한 데이터 전송 객체
  │  │  │  ├─ 📂 entity                ▶︎ 일기 객체에 필요한 속성들 정의
  │  │  │  ├─ 📂 repository            ▶︎ jpa 사용을 위한 데이터베이스 저장소
  │  │  │  └─ 📂service                ▶︎ 일기 작성, 수정, 삭제 등 서비스 제공에 필요한 모듈을 작성한 source code 폴더
  │  │  ├─ 🔵HomeController            ▶︎ 웹 서비스 시작 페이지 매핑<br>
  │  │  └─ 🔵PsylogApplication         ▶︎ 서버 실행 위한 source code<br>
  📂resources
  ├─ 📂 static                         ▶︎ 웹 페이지 css, js 파일
  ├─ 📂 templates                      ▶︎ 프론트엔드 html 파일
  ├─🌿application.properties           ▶︎ AI api 사용 위한 엔드포인트, 키 정보 저장
  └─🌿application.yml                  ▶︎spring에서 데이터베이스 사용 위한 정보 저장
```

### 실행 방법
  ▶ 1. Git clone을 이용하는 방법
        - 현재 레파지토리를 클론하여 로컬에서 동작시킬 수 있습니다.
        <br>
            ```git clone https://github.com/dayoungs/psylog.git```
        
        <br>
        - 이때, 보안 문제로, OpenAI API의 key와, Clova API key와 secret 부분이 들어가 있지 않습니다. 아래의 txt를 다운받아, clone한 환경에서 application.properties 파일을 수정해주세요.
        [application.properties수정 부분.txt](https://github.com/user-attachments/files/15905988/application.properties.txt)
        <br>

        - 로컬에서 실행하는거다 보니, Mysql이 이미 설치 되어야 합니다. 설치후, root에다가 psylog db 저장을 위해, application.yml 부분의 password를 본인의 sql password로 설정해주세요.

  ▶ 2. Web site에 접속하는 방법

### 팀원 소개
|**개발(FE),팀장**|**개발(BE)**|**개발(BE)**|
|:---:|:---:|:---:|
|[@angelaoh0619](https://github.com/angelaoh0619)|[@dayoungs](https://github.com/dayoungs)|[@zoo0YOON](https://github.com/zoo0YOON)|
|오윤재|신다영|윤주영|



- How to build
- How to install
- How to test


[sdfdfsfdsfsddfs.txt](https://github.com/user-attachments/files/15880057/sdfdfsfdsfsddfs.txt)
