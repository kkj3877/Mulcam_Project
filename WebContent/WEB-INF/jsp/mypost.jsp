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
function abcd(value){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	const subject = urlParam.get('subject');
	
	window.location.href="sub_board.do?subject="+subject+"&ch="+value;
}
window.onload = function() {
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	const subject = urlParam.get('subject');
	 
	if( subject == 'Basic' ){
		$(function(){
			$('#basic').css('background-color', 'yellow');
		});
	}
	else if( subject == 'Calc' ){
		$(function(){
			$('#calc').css('background-color', 'yellow');
		});
	}
	else if( subject == 'Linear' ){
		$(function(){
			$('#linear').css('background-color', 'yellow');
		});
	}
}
</script>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400&display=swap');
.font {
	font-family: 'IBM Plex Sans KR', sans-serif;
}
@media (max-width: 890px) {
	.table-container {
		margin-left: 10% !important;
		width: 100%;
	}
	.th{
		width: auto !important;
	}
	td{
		width: 10% !important;
	}
}
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
	.table-container {
		width: 50% !important;
		margin-left: 10% !important;
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
	.table {
		overflow-x: auto !important;
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
h2 {
	font-size: 25px;
}
p {
	margin-top: 10px;
}
th td {
	width: auto;
}
#grid {
	display:grid;
	grid-template-columns: 200px 2fr 0.5fr;
}
#grid #main{
	margin: 25px 25px 25px 25px;	
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
.main{
	padding-top: 20px;
	padding-left: 150px;
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

.write_button {
	align:right;
}
.jm-font{
	font-family:'Jeju Myeongjo', serif;
}
.item_1 {
	position: relative;
	display: inline-block;
}
.button {
	float: right;
}
.ol {
	margin-left: 15px;
	border-right: 1px solid gray;
	height: 500px;
	width:200px;
	padding-top: 20px;
	padding-left: 20px;
	display: flex;
	flex-direction: column;
}
.li-font {
	font-size: 18px;
}
.clicked {
	color: yellow;
}
.color {
	background-color: red;
}
.toggle-size {
	font-size: 15px;
	margin-top: 5px !important;
	margin-left: 7px;
}
.table-container {

	margin-left: 30%;
	width: auto;
}
th {
	text-align: center;
}
.table_1 {
	text-align: center;
	width: 500px;
	background-color: white;
	margin-top: 10px;
	margin-left: 10px;
}
.table_2 {
	text-align: center;
	width: 500px;
	background-color: white;
	margin-top: 10px;
	margin-left: 10px;
}
.table_3 {
	text-align: center;
	width: 500px;
	background-color: white;
	margin-top: 10px;
	margin-left: 10px;
}
.table {
	border-bottom: 1px solid #ccc;
	border-collapse: collapse;
	background-color: white;
	padding: 5px 5px 5px 5px;
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
.li-font {
	font-size: 18px;
}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="font">
<nav class="container bgcolor">
	<div class="font-bold font-size font-color item-title">수학카페</div>
	<div class="item-button">
		<a href="subs.do">
			<img class="image-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout" onclick="abcd();">
			<img class="image-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size" class="toggle-size" href="mypost.do">나의 질문</a>
	</div>
</nav>
<div id="grid">
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
	<div class="table-container">
		<div class="table_1">
			<h2>기초수학 및 연습</h2>
			
			<table class="table">
				<thead>
					<tr>
						<th class="th">
							챕터
						</th>
						<th>
							제목
						</th>
					</tr>
				</thead>
				<q:forEach items="${ rList_Basic }" var="t">
					<tr>
						<td><a href="view_article.do?subject=Basic&no=${ t.no }">${ t.ch }</a></td>
						<td><a href="view_article.do?subject=Basic&no=${ t.no }">${ t.title }</a></td>
					</tr>
				</q:forEach>
			</table>
		</div>
	
		<div class="table_2">
			<h2>미적분학</h2>
			<table class="table">
				<thead>
					<tr>
						<th>
							챕터
						</th>
						<th>
							제목
						</th>
					</tr>
				</thead>
				<q:forEach items="${ rList_Calc }" var="t">
					<tr>
						<td><a href="view_article.do?subject=Calc&no=${ t.no }">${ t.ch }</a></td>
						<td><a href="view_article.do?subject=Calc&no=${ t.no }">${ t.title }</a></td>
					</tr>
				</q:forEach>
			</table>
		</div>
		
		<div class="table_3">
			<h2>선형대수학</h2>
			<table class="table">
				<thead>
					<tr>
						<th class="th">
							챕터
						</th>
						<th>
							제목
						</th>
					</tr>
				</thead>
				<q:forEach items="${ rList_Linear }" var="t">
					<tr>
						<td><a href="view_article.do?subject=Calc&no=${ t.no }">${ t.ch }</a></td>
						<td><a href="view_article.do?subject=Calc&no=${ t.no }">${ t.title }</a></td>
					</tr>
				</q:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>
