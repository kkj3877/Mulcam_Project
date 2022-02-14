<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
<%-- 폰트 --%>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400;500;600;700&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
	font-weight: bolder;
	font-size: 20px;
}
<%-- 이미지 박스 --%>
img {
	float:left;
	padding:0px auto;
}
#image {
	height: 130px;
	width:200px;
	border-radius: 30px 30px 30px 30px;
	margin:0 auto;
	overflow:hidden;
}
#image-box {
	width:100%;
	height:99%;
	object-fit:cover;
}
.image-size {
	height: 28px;
}
.icon-white {
	 filter:invert();
}
<%-- 로그인, 회원가입 버튼 사이 간격--%>
.padding-bottom {
	padding-bottom: 20px;
}
<%-- 로그인, 회원가입 텍스트 설정 --%>
.text {
	color: black;
	text-decoration: none;
}
.width {
	width:80px;
	margin-left:60px;
}
.width-signup {
	width:100px;
	margin-left:52px;
}
<%-- 로그인 , 회원가입 버튼 위치 --%>
.a {
	width:200px;
	height:150px;
	text-align:center;
	position:absolute;
	top:40%;
	left:50%;
	margin-left:-100px;
	margin-top:-75px;
}
body {
	background-image: url('image/board-4.jpg');
	background-repeat: no-repeat;
	background-size: 100% auto;
}
</style>
</head>
<body class="font">
<div class="a">
<div id="image">
	<img id="image-box" src="image/KakaoTalk_20220204_164443247.jpg">
</div>
 <br/>
	<div class="width icon-white padding-bottom">
 		<a class="text " href="login.do" >
 			<img class="image-size" src="image/login-box-line.svg">Login
 		</a>
	</div>
 	<div class="icon-white width-signup">
 		<a class="text font-weight " href="signup.do">
 			<img class="image-size" src="image/account-box-line.svg">Sign Up
 		</a>
 	</div>
</div>

</body>
</html>
<!--
	background-color: #F2D7B6;
	height: 600px;
	border-bottom: 600px solid #A9D9CB;
	border-right: 670px solid #F29985;
	margin: auto;
-->