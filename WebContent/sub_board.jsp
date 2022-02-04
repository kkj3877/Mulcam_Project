<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style type="text/css">
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
	grid-template-columns: 200px 1fr;
}
#grid #main{
	margin: 25px 25px 25px 25px;	
}
.table {
	border: solid #ccc;
	border-collapse: separate;
}
.write_button {
	align:right;
}
</style>
<script>
function abcd(value){
	location.href="sub_board.jsp?ch="+value;
}
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<h1>제목</h1>
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
	<div id="main">
	<form name="banana">
		<select name="ch" onchange="abcd(this.value)">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
		</select>
	</form>
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
		<tr>
			<td>1</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>2</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>3</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>4</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>5</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>6</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>7</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>8</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>9</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
		<tr>
			<td>10</td>
			<td>201610788</td>
			<td>2</td>
			<td>미적분 1단원 3번문제</td>
			<td>abcdefg hijk lmnot</td>
		</tr>
	</table>
	<div align="right">
		<a href="write.jsp">
			<img src="image/pencil-line.svg"/>
		</a>
	</div>
	</div>
</div>
</body>
</html>





