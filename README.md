# Mulcam Project - 수학카페 리메이크
- Notion : https://treenamu.notion.site/826f0a55277741b3b5facd00faad6107
- Web Link : http://treenamu.co.kr/start.do
<hr/>

### 기간
- 2022/01/24 ~ 2022/02/13 ( 3주 )

### 내용
- 수학카페는 학부생이 교양수학 질문을 하면 조교와 근로학생이 답을 해주는 곳이다.
- 수학카페 조교의 불편함을 덜어주기 위해 홈페이지 리메이크를 시도해본다.
### 참여인원 : 2명
- 김경준(본인) : 프로젝트 매니저(팀장), DB 설계, Back-End 개발
- 오상민 : DB 설계, Front-End 개발
### 진행 방식
- Agile Process
- Notion 을 사용한 작업상황 공유
### 사용 언어
- Back-End : JAVA
- Front-End : JSP, HTML/CSS/JS
- DB : MariaDB SQL
### 기술 스택
- Back-End : MVC 기반 자체 제작 Framework, JAVA HttpServlet
![Architecture](https://user-images.githubusercontent.com/42484169/156017038-6f20aca7-fd03-4470-a451-702f131dddd1.JPG)
- Front-End : JSTL, BootStrap
### 페이지 구성도
![page_flow](https://user-images.githubusercontent.com/42484169/156016859-91f02f86-ce25-4293-b8f9-e3e62b7c6115.JPG)
### DB 구성
![DB_Flow](https://user-images.githubusercontent.com/42484169/156017295-07ae7fea-b6e6-4473-a4b8-ddb4ea34e654.JPG)
<hr/>

![DB_table](https://user-images.githubusercontent.com/42484169/156017308-5db7a0d4-2a9a-40b0-ab91-4b302ddb0104.JPG)

### 구현 기능
- 회원
  - 회원가입
    - 필수정보 미기입시 경고창
    - 존재하는 학번으로 시도시 회원가입 실패
  - 로그인
    - 필수정보 미기입시 경고창
    - 가입되지 않은 학번으로 시도시 로그인 실패 & 알림창
    - 틀린 비밀번호로 시도시 로그인 실패 & 알림창
    - 로그인 성공시 환영인사 후 과목 선택창으로 이동
    - 로그인 정보는 세션에 저장되며 세션 유효시간은 2시간
  - 로그아웃
    - 로그아웃 버튼 누를 시 세션에 저장된 정보 삭제 & 로그인 창으로 이동
<br/>

- 게시글
  - 과목별 게시판 보기
  - 질문 작성, 보기, 수정, 삭제(CRUD)
    - 제목, 내용, 챕터, 첨부사진 을 등록 가능
    - 답변이 달린 게시글은 관리자 외에 수정/삭제 불가능(고객 요청사항)
    - 질문 작성, 수정 시 필수정보 미기입시 경고창
    - 관리자는 게시글에 답변도 가능 : 답변내용, 첨부사진 등록 & 수정 가능
  - 챕터별 질문 보기
  - 조회수
    - 중복을 허용하는 단순 조회수가 표시 됨
    - 관리자는 중복을 허용하지 않는 조회수인 '열람자수' 까지 확인 가능
  - '나의 질문' 보기 가능
    - 로그인 한 아이디로 작성했던 질문들을 과목별로 한 번에 조회 가능
    - 질문을 누를 시 해당 게시글로 이동
<br/>

- 관리자 페이지
  - 톱니바퀴 버튼을 눌러 관리자 페이지로 이동 가능
  - 회원 정보, 과목별로 게시글을 한 번에 조회 가능
    - '열람자수' 도 확인 가능
  - 'csv 파일 만들기' 버튼 클릭 시 csv 파일 추출
    - 추출 정보 : 회원 데이터, 과목별 게시글 데이터, 과목별 질문 수, 회원별 질문 수
    - csv 파일 생성장소 : local(C:\\mathcafe_upload\\csv), host(/mathcafe_upload/csv)
    - server 에 만들어진 csv 파일을 불러오는 방법 추가 필요함
    - 만들어진 csv 파일 예시
    - ![tocsv_3](https://user-images.githubusercontent.com/42484169/156019157-7d8ab2fe-4952-4b31-99ab-a0482449a6bd.JPG)
