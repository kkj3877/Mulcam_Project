<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
.a {
	text-align:center;
	position:absolute;
	width:300px;
	margin:150px 0px 0px 0px;
	border:1px solid;
}
#width {
	width:200px;
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