<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.a {
	width:200px;
	height:150px;
	text-align:center;
	position:absolute;
	top:40%;
	left:50%;
	margin-left:-100px;
	margin-top:-75px;
}
#b {
	border:1px solid;
	width:150px;
	margin:0 auto;
	text-decoration: none;
}
a {
	color:black;
	text-decoration:none;
}
</style>
</head>
<body>
<div class="a">
과목 목록
	<div id="b">
	<a href="sub_board.jsp">기초수학 및 연습</a>
	</div>
	<div id="b">
	<a href="sub_board.do?subject=Calc">미적분학</a>
	</div>
	<div id="b">
	<a href="sub_board.do?subject=Linear">선형대수</a>
	</div>
</div>
</body>
</html>