<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script>
function abcd(value){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	const subject = urlParam.get('subject');
	alert(subject);
	
	window.location.href="sub_board.do?subject="+subject+"&ch="+value;
}
</script>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejuhallasan.css);

a {
	color: black;
	text-decoration: none;
}
ol {
	border-right: 1px solid gray;
	width:200px;
	padding-top: 20px;
	padding-left: 20px;
}
h1 {
	text-align: center;
	padding: 20px;
	border-bottom: 1px solid gray;
}
#grid {
	display:grid;
	grid-template-columns: 200px 2fr 1fr;
}
#grid #main{
	margin: 25px 25px 25px 25px;	
}
.main{
	padding-top: 20px;
	padding-left: 60px;
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
.table {
	border: solid #ccc;
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
</style>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="container">
	<div class="item-title">로그인</div>
	<div class="item-button">
		<a href="subs.do">
			<img src="image/arrow-left-line.svg"/>
		</a>
	</div>
</nav>
<div id="grid">
	<ol>
		<li>
		<a href="sub_board.do?subject=Basic">기초수학 및 연습</a>
		</li>
		<li>
		<a href="sub_board.do?subject=Calc">미적분학</a>
		</li>
		<li>
		<a href="sub_board.do?subject=Linear">선형대수</a>
		</li>
	</ol>
	<div class="main">
		<div class="category">
			<div class="item_1">
				<form name="banana">
					<select name="ch" onchange="abcd(this.value)">
						<option>Chapter</option>
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
		
		
		<table border="1" class="table">
			<thead>
			<tr>
				<th>순번</th>
				<th>학번</th>
				<th>Chapter</th>
				<th>제목</th>
				<th>내용</th>
			</tr>	
			</thead>
			<q:forEach items="${ rList }" var="t">
			<tr>
				<td>${ t.no }</td>
				<td>${ t.stid }</td>
				<td>${ t.ch }</td>
				<td>${ t.title }</td>
				<td>${ t.content }</td>
			</tr>
			</q:forEach>
		</table>
	
	</div>
</div>
</body>
</html>
