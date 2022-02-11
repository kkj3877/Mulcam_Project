<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script>
<%-- �α׾ƿ� �Լ� --%>
function logout(value){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	const subject = urlParam.get('subject');
	
	window.location.href="sub_board.do?subject="+subject+"&ch="+value;
}
<%-- ���� �޴� ���� �� ���� ���� --%>
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
	
	document.getElementById('reply-button').addEventListener('mouseover',function(event){
		var text = document.getElementById('reply-text');
		event.target.style.display = 'inline';
		
		setTimeout( function() {
			event.target.style.display="";
		}, 500);
	}, false);
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
@media (max-width: 800px) {
	.ol {
		display:none !important;
	}
	#grid {
		display:grid;
		grid-template-columns: auto !important;
	}
	.main{
		padding-top: 20px;
		padding-left:0px !important;
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
<%-- a �±� ������ ���� --%>
a {
	color: black;
	text-decoration: none;
}
<%-- ���� �޴� ��, ���� ���� grid css --%>
#grid {
	display:grid;
	grid-template-columns: 200px 2fr 0.5fr;
}
<%-- ���� �޴� ���� ���� �� ����, ũ�� ���� css --%>
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
.table-padding{
	padding-top: 20px;
	padding-left: 150px;
}
.ol {
	margin-left: 15px;
	border-right: 1px solid gray;
	width:200px;
	padding-top: 20px;
	padding-left: 20px;
	display: flex;
	flex-direction: column;
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
.toggle-title {
	margin-left:0px;
	padding-right: 200px;
}
.toggle-button {
	margin-right:0px;
	padding-left: 200px;
}
.toggle-text-size {
	font-size: 18px;
	margin-top: 5px !important;
	margin-left: 7px;
}
.icon-size {
	height: 35px;
}
.icon-white {
	 filter:invert();
}
.toggle-margin {
	margin-left: 10px;
}
.bgcolor {
	background-color: black;
}
<%-- ���̺� ǥ css --%>
.table {
	border: 1px solid #ccc;
	border-collapse: separate;
}
.write-ch-position {
	position: relative;
	display: inline-block;
}
.write-position {
	float: right;
}
.reply-button {
	background-color : #C8C9C4;
	display: inline-block;
	text-align: center;
}
.border-gray-radius {
	border-top: 1px solid #FFFFFF;
	border-right: 1px solid #FFFFFF;
	border-left: 1px solid #FFFFFF;
	border-bottom: 1px solid #FFFFFF;
	border-radius: 0.5em;
	background-color: #C8C9C4;
}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="font">
<!-- ��۹� -->
<nav class="container bgcolor">
	<div class="font-bold font-size font-color toggle-title">����ī��</div>
	<div class="toggle-button">
		<a href="subs.do">
			<img class="icon-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;" href="login.do?ecode=logout" onclick="logout();">
			<img class="icon-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size" href="mypost.do">���� ����</a>
	</div>
</nav>
<!-- ���̵� �޴� �� -->
<div id="grid">
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
	<!-- ���� ���� -->
	<div class="table-padding">
		<div class="category">
			<!-- é�� -->
			<div class="write-ch-position">
				<form name="banana">
					<select name="ch" onchange="abcd(this.value)">
						<option>é��</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select>
				</form>
			</div>
			<!-- �ۼ� ������ -->
			<div class="write-ch-position write-position">	
				<a href="write.do?subject=${ subject }">
					<img src="image/pencil-line.svg"/>
				</a>
			</div>
		</div>
		<!-- ���̺� ǥ -->
		<div class="font" style="overflow-x: auto;">
			<table border="1" class="table">
				<thead>
				<tr>
					<th>����</th>
					<th>�й�</th>
					<th>Chapter</th>
					<th class="table-title">����</th>
					<th>��ȸ��</th>
				</tr>	
				</thead>
				<q:forEach items="${ rList }" var="t" >
				<tr >
					<td><a href="view_article.do?subject=${ subject }&no=${t.no}">${ t.no }</a></td>
					<td><a href="view_article.do?subject=${ subject }&no=${t.no}">${ t.stid }</a></td>
					<td><a href="view_article.do?subject=${ subject }&no=${t.no}">${ t.ch }</a></td>
					<td >
						<a href="view_article.do?subject=${ subject }&no=${t.no}">${ t.title }</a>
						<q:if test="${ t.ans != null}">
							<div id="reply-button" class="reply-button border-gray-radius font-bold"><img src="image/chat-check-fill.svg"/>${ t.ans }</div>
						</q:if>
						
					</td>
					<td>${ t.views }</td>
				</tr>
				</q:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>
<!-- 
function fun(){
	var basic = document.getElementById('basic');
	basic.style.background="yellow";
	var calc = document.getElementById('calc');
	calc.style.background="white";
	var calc = document.getElementById('linear');
	linear.style.background="white";
	
}
 -->
