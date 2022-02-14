<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script language="javascript">
<%-- 회원가입 함수 --%>
function sign() {
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
<%-- 폰트 --%>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
}
<%-- 반응형 웹 --%>
@media (max-width: 375px){
	.toggle-button {
		padding-left: 10px;
	}
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
	padding-right: 300px;
}
.toggle-button {
	margin-right:0px;
	padding-left: 200px;
}
.icon-size {
	height: 35px;
}
.icon-white {
	 filter:invert();
}
<%-- 회원가입 칸 css --%>
.sign-wrap {
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
<%-- 입력칸 테두리 --%>
.input-border {
 	border-right: white 1px solid;
    border-left: white 1px solid;
    border-top: white 1px solid;
	border-bottom:1px solid black;
}
<%-- 입력칸 사이 간격 --%>
.input-padding {
	padding:15px;
}
.padding-top {
	padding-top:10px;
}
.padding_bottom{
	padding-bottom: 16px;
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

.signup-font-size {
	font-size: 20px;
}
<%-- 로그인 버튼 테투리 --%>
.button-border {
	margin-top: 15px;
	border-radius: 0.4em;
	border: 1px black;
}
.button-size {
	font-size: 17px;
}
</style>
</head>
<body class="font">
<!-- 토글바 -->
<nav class="container bgcolor">
	<div class="font-bold font-size font-color toggle-title">수학카페</div>
	<div class="toggle-button">
		<a href="start.do">
			<img class="icon-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
	</div>
</nav>
<!-- 회원가입 칸 -->
<div class="sign-wrap">
	<div class="font-bold signup-font-size padding-top">Sign up</div>	
	<form method="POST" name="signup" action="signuptry.do">
	<div class="input-padding">
		<!-- 이름 입력칸 -->
		<div class="padding_bottom">
			<input class="input-border" type="text" name="name" placeholder="이름" autocomplete="off"/>
		</div>
		<!-- 학번 입력칸 -->
		<div class="padding_bottom">
			<input class="input-border" type="text" name="stid" placeholder="학번" autocomplete="off"/>
		</div>
		<!-- 이메일 입력칸 -->
		<div class="padding_bottom">
			<input class="input-border" type="text" name="mail" placeholder="이메일" autocomplete="off"/>
		</div>
		<!-- 비밀번호 입력칸 -->
		<div>
			<input class="input-border" type="password" name="pw" placeholder="비밀번호" autocomplete="off"/>
		</div>
	</div>
	<!-- 회원가입 버튼 -->
	<div>
		<input class="button-size button-border" type="button" value="Click" onclick="sign();"/>
	</div>

	</form>
</div>
</body>
</html>