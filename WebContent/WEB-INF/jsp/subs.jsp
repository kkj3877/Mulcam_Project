<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script>
</script>
<style type="text/css">
<%-- 폰트 --%>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
}
.font-size {
	font-size: 30px;
}
.font-color {
	color: #FFFFFF;
}
.font-bold {
	font-weight: bold;
}
<%-- 반응형 웹 --%>
@media (max-width: 540px) {
	.item-title {
		margin-left:0px;
		padding-right: 100px !important;
	}
	.item-button {
		margin-right:0px;
		padding-left: 100px !important;
	}
}
<%-- 과목 버튼 css --%>
#sub {
	border:1px solid;
	height: 30px;
	width:150px;
	margin: auto;
	margin-bottom: 17px;
	text-decoration: none;
	border-radius: 1.0em;
	padding-top: 4.3px;
}
.text-color {
	color:black;
	text-decoration: none;
}
<%-- 토글바 css --%>
.container {
	position: relative;
	height: 70px;
	border-bottom: 1px solid black;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	padding-left: 0px;
	padding-right: 0px;
	width:100%;
}
.toggle-title {
	margin-left:0px;
	padding-right: 200px;
}
.toggle-button {
	margin-right:0px;
	padding-left: 200px;
}
.toggle-text-size {
	font-size: 18px;
	margin-top: 5px !important;
	margin-left: 7px;
}
.icon-size {
	height: 35px;
}
.icon-white {
	 filter:invert();
}
.toggle-margin {
	margin-left: 10px;
}
.bgcolor {
	background-color: black;
}
<%-- 과목 선택 칸 css --%>
.subjects-wrap {
	width:280px;
	height:220px;
	text-align:center;
	position:absolute;
	top:42%;
	left:50%;
	margin-left:-140px;
	margin-top:-110px;
	border-radius:1.5em;
	border:1px solid;
}
.subject-title-margin {
	margin-top: 15px;
	margin-bottom: 19px;
}
.subject-title-size {
	font-size: 20px;
}
</style>
</head>
<body class="font">
<!-- 토글바 -->
<nav class="container bgcolor">
	<div class="font-bold font-size font-color toggle-title">수학카페</div>
	<div class="toggle-button">
		<a href="login.do">
			<img class="icon-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout" onclick="abcd();">
			<img class="icon-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size text-color" href="mypost.do">나의 질문</a>
	</div>
</nav>
<!-- 과목 칸 -->
<div class="subjects-wrap">
	<div class="font-bold subject-title-size subject-title-margin">
	SUBJECTS
	</div>

	<div id="sub">
	<a class="text-color" href="sub_board.do?subject=Basic">기초수학 및 연습</a>
	</div>
	<div id="sub">
	<a class="text-color" href="sub_board.do?subject=Calc">미적분학</a>
	</div>
	<div id="sub">
	<a class="text-color" href="sub_board.do?subject=Linear">선형대수</a>
	</div>
</div>
</body>
</html>