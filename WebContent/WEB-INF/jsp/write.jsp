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
<%-- 게시글 작성 함수 --%>
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
	}
	else if( content == "" ) {
		alert("내용을 입력하세요!");
	}
	else if( chapter == '챕터' ) {
		alert("챕터를 고르세요!");
	}
	else{
		document.form.submit();
	}
}
window.onload=function(){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	var ecode = urlParam.get('ecode');
	
	<%-- url에서 ecode 뒤 메세지 확인 --%>
	if( ecode == "invalid_session" ){
		alert('로그인 하셔야죠?');
	}	
	else if( ecode =="logout" ){
		alert('로그아웃');
	}
	<%-- 좌측 메뉴 선택 시 색상 변경 --%>
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
	
	<%-- 파일 업로드 이벤트리스너 --%>
	document.getElementById('submitFile').addEventListener('change', function(){
		var filename = document.getElementById('fileName');
		if( this.files[0] == undefined ) {
			filename.innerText = '선택된 파일없음';
		}
		filename.innerText = this.files[0].name;
	});
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
@media (max-width: 540px) {
	.toggle-title {
		margin-left:0px;
		padding-right: 100px !important;
	}
	.toggle-button {
		margin-right:0px;
		padding-left: 100px !important;
	}
	.write-button {
		margin-right: 100px !important;
		float: right;
	}
}
<%-- a 태그 검정색 고정 --%>
a {
	color: black;
	text-decoration: none;
}
<%-- 좌측 메뉴 과목 선택 시 색상, 크기 지정 css --%>
.ol {
	border-right: 1px solid gray;
	width:200px;
	height: 500px;
	padding-top: 20px;
	padding-left: 20px;
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

.title{
	margin-left: 40px;
	width:528px;
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
.toggle-title {
	margin-left:0px;
	padding-right: 200px;
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
.toggle-text-size {
	font-size: 18px;
	margin-top: 5px !important;
	margin-left: 7px;
}
.toggle-margin {
	margin-left: 10px;
}
<%-- 메인 css --%>
<%-- 제목 위치 css --%>
.wrap-title {
	width: 500px;
	height: 100px;
	margin-left: 150px;
	margin-top: 50px;
	justify-self: center;
}
<%-- 제목, 내용, 챕터, 파일업로드 가운데 정렬 css --%>
.align-column{
	justify-self:center;
	padding-left:-10px;
}
.content-ch-file-div-margin{
	margin-left: 40px;
}
.content-width {
	width:528px;
}
.file-margin {
	margin-top: 5px;
	margin-bottom: 20px;
}
.write-button {
	margin-right: -100px;
	float: right;
}
.ch-margin {
	margin-bottom: 5px;
}
.border-gray-radius {
	border-top: 1px solid #A6A6A6;
	border-right: 1px solid #A6A6A6;
	border-left: 1px solid #A6A6A6;
	border-bottom: 1px solid #A6A6A6;
	border-radius: 0.2em;
}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="font">
<!-- 토글바 -->
<nav class="container bgcolor">
	<div class="font-bold font-size font-color toggle-title">수학카페</div>
	<div class="toggle-button">
		<a href="sub_board.do?subject=${ subject }">
			<img class="icon-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout">
			<img class="icon-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size" href="mypost.do">나의 질문</a>
	</div>
</nav>
<!-- 사이드 메뉴 바 -->
<div id="grid">
	<div class="col-xs-0.1 col-sm-1 col-md-3">
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
	<!-- 메인 영역 -->
	<div id="main" class="align-column wrap-title col-xs-11.9 col-sm-11 col-md-9">
		<form name="form" method="POST" action="question.do" id="write" enctype="multipart/form-data">
			<input type="hidden" name="subject" value="${ subject }"/>
			<!-- 제목 입력 영역 -->
			<div>
				<input class="title border-gray-radius" type="text" name="title" id="x" placeholder="제목"/>
			</div>
			<br/>
			<div class="content-ch-file-div-margin" >
				<!-- 챕터 영역 -->
				<div>
					<select class="ch-margin border-gray-radius" name="ch" id="chapter">
						<option>챕터</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select>
				</div>			
				<!-- 내용 입력 영역 -->
				<textarea class="content-width border-gray-radius" rows="10" name="content" id="y" placeholder="질문내용입력하세요"></textarea>
				<!-- 파일 업로드 영역 -->
				<label class="btn btn-default btn-file" for="submitFile">파일업로드
					<input class="file-margin border-gray-radius" id="submitFile" type="file" name="fsn_q" style="display:none;"/>
				</label>
				<span id="fileName">선택된 파일없음</span>
				<!-- 제출 버튼 -->
				<div>
					<input class="write-button border-gray-radius" type="button" onclick="fun();" value="작성완료"/>
				</div>		
			</div>
		</form>
	</div>
</div>
</body>
</html>
