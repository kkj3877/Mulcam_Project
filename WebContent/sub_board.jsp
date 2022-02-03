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
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th style="width:50px;"><span>번호</span></th>
				<th><span class="head_font">내용</span></th>
				<th style="width:150px;"><span>글쓴이</span></th>
			</tr>
		</thead>
		
		<tbody>
			<q:forEach items="${rList}" var="t">
				<tr>
					<td style="text-align: center;">
						<span class="contents_font">${t.no}</span>
						[<a href="del2.do?no=${t.no}">X</a>]
					</td>
					<td><span class="contents_font">${t.title}</span></td>
					<td><span class="contents_font">${t.stid}</span></td>
				</tr>
			</q:forEach>
		</tbody>
	</table>
	<form method="POST" action="add2.do">
		<div class="form-group">
			<label class="font" for="content">내용</label>
			<textarea class="form-control font" id="content" name="content" rows="4"></textarea>
		</div>
		
		<div class="form-group">
			<label class="font" for="author">작성자</label>
			<input type="text" class="form-control font" id="author" name="author"/>
		</div>
		
		<div class="form-group has-primary">
			<input class="form-control" type="submit"/>
		</div>
	</form>
</div>
</body>
</html>