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
	else {
		document.signup.submit();
	}
}
</script>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
}
@media (max-width: 375px){
	.item-button {
		padding-left: 10px;
	}
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
	padding-right: 300px;
}
.item-button {
	margin-right:0px;
	padding-left: 200px;
}
.a {
	width:280px;
	height:290px;
	text-align:center;
	position:absolute;
	top:40%;
	left:50%;
	margin-left:-150px;
	margin-top:-75px;
	border:1px solid;
	border-radius:1.5em;
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
.e {
	padding-top:10px;
}
.padding_bottom{
	padding-bottom: 16px;
}
.button {
	border-radius: 0.4em;
	border: 1px black;
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
.wrapper-title-size {
	font-size: 20px;
}
.button {
	margin-top: 15px;
	border-radius: 0.4em;
	border: 1px black;
}
.submit-size {
	font-size: 17px;
}
</style>
</head>
<body class="font">
<nav class="container bgcolor">
	<div class="font-bold font-size font-color item-title">수학카페</div>
	<div class="item-button">
		<a href="start.do">
			<img class="image-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
	</div>
</nav>
<div class="a">
	<div class="font-bold wrapper-title-size e">Sign up</div>	
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
		<input class="submit-size button" type="button" value="Click" onclick="abcd();"/>
	</div>

	</form>
</div>
</body>
</html>