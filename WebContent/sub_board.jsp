<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
.table {
	border: solid #ccc;
	border-collapse: separate;
	
}
</style>
</head>
<body>
<div>
${ rList }

<table border="1" class="table">
<thead>
<tr>
	<th>순번</th>
	<th>Chap</th>
	<th>학번</th>
	<th>제목</th>
	<th>내용</th>
</tr>	
</thead>
	
	<q:forEach items="${ rList }" var="t">
	<tr>
		<td>${t.no}</td>
		<td>${t.ch}</td>
		<td>${t.stid}</td>
		<td>${t.title}</td>
		<td>${t.content}</td>
	</tr>
	</q:forEach>
	
</table>
	<a href="write.jsp">
		<img src="image/pencil-line.svg"/>
	</a>
</div>
</body>
</html>