<%@ page contentType="text/html; charset=utf-8"
	 pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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

<table class="table" border="1" class="table">
<thead>
<tr>
	<td>순번</td>
	<td>챕터</td>
	<td>학번</td>
	<td>제목</td>
	<td>내용</td>
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