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
a {
	color: black;
	text-decoration: none;
}
h1 {
	text-align: center;
	padding: 20px;
	border-bottom: 1px solid gray;
}
p {
	margin-top: 10px;
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
.table {
	border: 1px solid #ccc;
	border-collapse: separate;
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
	<div class="main">
		<div class="category">
			<div class="item_1">
				<form name="banana">
					<select name="ch" onchange="abcd(this.value)">
						<option>챕터</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select>
				</form>
			</div>
			<div class="item_1 button">	
				<a href="write.do?subject=${ subject }">
					<img src="image/pencil-line.svg"/>
				</a>
			</div>
		</div>
		
		${ rList }
		
		<div class="font" style="overflow-x: auto;">
			<table border="1" class="table">
				<thead>
				<tr>
					<th>순번</th>
					<th>학번</th>
					<th>Chapter</th>
					<th class="table-title">제목</th>
				</tr>	
				</thead>
				<q:forEach items="${ rList }" var="t" >
				<tr >
					<td><a href="view_article.do?subject=${ subject }&no=${t.no}">${ t.no }</a></td>
					<td><a href="view_article.do?subject=${ subject }&no=${t.no}">${ t.stid }</a></td>
					<td><a href="view_article.do?subject=${ subject }&no=${t.no}">${ t.ch }</a></td>
					<td ><a href="view_article.do?subject=${ subject }&no=${t.no}">${ t.title }</a></td>
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
