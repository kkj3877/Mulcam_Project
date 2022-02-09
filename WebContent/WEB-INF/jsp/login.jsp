<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script language="javascript">
function abcd() {
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
.b {
 	border-right: white 1px solid;
    border-left: white 1px solid;
    border-top: white 1px solid;
	border-bottom:1px solid black;
}
.c {
	padding:15px;
}
.button {
	margin-top: 15px;
	border-radius: 0.4em;
	border: 1px black;
}
.e {
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
.icon-white {
	 filter:invert();
}
.login-size {
	font-size: 20px;
}
.image-size {
	height: 35px;
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
<div class="font-bold login-size e">Login</div>
	<form method="POST" name="login" id="login" action="logintry.do">
		<div class="c">
			<div>
				<input class="b" type="text" id="stid" name="stid" placeholder="학번"/>
			</div>
			<br/>
			<div>
				<input class="b" type="password" id="pw" name="pw" placeholder="비밀번호"/>
			</div>
		</div>
		
		<div>
			<input class="submit-size button" type="button" value="Login" onclick="abcd();"/>
		</div>
	</form>
</div>
</body>
</html>