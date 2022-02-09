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
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
}
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
a {
	color:black;
	text-decoration:none;
}
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
.item-title {
	margin-left:0px;
	padding-right: 200px;
}
.item-button {
	margin-right:0px;
	padding-left: 200px;
}
.a {
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
.subs {
	margin-top: 15px;
	margin-bottom: 19px;
}
.toggle-size {
	font-size: 15px;
	margin-top: 5px !important;
	margin-left: 7px;
}
.bgcolor {
	background-color: black;
}
.font-bold {
	font-weight: bold;
}
.font-size {
	font-size: 30px;
}
.font-color {
	color: #FFFFFF;
}
.image-size {
	height: 35px;
}
.icon-white {
	 filter:invert();
}
.toggle-text-size {
	font-size: 18px;
}
.toggle-margin {
	margin-left: 10px;
}
.font-bold {
	font-weight: bold;
}
.wrapper-title-size {
	font-size: 20px;
}
</style>
</head>
<body class="font">
<nav class="container bgcolor">
	<div class="font-bold font-size font-color item-title">수학카페</div>
	<div class="item-button">
		<a href="login.do">
			<img class="image-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout" onclick="abcd();">
			<img class="image-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size" class="toggle-size" href="mypost.do">나의 질문</a>
	</div>
</nav>
<div class="a">
	<div class="font-bold wrapper-title-size subs">
	SUBJECTS
	</div>

	<div id="sub">
	<a href="sub_board.do?subject=Basic">기초수학 및 연습</a>
	</div>
	<div id="sub">
	<a href="sub_board.do?subject=Calc">미적분학</a>
	</div>
	<div id="sub">
	<a href="sub_board.do?subject=Linear">선형대수</a>
	</div>
</div>
</body>
</html>