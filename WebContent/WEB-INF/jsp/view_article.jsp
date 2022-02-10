<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script>
function abcd(value){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	const subject = urlParam.get('subject');
	
	window.location.href="sub_board.do?subject="+subject;
}
window.onload = function() {
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	const subject = urlParam.get('subject');
	 
	if( subject == 'Basic' ){
		$(function(){
			$('#basic').css('background-color', '#A9D9CB');
		});
	}
	else if( subject == 'Calc' ){
		$(function(){
			$('#calc').css('background-color', '#A9D9CB');
		});
	}
	else if( subject == 'Linear' ){
		$(function(){
			$('#linear').css('background-color', '#A9D9CB');
		});
	}
}
function del() {
	
	
}
</script>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
}
@media (max-width: 800px) {
	.ol {
		display:none !important;
	}
	.wrap-title {
		width: 523 !important; 
		height: 100px;
		margin-left: 50px !important;	
		margin-top: 50px;
		justify-self: center;
		border: 1px solid;
		border-radius: 0.7em;
	}
	.wrap-content {
		width: 500px !important;
		height:400px;
		margin-top: 50px;
		justify-self: center;
		border: 1px solid;
		border-radius: 0.7em;
	}
}
@media (max-width: 566px) {
	.ol {
		display:none !important;
	}
	.wrap-title {
		width: auto !important; 
		height: 100px;
		margin-right: 50px !important;	
		margin-left: 50px !important;	
		margin-top: 50px;
		justify-self: center;
		border: 1px solid;
		border-radius: 0.7em;
	}
	.wrap-content {
		width: auto !important;
		height:400px;
		margin-right: -15px !important;
		margin-top: 50px;
		justify-self: center;
		border: 1px solid;
		border-radius: 0.7em;
	}
}
@media (max-width: 540px) {
	.item-title {
		margin-left:0px;
		padding-right: 100px !important;
	}
	.item-button {
		margin-right:0px;
		padding-left: 100px !important;
	}
}
a {
	color: black;
	text-decoration: none;
}
#basic {
	margin-top: 20px;
	width: auto !important;
	margin-bottom: 30px;
	margin-right: 51.7px;
}
#calc {
	width: auto !important;
	margin-bottom: 30px;
	margin-right: 113px;
}
#linear {
	width: auto !important;
	margin-bottom: 30px;
	margin-right: 95px;
}
.ol {
	border-right: 1px solid gray;
	width:200px;
	padding-top: 20px;
	padding-left: 20px;
	display: flex;
	flex-direction: column;
	height: 800px;
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
	padding-right: 200px;
}
.item-button {
	margin-right:0px;
	padding-left: 200px;
}
.wrap-title {
	width: 500px;
	height: 115px;
	margin-left: 180px;
	margin-top: 50px;
	justify-self: center;
	border: 1px solid;
	border-radius: 0.7em;
}
.wrap-content {
	width:500px;
	height:200px;
	margin-top: 25px;
	margin-left: -15px;
	justify-self: center;
	border: 1px solid;
	border-radius: 0.7em;
}
.article {
	width: 25px;
	border-bottom: 2px solid #A6A6A6;
}
.article-margin {
	margin-left: 10px;
	margin-top: 7px;
	margin-bottom: 8px;
}
.content {
	width: 25px;
	border-bottom: 2px solid #A6A6A6;
}
.content-margin {
	margin-left: 19px;
	margin-top: 7px;
	margin-bottom: 8px;
}
.wrap-image {
	margin-top: 10px;
	margin-left: -15px;
	width: 500px;
	height: 300px;
}
.image-box {
	width:100%;
	height:99%;
	object-fit:cover;
}
.buttons {
	display: flex;
	flex-direction: row-reverse;
}
.list-button {
	float: left !important;
}
.delete-button {
	float: left !important;
}
.revise-button {
	float: left !important;
}
.button-size {
	height: 38px;
}
.li-font {
	font-size: 18px;
}
.toggle-size {
	font-size: 15px;
	margin-top: 5px !important;
	margin-left: 7px;
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
.toggle-margin {
	margin-left: 10px;
}
.toggle-text-size {
	font-size: 18px;
}
.border-gray-radius {
	border-top: 1px solid #FFFFFF;
	border-right: 1px solid #FFFFFF;
	border-left: 1px solid #FFFFFF;
	border-bottom: 1px solid #FFFFFF;
	border-radius: 0.9em;
	background-color: #FFFAF0;
}
.subject-size {
	font-size: 18px;
}
.button-margin {
	margin-left: 10px;
}
</style>
</head>
<body class="font">
<nav class="container bgcolor">
	<div class="font-bold font-size font-color item-title">수학카페</div>
	<div class="item-button">
		<a href="sub_board.do?subject=${ subject }">
			<img class="image-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout" onclick="abcd();">
			<img class="image-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size" class="toggle-size" href="mypost.do">나의 질문</a>
	</div>
</nav>
<div class="grid">

	<div class="wrap-ol col-xs-0.1 col-sm-1 col-md-3">
		<div class="ol">
			<div id="basic">
				<a class="li-font font-bold" href="sub_board.do?subject=Basic">기초 수학 및 연습</a>
			</div>
			<div id="calc">
				<a class="li-font font-bold" href="sub_board.do?subject=Calc">미적분학</a>
			</div>
			<div id="linear">
				<a class="li-font font-bold" href="sub_board.do?subject=Linear">선형대수학</a>
			</div>
		</div>
	</div>
	
	<div class="border-gray-radius wrap-title col-xs-11.9 col-sm-11 col-md-9">
		<div class="font-bold subject-size article-margin">${ subject } > ${ article.ch }</div>
		<div class="font-bold article article-margin">제목</div>
		<div class="article-margin">${ article.title }</div>
		
		<div class="border-gray-radius wrap-content">
			<div class="font-bold content content-margin">내용</div>
			<div class="content-margin">${ article.content }</div>
		</div>
		
		<div class="wrap-image">
			<img class="image-box" src="image.jsp?fname=${fsn_q}"/>
		</div>
		<div class="buttons">
			<div class="button-margin">
				<a class="list-button" href="sub_board.do?subject=${ subject }">
					<img class="button-size" src="image/file-list-line.svg"/>
				</a>
			</div>
			<div class="button-margin">
				<a class="delete-button" href="del_post.do?subject=${ subject }&no=${article.no}" onclick="del();">
					<img class="button-size" src="image/chat-delete-line.svg"/>
				</a>
			</div>
			<div class="button-margin">
				<a class="revise-button" href="rewrite.do?subject=${ subject }&no=${article.no}&fname=${ fsn_q }">
					<img class="button-size" src="image/eraser-line.svg"/>
				</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>