<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	border:1px solid;
}

</style>
</head>
<body>
<div class="a">
	Login	
<form method="POST" action="logintry.do">
	<div>
		<div class="b">
			<input type="text" name="stid" value="학번"/>
		</div>
		<br/>
		<div class="c">
			<input type="text" name="pw" value="비밀번호"/>
		</div>
	</div>
	<div>
		<a href="board.jsp">완료</a>
		<input type="submit"/>
	</div>
</form>
</div>
</body>
</html>