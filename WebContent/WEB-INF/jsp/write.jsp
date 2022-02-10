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
<%-- �Խñ� �ۼ� �Լ� --%>
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
		alert("é�͸� ������!");
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
	
	<%-- url���� ecode �� �޼��� Ȯ�� --%>
	if( ecode == "invalid_session" ){
		alert('�α��� �ϼž���?');
	}	
	else if( ecode =="logout" ){
		alert('�α׾ƿ�');
	}
	<%-- ���� �޴� ���� �� ���� ���� --%>
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
	
	<%-- ���� ���ε� �̺�Ʈ������ --%>
	document.getElementById('submitFile').addEventListener('change', function(){
		var filename = document.getElementById('fileName');
		if( this.files[0] == undefined ) {
			filename.innerText = '���õ� ���Ͼ���';
		}
		filename.innerText = this.files[0].name;
	});
}

</script>
<style type="text/css">
<%-- ��Ʈ --%>
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
<%-- ������ �� --%>
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
<%-- a �±� ������ ���� --%>
a {
	color: black;
	text-decoration: none;
}
<%-- ���� �޴� ���� ���� �� ����, ũ�� ���� css --%>
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
<%-- ���� css --%>
<%-- ���� ��ġ css --%>
.wrap-title {
	width: 500px;
	height: 100px;
	margin-left: 150px;
	margin-top: 50px;
	justify-self: center;
}
<%-- ����, ����, é��, ���Ͼ��ε� ��� ���� css --%>
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
<!-- ��۹� -->
<nav class="container bgcolor">
	<div class="font-bold font-size font-color toggle-title">����ī��</div>
	<div class="toggle-button">
		<a href="sub_board.do?subject=${ subject }">
			<img class="icon-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout">
			<img class="icon-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size" href="mypost.do">���� ����</a>
	</div>
</nav>
<!-- ���̵� �޴� �� -->
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
	<!-- ���� ���� -->
	<div id="main" class="align-column wrap-title col-xs-11.9 col-sm-11 col-md-9">
		<form name="form" method="POST" action="question.do" id="write" enctype="multipart/form-data">
			<input type="hidden" name="subject" value="${ subject }"/>
			<!-- ���� �Է� ���� -->
			<div>
				<input class="title border-gray-radius" type="text" name="title" id="x" placeholder="����"/>
			</div>
			<br/>
			<div class="content-ch-file-div-margin" >
				<!-- é�� ���� -->
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
				<!-- ���� �Է� ���� -->
				<textarea class="content-width border-gray-radius" rows="10" name="content" id="y" placeholder="���������Է��ϼ���"></textarea>
				<!-- ���� ���ε� ���� -->
				<label class="btn btn-default btn-file" for="submitFile">���Ͼ��ε�
					<input class="file-margin border-gray-radius" id="submitFile" type="file" name="fsn_q" style="display:none;"/>
				</label>
				<span id="fileName">���õ� ���Ͼ���</span>
				<!-- ���� ��ư -->
				<div>
					<input class="write-button border-gray-radius" type="button" onclick="fun();" value="�ۼ��Ϸ�"/>
				</div>		
			</div>
		</form>
	</div>
</div>
</body>
</html>
