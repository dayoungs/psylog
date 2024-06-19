## PSYLog
#### 일기장 속 친구 : 감정 이해와 조언의 동반자
![initial](https://github.com/dayoungs/psylog/assets/113420912/42984dae-87a5-4cee-8e58-491ee28dfc10)
  <br> <br>

### 프로젝트 소개
![initial](https://github.com/dayoungs/psylog/assets/113420912/626c1b76-c8cb-440f-8d64-8c0cef402ac0)
**PSYLog**는 자신의 감정을 숨기려고 하는 현대인들을 타겟으로 두어 타인에게 쉽게 털어놓을 수 없는 이야기를 아무도 듣거나 보지 않는 곳에 털어놓고, 이에 대해 적합한 공감 및 조언을 제공해주는 서비스입니다.<br> <br>
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
<br> <br>

### 실행 방법
  #### ▶ Git clone을 이용하는 방법
  1. 현재 레파지토리를 클론하여 로컬에서 동작시킬 수 있습니다. <br> ```git clone https://github.com/dayoungs/psylog.git```
        <br>
        
2. 이때, 보안 문제로, OpenAI API의 key와, Clova API key와 secret 부분이 들어가 있지 않습니다. 아래의 txt를 다운받아, clone한 환경에서 application.properties 파일을 수정해주세요.
        <br>
[application.properties수정 부분.txt](https://github.com/user-attachments/files/15905988/application.properties.txt)
        <br>

3. 로컬에서 실행하는거다 보니, Mysql이 이미 설치 되어야 합니다. 설치후, root에다가 psylog db 저장을 위해, application.yml 부분의 password를 본인의 sql password로 설정해주세요.<br>

4. PsylogApplication.controller에 들어가서
	```
  		 @SpringBootApplication
			public class PsylogApplication {

				public static void main(String[] args) {
					SpringApplication.run(PsylogApplication.class, args);
				}

			}
	```
	부분을 실행해 주세요. <br>
5. http://localhost:8081/ 를 통해 웹 화면에 접근할 수 있습니다. 이때, 로컬환경에서 실행되는거다 보니, 회원가입부터 실행하셔야 합니다.
<br>

  #### ▶ Web site에 접속하는 방법
  아래의 PSYLog 홈페이지에 접속하여 로그인 해 주세요. 
  <br> http://43.203.77.125:8080/ <br>
  이때, 사용하실 수 있는 테스트 계정의 아이디와 비밀번호는 아래와 같습니다. <br>
  > ID : asdf <br>
  > Password : asdf
<br> <br>
### 프로젝트 사용 방법
1. 테스트 계정 혹은 본인의 계정을 만들어 해당 사이트에 들어갑니다.
2. 위에 존재하는 일기 작성 탭을 누르시면 일기를 작성하실 수 있습니다.
   ![initial](https://github.com/dayoungs/psylog/assets/113420912/bbb08377-7612-42e3-ae93-c439c3492872)
3. 일기를 작성하고 등록 버튼을 누르면, 해당 일기가 저장됨과 동시에 감정 분석과 일기에 대한 공감 및 조언을 제공합니다.
   ![initial](https://github.com/dayoungs/psylog/assets/113420912/5fa97df3-a4ed-4cf0-be21-06a3c53aca36)
4. 감정 분석 결과와 공감 및 조언 제공을 보고 싶으시다면, 작성한 일기를 클릭하여 아래로 스크롤을 내리면 됩니다. 이때 감정 분석 결과에 쓰이는 API는 Clova Sentiment API 이고, 공감 및 조언 제공에 쓰이는 API는 OpenAI의 GPT-3.5를 따로 Fine tuning한 API입니다.
   ![initial](https://github.com/dayoungs/psylog/assets/113420912/56c3e008-ced9-41b7-8e2f-aba2dac498b9)
   ![initial](https://github.com/dayoungs/psylog/assets/113420912/54185bae-7282-435a-9e6a-697674ecf2dd)

<br> <br>

### 팀원 소개
|**개발(FE),팀장**|**개발(BE)**|**개발(BE)**|
|:---:|:---:|:---:|
|[@angelaoh0619](https://github.com/angelaoh0619)|[@dayoungs](https://github.com/dayoungs)|[@zoo0YOON](https://github.com/zoo0YOON)|
|오윤재|신다영|윤주영|

<br> <br>

### Stacks
#### FrontEnd
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
<br>
#### BackEnd
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
<br>
#### API
![ChatGPT](https://img.shields.io/badge/chatGPT-74aa9c?style=for-the-badge&logo=openai&logoColor=white)
![Naver](https://a11ybadges.com/badge?logo=naver)
<br>
#### Server
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
