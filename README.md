## PSYLog
##### 일기장 속 친구 : 감정 이해와 조언의 동반자
![initial](https://github.com/dayoungs/psylog/assets/113420912/42984dae-87a5-4cee-8e58-491ee28dfc10)
  <br>

- Source code에 대한 설명<br>
  📂java                               ▶︎ 백엔드 서버 실행에 필요한 source code의 최상위 폴더<br>
  ├─ 📂capston<br>
  │  ├─📂psylog<br>
  │  │  ├─ 📂 member                   ▶︎ Psylog 서비스 회원 관련 source code의 상위 폴더<br>
  │  │  │  ├─ 📂 configuration         ▶︎ 회원 보안 관련 사항 (비밀번호 암호화 등)<br>
  │  │  │  ├─ 📂 controller            ▶︎ 회원 관련 웹 서비스 페이지 매핑<br>
  │  │  │  ├─ 📂 dto                   ▶︎ 회원 객체에 직접 접근하는 것을 막기 위한 데이터 전송 객체<br>
  │  │  │  ├─ 📂 entity                ▶︎ 회원 객체에 필요한 속성들 정의<br>
  │  │  │  ├─ 📂 repository            ▶︎ jpa 사용을 위한 데이터베이스 저장소<br>
  │  │  │  └─ 📂service                ▶︎ 로그인, 회원가입, 회원 객체 반환 등 서비스 제공에 필요한 모듈을 작성한 source code 폴더<br>
  │  │  ├─ 📂 post                     ▶︎ Psylog 서비스 일기 게시글 관련 source code의 상위 폴더<br>
  │  │  │  ├─ 📂 controller            ▶︎ 일기 게시글 관련 웹 서비스 페이지 매핑<br>
  │  │  │  ├─ 📂 dto                   ▶︎ 일기 객체에 직접 접근하는 것을 막기 위한 데이터 전송 객체<br>
  │  │  │  ├─ 📂 entity                ▶︎ 일기 객체에 필요한 속성들 정의<br>
  │  │  │  ├─ 📂 repository            ▶︎ jpa 사용을 위한 데이터베이스 저장소<br>
  │  │  │  └─ 📂service                ▶︎ 일기 작성, 수정, 삭제 등 서비스 제공에 필요한 모듈을 작성한 source code 폴더<br>
  │  │  ├─ 🔵HomeController            ▶︎ 웹 서비스 시작 페이지 매핑<br>
  │  │  └─ 🔵PsylogApplication         ▶︎ 서버 실행 위한 source code<br>
  📂resources<br>
  ├─ 📂 static                         ▶︎ 웹 페이지 css, js 파일<br>
  ├─ 📂 templates                      ▶︎ 프론트엔드 html 파일<br>
  ├─🌿application.properties           ▶︎ AI api 사용 위한 엔드포인트, 키 정보 저장<br>
  └─🌿application.yml                  ▶︎spring에서 데이터베이스 사용 위한 정보 저장<br>
  <br>

- How to build
- How to install
- How to test


[sdfdfsfdsfsddfs.txt](https://github.com/user-attachments/files/15880057/sdfdfsfdsfsddfs.txt)
