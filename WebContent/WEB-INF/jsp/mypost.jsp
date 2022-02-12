<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
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
		margin-left: 4% !important;
	}
	
}
@media (max-width: 690px) {
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
<%-- a 태그 검정색 고정 --%>
a {
	color: black;
	text-decoration: none;
}
<%-- 좌측 메뉴 과목 css --%>
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
.li-font {
	font-size: 18px;
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
<%-- 좌측 메뉴 바, 메인 영역 grid css --%>
#grid {
	display:grid;
	grid-template-columns: 200px 2fr 0.5fr;
}
#grid #main{
	margin: 25px 25px 25px 25px;	
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
.item-title {
	margin-left:0px;
	padding-right: 200px;
}
.item-button {
	margin-right:0px;
	padding-left: 200px;
}
.toggle-size {
	font-size: 15px;
	margin-top: 5px !important;
	margin-left: 7px;
}
.toggle-margin {
	margin-left: 10px;
}
.toggle-text-size {
	font-size: 18px;
}
.icon-white {
	 filter:invert();
}
.icon-size {
	height: 35px;
}
.bgcolor {
	background-color: black;
}
<%-- 테이블 css --%>
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
.reply-button {
	display: inline-block ;
	text-align: center;
}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="font">
<!-- 토글바 -->
<nav class="container bgcolor">
	<div class="font-bold font-size font-color item-title">수학카페</div>
	<div class="item-button">
		<a href="subs.do">
			<img class="icon-size icon-white" src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout">
			<img class="icon-size icon-white" src="image/logout-box-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="status.do">
			<img class="icon-size icon-white" src="image/settings-2-line.svg"/>
		</a>
		<a class="toggle-margin icon-white font-bold toggle-text-size" class="toggle-size" href="mypost.do">나의 질문</a>
	</div>
</nav>
<!-- 사이드 메뉴 바 -->
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
	<!-- 메인 테이블 영역 -->
	<div class="table-container">
		<div class="table_1">
			<h2>기초수학 및 연습</h2>
			<table class="table">
				<thead>
					<tr>
						<th>
							챕터
						</th>
						<th>
							제목
						</th>
						<th>
							조회수
						</th>
					</tr>
				</thead>
				<q:forEach items="${ rList_Basic }" var="t">
					<tr>
						<td><a href="view_article.do?subject=Basic&no=${ t.no }">${ t.ch }</a></td>
						<td><a href="view_article.do?subject=Basic&no=${ t.no }">
							<q:if test="${ t.ans != null}">
								<div id="reply-button" class="reply-button font-bold"><img src="image/chat-check-fill.svg"/></div>
							</q:if>
							${ t.title }</a>
						</td>
						<td>${ t.views }</td>
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
						<th>
							조회수
						</th>
					</tr>
				</thead>
				<q:forEach items="${ rList_Calc }" var="t">
					<tr>
						<td><a href="view_article.do?subject=Calc&no=${ t.no }">${ t.ch }</a></td>
						<td><a href="view_article.do?subject=Calc&no=${ t.no }">
							<q:if test="${ t.ans != null}">
								<div id="reply-button" class="reply-button font-bold"><img src="image/chat-check-fill.svg"/></div>
							</q:if>
							${ t.title }</a>
						</td>
						<td>${ t.views }</td>
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
						<th>
							조회수
						</th>
					</tr>
				</thead>
				<q:forEach items="${ rList_Linear }" var="t">
					<tr>
						<td><a href="view_article.do?subject=Linear&no=${ t.no }">${ t.ch }</a></td>
						<td><a href="view_article.do?subject=Linear&no=${ t.no }">
							<q:if test="${ t.ans != null}">
								<div id="reply-button" class="reply-button font-bold"><img src="image/chat-check-fill.svg"/></div>
							</q:if>
							${ t.title }</a>
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
