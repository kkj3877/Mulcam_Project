<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script language="javascript">
function abcd() {
	if( document.signup.name.value=='' ){
		alert('이름을 입력하셔야죠^-^');
	}
	else if( document.signup.stid.value=='' ){
		alert('학번을 입력하셔야죠^-^');
	}
	else if( document.signup.mail.value=='' ){
		alert('메일을 입력하셔야죠^-^');
	}
	else if( document.signup.pw.value=='' ){
		alert('비밀번호를 입력하셔야죠^-^');
	}
}
</script>
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
	width:300px;
	height:240px;
	text-align:center;
	position:absolute;
	top:40%;
	left:50%;
	margin-left:-150px;
	margin-top:-75px;
	border:1px solid;
	border-radius:0.4em;
	padding-top: 10px;
}
.b {
 	border-right: white 1px solid;
    border-left: white 1px solid;
    border-top: white 1px solid;
	border-bottom:1px solid black;
}
.c {
	padding:15px;
}
.d {
	margin-bottom:20px;
}
.c {
	padding:15px;
}
.padding_bottom{
	padding-bottom: 16px;
}
.button {
	border-radius: 0.4em;
	border: 1px black;
}
</style>
</head>
<body>
<nav class="container">
	<div class="item-title">회원가입</div>
	<div class="item-button">
		<a href="start.jsp">
			<img src="image/arrow-left-line.svg"/>
		</a>
	</div>
</nav>
<div class="a">
	Sign up	
	<form method="POST" name="signup" action="signuptry.do">
	<div class="c">
		<div class="padding_bottom">
			<input class="b" type="text" name="name" placeholder="이름"/>
		</div>
		<div class="padding_bottom">
			<input class="b" type="text" name="stid" placeholder="학번"/>
		</div>
		<div class="padding_bottom">
			<input class="b" type="text" name="mail" placeholder="이메일"/>
		</div>
		<div>
			<input class="b" type="password" name="pw" placeholder="비밀번호"/>
		</div>
	</div>
	
	<div>
		<input class="button" type="button" value="Signup" onclick="abcd();"/>
	</div>

	</form>
</div>
</body>
</html>