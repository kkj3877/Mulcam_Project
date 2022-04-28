<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Integer stid = (Integer)session.getAttribute("stid");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script>
<%-- 세션에서 받아온 학번 값 --%>
var stid = "<%=stid%>";

window.onload = function() {
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	const subject = urlParam.get('subject');
	var ecode = urlParam.get('ecode');
	
	<%-- url에서 ecode 뒤 메세지 확인 --%>
	if( ecode == "invalid_session" ){
		alert('로그인 하셔야죠?');
	}	
	else if( ecode =="logout" ){
		alert('로그아웃');
	}
	<%-- 사이드 메뉴 색상 표시 --%>
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
	<%-- 관리자 일 떄 답변 작성 div 표시 --%>
	var reply = document.getElementById('reply');
	if( stid != '0') {
		reply.remove();
	}
	<%-- 파일 업로드 이벤트리스너 --%>
	document.getElementById('submitFile').addEventListener('change', function(){
		var filename = document.getElementById('fileName');
		if( this.files[0] == undefined ) {
			filename.innerText = '선택된 파일없음';
		}
		filename.innerText = this.files[0].name;
	});		
}
<%-- 답변 제출 함수 --%>
function reply(){
	document.Sang.submit();
}
</script>
<style type="text/css">
<%-- 폰트 --%>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
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
<%-- 반응형 웹 --%>
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
@media (max-width: 698px) {
	.item-title {
		margin-left:0px;
		padding-right: 100px !important;
	}
	.item-button {
		margin-right:0px;
		padding-left: 100px !important;
	}
}
<%-- a 태그 검정색 고정 --%>
a {
	color: black;
	text-decoration: none;
}
<%-- 좌측 메뉴 과목 선택 시 색상, 크기 지정 css --%>
#basic {
	margin-top: 20px;
	width: auto !important;
	margin-bottom: 30px;
	margin-right: 52.5px;
	border-radius: 0.4em;
}
#calc {
	width: auto !important;
	margin-bottom: 30px;
	margin-right: 113px;
	border-radius: 0.4em;
}
#linear {
	width: auto !important;
	margin-bottom: 30px;
	margin-right: 95px;
	border-radius: 0.4em;
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
.li-font {
	font-size: 18px;
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
.bgcolor {
	background-color: black;
}
.image-size {
	height: 35px;
}
.icon-white {
	 filter:invert();
}
.item-title {
	margin-left:0px;
	padding-right: 200px;
}
.item-button {
	margin-right:0px;
	padding-left: 200px;
}.toggle-size {
	font-size: 18px;
	margin-top: 5px !important;
	margin-left: 7px;
}
.toggle-margin {
	margin-left: 10px;
}
.icon-size {
	height: 35px;
}
.wrap-title {
	width: 500px;
	height: 115px;
	margin-left: 200px;
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
.article-margin {
	margin-left: 10px;
	margin-top: 7px;
	margin-bottom: 0px;
}
.content {
	width: 25px;
	border-bottom: 2px solid black;
}
.content-margin {
	margin-left: 23px;
	margin-top: 7px;
	margin-bottom: 8px;
}
.write-image {
	margin:0 auto;
	overflow:hidden;
	margin-top: 0px;
	margin-left: -15px;
	width: 500px;
	height: 300px;
	background-color: #FFFAF0;
	border-radius: 0.9em;
}
.image-box {
	width: 100%;
	height: 100%;
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
.reply-form {
	margin-left: -14px;
	height: 190px;
}
.border-reply-button {
	border-top: 1px solid #A6A6A6;
	border-right: 1px solid #A6A6A6;
	border-left: 1px solid #A6A6A6;
	border-bottom: 1px solid #A6A6A6;
	border-radius: 0.2em;
}
.reply-width {
	width: 500px;	
}
.write-button {
	margin-top: 10px;
	margin-right: -20px;
	float: right;
	top: 70%;
}
.reply-view {
	border-top: 1px solid #FFFFFF;
	border-right: 1px solid #FFFFFF;
	border-left: 1px solid #FFFFFF;
	border-bottom: 1px solid #FFFFFF;
	background-color: #E8E8E8;
	border-radius: 0.9em;
	height: 100px;
	width: 500px;
	margin-left: -15px;
	padding-top: 7px;
	margin-top: 30px;
}
.reply-margin {
	margin-left: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
}
.title-width-border {
	width: 25px;
	border-bottom: 2px solid black;
}
.reply-image {
	margin:0 auto;
	overflow:hidden;
	margin-top: -0.6px;
	margin-left: -15px;
	width: 500px;
	height: 300px;
	background-color: #E8E8E8;
	border-radius: 0.9em;
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
		<a style="padding-left:17px;"href="login.do?ecode=logout">
			<img class="image-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="status.do">
			<img class="icon-size icon-white" src="image/settings-2-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-size" href="mypost.do">나의 질문</a>
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
		<div class="font-bold title-width-border article-margin">제목</div>
		<div class="article-margin">${ article.title }</div>
		
		<div class="border-gray-radius wrap-content">
			<div class="font-bold title-width-border content-margin">내용</div>
			<div class="content-margin">${ article.content }</div>
		</div>
		
		<div class="write-image">
			<div>${fsn_q}</div>
			<img class="image-box" src="image.jsp?fname=${fsn_q}"/>
		</div>
		
		<q:if test="${ article.ans != null }"> 
			<div class="reply-view">
				<span style="padding-top: 10px;" class="font-bold reply-margin title-width-border">답변</span>
				<div class="reply-margin">${ article.ans }</div>
			</div>
			<div class="reply-image">
					<img class="image-box" src="image.jsp?fname=${fsn_a}"/>
			</div>
		</q:if>
		
		<div id="reply" class="reply-form">
			<form method="POST" name="Sang" action="answer.do" enctype="multipart/form-data">
				<input type="hidden" name="subject" value="${ subject }"/>
				<input type="hidden" name="no" value="${ article.no }"/>
				<input type="hidden" name="fsn_a_original" value="${ article.fsn_a }"/>
				<textarea class="reply-width border-gray-reply"  rows="6" name="content" placeholder="답변"></textarea>
				
				<label class="btn btn-default btn-file" for="submitFile">파일업로드
					<input class="file-margin border-gray-radius" id="submitFile" type="file" name="fsn_q" style="display:none;"/>
				</label>
				<span id="fileName">선택된 파일없음</span>
				
				<input class="write-button border-gray-button" type="button" onclick="reply();" value="작성완료"/>	
			</form>
		</div>
		
		<div class="buttons">
		<div class="button-margin">
			<a class="list-button" href="sub_board.do?subject=${ subject }">
				<img class="button-size" src="image/file-list-line.svg"/>
			</a>
		</div>
		<div class="button-margin">
			<a class="delete-button" href="del_post.do?subject=${ subject }&no=${article.no}">
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