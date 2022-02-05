<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
img {
	float:left;
	padding:0px auto;
}
.padding-bottom {
	padding-bottom: 20px;
}
.text {
	font-size: ;
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
</style>
</head>
<body>
<div class="a">
<div id="image">
	<img id="image-box" src="image/KakaoTalk_20220204_164443247.jpg">
</div>
 <br/>
	<div class="width padding-bottom">
 		<a class="text" href="login.do" >
 			<img src="image/login-box-line.svg">Login
 		</a>
	</div>
 	<div class="width-signup">
 		<a class="text" href="signup.do">
 			<img src="image/account-box-line.svg">Sign Up
 		</a>
 	</div>
</div>

</body>
</html>
<!--
 메인페이지 

-- 로그인, 회원가입
-->