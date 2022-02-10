<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"
import="Control.Util, java.io.File"%>
<% 
	String fname = request.getParameter("fname");
	System.out.println( fname );
	File file = null;
	String file_dir = null;
	if( fname != null ) {
		file = new File( Util.uploadDir() + fname );
		file_dir = file.toString();
	}
	
%>
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
		alert("���� �Է��ϼ���!");
	}
	else if( content == "" ) {
		alert("������ �Է��ϼ���!");
	}
	else if( chapter == 'é��' ) {
		alert("é�͸� ��������!");
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
	
	if( ecode == "invalid_session" ){
		alert('�α��� �ϼž���?');
	}	
	else if( ecode =="logout" ){
		alert('�α׾ƿ�');
	}
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
window.onload=function() {
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	var filename = document.getElementById('fileName');
	var fname = urlParam.get('fname');
	filename.innerText = fname;
	
	document.getElementById('submitFile').addEventListener('change', function(){
		if( this.files[0] == fname ) {
			filename.innerText = fname;
		}
		filename.innerText = this.files[0].name;
	});
	
}
</script>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
}
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
	.item-title {
		margin-left:0px;
		padding-right: 100px !important;
	}
	.item-button {
		margin-right:0px;
		padding-left: 100px !important;
	}
	#grid #main{
		margin: 100px 70px 70px 30px !important;	
	}
	.write-button {
		margin-right: 100px !important;
		float: right;
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
	margin: 100px 70px 70px 100px;	
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
	width: 500px;
}
.content-width {
	width:528px;
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
.toggle-text-size {
	font-size: 18px;
}
.toggle-margin {
	margin-left: 10px;
}
.file-margin {
	margin-top: 5px;
	margin-bottom: 20px;
}
.write-button {
	margin-top: 10px;
	margin-right: -30px;
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
.submit-custom {
	display:none;
}
span {
	height: 10px;
}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="font">
<nav class="container bgcolor">
	<div class="font-bold font-size font-color item-title">����ī��</div>
	<div class="item-button">
		<a href="view_article.do?subject=${ subject }&no=${ article.no }">
			<img class="image-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout" onclick="abcd();">
			<img class="image-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size" class="toggle-size" href="mypost.do">���� ����</a>
	</div>
</nav>
<div id="grid">
	<div class="col-xs-0.1 col-sm-1 col-md-3">
		<div class="ol">
			<div id="basic">
				<a class="li-font font-bold" href="sub_board.do?subject=Basic">���� ���� �� ����</a>
			</div>
			<div id="calc">
				<a class="li-font font-bold" href="sub_board.do?subject=Calc">��������</a>
			</div>
			<div id="linear">
				<a class="li-font font-bold" href="sub_board.do?subject=Linear">���������</a>
			</div>
		</div>
	</div>
	<div id="main" class="item wrap-title col-xs-11.9 col-sm-11 col-md-9">
		<form name="form" method="POST" action="change.do" id="write" enctype="multipart/form-data">
			<input type="hidden" name="subject" value="${ subject }"/>
			<div>
				<input class="title border-gray-radius" type="text" name="title" id="x" value="${ article.title }"/>
			</div>
			<br/>
			<div class="content" >
				<div>
					<select class="ch-margin border-gray-radius" name="ch" id="chapter">
						<option>é��</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select>
				</div>			
				<textarea class="content-width border-gray-radius" rows="10" name="content" id="y">${ article.content }</textarea>
			
				<label class="btn btn-default btn-file" for="submitFile">���Ͼ��ε�
					<input class="file-margin border-gray-radius" id="submitFile" type="file" name="fsn_q" style="display:none;"/>
				</label>
				<span id="fileName">���Ͼ���</span>
				
				<div>
					<input class="write-button border-gray-radius" type="button" onclick="fun();" value="�ۼ��Ϸ�"/>
				</div>		
			</div>
		</form>
	</div>
</div>
</body>
</html>