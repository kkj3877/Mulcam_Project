<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
.font, .head_font, table thead {
	font-family:'Nanum Gothic';
	font-size: 26px;
	text-align: center;
}
table tbody {
	font-family:'Nanum Gothic';
	font-size: 14px;
}
</style>
</head>
<body>
<div class="container">
	<br/><span class="font">Student_T</span>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th><span>stid</span></th>
				<th><span>pw</span></th>
				<th><span>name</span></th>
				<th><span>mail</span></th>
			</tr>
		</thead>
		<tbody>
			<q:forEach items="${Students}" var="t">
				<tr>
					<td style="text-align: center;">
						<span class="contents_font">${t.stid}</span>
						[<a href="del.do?no=201510810">X</a>]
					</td>
					<td><span class="contents_font">${t.pw}</span></td>
					<td><span class="contents_font">${t.name}</span></td>
					<td><span class="contents_font">${t.mail}</span></td>
				</tr>
			</q:forEach>
		</tbody>
	</table>
	
	<br/><span class="font">Basic_T</span>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th><span>no</span></th>
				<th><span>stid</span></th>
				<th><span>ch</span></th>
				<th><span>title</span></th>
				<th><span>content</span></th>
				<th><span>ans</span></th>
				<th><span>fsn_q</span></th>
				<th><span>fsn_a</span></th>
			</tr>
		</thead>
		<tbody>
			<q:forEach items="${Basic}" var="t">
				<tr>
					<td><span class="contents_font">${t.no}</span></td>
					<td><span class="contents_font">${t.stid}</span></td>
					<td><span class="contents_font">${t.ch}</span></td>
					<td><span class="contents_font">${t.title}</span></td>
					<td><span class="contents_font">${t.content}</span></td>
					<td><span class="contents_font">${t.ans}</span></td>
					<td><span class="contents_font">${t.fsn_q}</span></td>
					<td><span class="contents_font">${t.fsn_a}</span></td>
				</tr>
			</q:forEach>
		</tbody>
	</table>
	
	<br/><span class="font">Calc_T</span>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th><span>no</span></th>
				<th><span>stid</span></th>
				<th><span>ch</span></th>
				<th><span>title</span></th>
				<th><span>content</span></th>
				<th><span>ans</span></th>
				<th><span>fsn_q</span></th>
				<th><span>fsn_a</span></th>
			</tr>
		</thead>
		<tbody>
			<q:forEach items="${Calc}" var="t">
				<tr>
					<td><span class="contents_font">${t.no}</span></td>
					<td><span class="contents_font">${t.stid}</span></td>
					<td><span class="contents_font">${t.ch}</span></td>
					<td><span class="contents_font">${t.title}</span></td>
					<td><span class="contents_font">${t.content}</span></td>
					<td><span class="contents_font">${t.ans}</span></td>
					<td><span class="contents_font">${t.fsn_q}</span></td>
					<td><span class="contents_font">${t.fsn_a}</span></td>
				</tr>
			</q:forEach>
		</tbody>
	</table>
	
	<br/><span class="font">Linear_T</span>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th><span>no</span></th>
				<th><span>stid</span></th>
				<th><span>ch</span></th>
				<th><span>title</span></th>
				<th><span>content</span></th>
				<th><span>ans</span></th>
				<th><span>fsn_q</span></th>
				<th><span>fsn_a</span></th>
			</tr>
		</thead>
		<tbody>
			<q:forEach items="${Linear}" var="t">
				<tr>
					<td><span class="contents_font">${t.no}</span></td>
					<td><span class="contents_font">${t.stid}</span></td>
					<td><span class="contents_font">${t.ch}</span></td>
					<td><span class="contents_font">${t.title}</span></td>
					<td><span class="contents_font">${t.content}</span></td>
					<td><span class="contents_font">${t.ans}</span></td>
					<td><span class="contents_font">${t.fsn_q}</span></td>
					<td><span class="contents_font">${t.fsn_a}</span></td>
				</tr>
			</q:forEach>
		</tbody>
	</table>
	
</div>
</body>
</html>