<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script language="javascript">
<%-- 로그인 함수 --%>
function log() {
	var stid = document.login.stid.value;
	var pw = document.login.pw.value;
	
	if( stid=='' ){
		alert('아이디를 입력하셔야죠^-^');
	}
	else if( pw =='' ){
		alert('비밀번호를 입력하셔야죠^-^');
	}
	else {
		var rsaid = stid + 'rsa';
		var rsapw = pw + 'rsa';
		
		window.location.href = "logintry.do?stid="+rsaid+"&pw="+rsapw;
	}
}
<%-- url에서 ecode 뒤 메세지를 확인--%>
window.onload=function(){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	var ecode = urlParam.get('ecode');
	
	if( ecode == "invalid_session" ){
		alert('로그인 하셔야죠?');
	}	
	else if( ecode =="logout" ){
		alert('로그아웃');
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
.icon-white {
	 filter:invert();
}
.icon-size {
	height: 35px;
}
.toggle-title {
	margin-left:0px;
	padding-right: 300px;
}
.toggle-button {
	margin-right:0px;
	padding-left: 200px;
}
<%-- 로그인 칸 css --%>
.login-wrap {
	width:280px;
	height:218px;
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
.login-font-size {
	font-size: 20px;
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
<%-- 로그인 버튼 테투리 --%>
.button-border {
	margin-top: 15px;
	border-radius: 0.4em;
	border: 1px black;
}
.padding-top {
	padding-top:10px;
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
.bgcolor {
	background-color: black;
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
<!-- 로그인 칸 -->
<div class="login-wrap">
<div class="font-bold login-font-size padding-top">Login</div>
	<form method="POST" name="login" id="login" action="logintry.do">
		<div class="input-padding">
		<!-- 학번 입력칸 -->
			<div>
				<input class="input-border" type="text" id="stid" name="stid" placeholder="학번"/>
			</div>
			<br/>
		<!-- 비밀번호 입력칸 -->
			<div>
				<input class="input-border" type="password" id="pw" name="pw" placeholder="비밀번호"/>
			</div>
		</div>
		<!-- 로그인 버튼 -->
		<div>
			<input class="button-size button-border" type="button" value="Login" onclick="log();"/>
		</div>
	</form>
</div>
</body>
</html>