<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
.container {
	position: relative;
	height: 50px;
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
	top:40%;
	left:50%;
	margin-left:-140px;
	margin-top:-110px;
	border: 1px solid;
	border-radius: 0.7em;
}
.subs {
	margin-top: 15px;
	margin-bottom: 19px;
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
</style>
</head>
<body>
<nav class="container">
	<div class="item-title">로그인</div>
	<div class="item-button">
		<a href="start.do">
			<img src="image/arrow-left-line.svg"/>
		</a>
	</div>
</nav>
<div class="a">
	<div class="subs">
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