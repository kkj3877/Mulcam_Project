<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
.a {
	width:200px;
	height:210px;
	text-align:center;
	position:absolute;
	top:40%;
	left:50%;
	margin-left:-100px;
	margin-top:-105px;
	border:1px solid;
}
.b {
	margin-top:10px;
}
.c {
	margin-bottom:10px;
}
.d {
	margin-bottom:20px;
}
</style>
</head>
<body>

<div class="a">
	Sign up	
	<form method="POST" action="signuptry.do">
		<div>
		<div class="b">
			<input type="text" name="name" value="�̸�"/>
		</div>
		<div class="b">
			<input type="text" name="stid" value="�й�"/>
		</div>
		<br/>
		<div class="d">
			<input type="text" name="mail" value="�̸���"/>
		</div>
		<div class="c">
			<input type="text" name="pw" value="��й�ȣ"/>
		</div>
		</div>
		<div>
			<a href="login.jsp">�Ϸ�</a>
			<input type="submit"/>
		</div>
	</form>
</div>
</body>
</html>