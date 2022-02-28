# Mulcam Project - 수학카페 리메이크
- Notion : https://www.notion.so/826f0a55277741b3b5facd00faad6107
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
### 
### 구현 기능
- 회원
  - 회원가입
    - 필수정보 미기입시 경고창 띄우기
    - 존재하는 학번으로 시도시 회원가입 실패
  - 로그인
    - 필수정보 미기입시 경고창 띄우기
    - 가입되지 않은 학번으로 시도시 로그인 실패 & 알림창
    - 틀린 비밀번호로 시도시 로그인 실패 & 알림창
    - 로그인 성공시 환영인사 후 과목 선택창으로 이동
    - 로그인 정보는 세션에 저장되며 세션 유효시간은 2시간
   
- 과목별 게시판 보기
  - 챕터별 질문 보기
  - 질문 작성, 보기, 수정, 삭제(CRUD)
  - 조회수(단순 조회수 & 중복을 허용하지 않는 조회수)
  - (관리자) 질문 답변 기능
