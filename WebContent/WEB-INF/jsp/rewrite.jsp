<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script>
function fun(){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	var subject = urlParam.get('subject');
	var title = document.getElementById('x').value;
	var content = document.getElementById('y').value;
	var chapter = document.getElementById('chapter').value;
	var form = document.form;
	
	if( title == "" ){
		alert("제목 입력하세요!");
		form.action="write.do?subject="+subject;
	}
	else if( content == "" ) {
		alert("내용을 입력하세요!");
		form.action="write.do?subject="+subject;
	}
	else if( chapter == '챕터' ) {
		alert("챕터를 고르세요!");
		form.action="write.do?subject="+subject;
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
@media (max-width: 990px) {
	.ol {
		display:none !important;
	}
}
@media (max-width: 575px) {
	.ol {
		display:none !important;
	}
	.title{
		width:70% !important;
	}
	.content-width {
		width: 77% !important;
	}	
}
a {
	color: black;
	text-decoration: none;
}
h1 {
	text-align: center;
	padding: 20px;
	border-bottom: 1px solid gray;
}
#grid #main{
	margin: 25px 25px 25px 25px;	
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
	padding-right: 200px;
}
.item-button {
	margin-right:0px;
	padding-left: 200px;
}
.wrap-title {
	width: 500px;
	height: 100px;
	margin-left: 220px;
	margin-top: 50px;
	justify-self: center;
}
.wrap-content {
	width:500px;
	height:400px;
	margin-top: 50px;
	margin-left: -15px;
	justify-self: center;
	border: 1px solid;
	border-radius: 0.7em;
}
.ol {
	border-right: 1px solid gray;
	width:200px;
	height: 500px;
	padding-top: 20px;
	padding-left: 20px;
}
.item{
	justify-self:center;
	padding-left:-10px;
}
.title{
	margin-left: 40px;
	width:528px;
}
.content{
	margin-left: 40px;
}
.content-width {
	width:528px;
}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="container">
	<div class="item-title">로그인</div>
	<div class="item-button">
		<a href="start.do">
			<img src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout" onclick="abcd();">
			<img src="image/logout-box-line.svg"/>
		</a>
	</div>
</nav>
<div id="grid">
	<div class="wrap-ol col-xs-0.1 col-sm-1 col-md-3">
		<div class="ol">
			<li>
			<a href="sub_board.do?subject=Basic">기초수학 및 연습</a>
			</li>
			<li>
			<a href="sub_board.do?subject=Calc">미적분학</a>
			</li>
			<li>
			<a href="sub_board.do?subject=Linear">선형대수</a>
			</li>
		</div>
	</div>
	<div id="main" class="item wrap-title col-xs-11.9 col-sm-11 col-md-9">
		<form name="form" method="POST" action="question.do" id="write" enctype="multipart/form-data">
			<input type="hidden" name="subject" value="${ subject }"/>
			<div>
				<input class="title" type="text" name="title" id="x" value="${ article.title }"/>
			</div>
			<br/>
			<div class="content" >
				<div>
					<select name="ch" id="chapter">
						<option>챕터</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select>
				</div>			
				<textarea class="content-width" rows="10" name="content" id="y">${ article.content }</textarea>
				<div>
					<input type="file" name="fsn_q"/>
				</div>
				<div>
					<input type="submit" onclick="fun();"/>
				</div>		
			</div>
		</form>
	</div>
</div>
</body>
</html>
