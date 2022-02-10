<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script language="javascript">
<%-- �α��� �Լ� --%>
function log() {
	var stid = document.login.stid.value;
	var pw = document.login.pw.value;
	
	if( stid=='' ){
		alert('���̵� �Է��ϼž���^-^');
	}
	else if( pw =='' ){
		alert('��й�ȣ�� �Է��ϼž���^-^');
	}
	else {
		var rsaid = stid + 'rsa';
		var rsapw = pw + 'rsa';
		
		window.location.href = "logintry.do?stid="+rsaid+"&pw="+rsapw;
	}
}
<%-- url���� ecode �� �޼����� Ȯ��--%>
window.onload=function(){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	var ecode = urlParam.get('ecode');
	
	if( ecode == "invalid_session" ){
		alert('�α��� �ϼž���?');
	}	
	else if( ecode =="logout" ){
		alert('�α׾ƿ�');
	}
}
</script>
<style type="text/css">
<%-- ��Ʈ --%>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
}
<%-- ������ �� --%>
@media (max-width: 375px){
	.toggle-button {
		padding-left: 10px;
	}
}
<%-- ��۹� css --%>
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
<%-- �α��� ĭ css --%>
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
<%-- �Է�ĭ �׵θ� --%>
.input-border {
 	border-right: white 1px solid;
    border-left: white 1px solid;
    border-top: white 1px solid;
	border-bottom:1px solid black;
}
<%-- �Է�ĭ ���� ���� --%>
.input-padding {
	padding:15px;
}
<%-- �α��� ��ư ������ --%>
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
<!-- ��۹� -->
<nav class="container bgcolor">
	<div class="font-bold font-size font-color toggle-title">����ī��</div>
	<div class="toggle-button">
		<a href="start.do">
			<img class="icon-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
	</div>
</nav>
<!-- �α��� ĭ -->
<div class="login-wrap">
<div class="font-bold login-font-size padding-top">Login</div>
	<form method="POST" name="login" id="login" action="logintry.do">
		<div class="input-padding">
		<!-- �й� �Է�ĭ -->
			<div>
				<input class="input-border" type="text" id="stid" name="stid" placeholder="�й�"/>
			</div>
			<br/>
		<!-- ��й�ȣ �Է�ĭ -->
			<div>
				<input class="input-border" type="password" id="pw" name="pw" placeholder="��й�ȣ"/>
			</div>
		</div>
		<!-- �α��� ��ư -->
		<div>
			<input class="button-size button-border" type="button" value="Login" onclick="log();"/>
		</div>
	</form>
</div>
</body>
</html>