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
@media (max-width: 375px){
	.item-button {
		padding-left: 10px;
	}
}
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
}
.item-button {
	margin-right:0px;
	padding-left: 200px;
}
.a {
	width:300px;
	height:180px;
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
.button {
	border-radius: 0.4em;
	border: 1px black;
}
.e {
	padding-top:10px;
}
</style>
</head>
<body>
<nav class="container">
	<div class="item-title">수학카페</div>
	<div class="item-button">
		<a href="start.do">
			<img src="image/arrow-left-line.svg"/>
		</a>
	</div>
</nav>
<div class="a">
<div class="e">Login</div>
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
			<input class="button" type="button" value="Login" onclick="abcd();"/>
		</div>
	</form>
</div>
</body>
</html>
<!-- 
<div id="icon">
	<a href="start.jsp">
		<img src="image/arrow-left-line.svg"/>
	</a>
</div>
 -->