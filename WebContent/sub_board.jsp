<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
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

<table border="1" class="table">
<thead>
<tr>
	<th>순번</th>
	<th>작성자</th>
	<th>질문</th>
</tr>	
</thead>
	<tr>
	<q:forEach items="${ rList }" var="t">
		<td>t.no</td>
		<td>t.ch</td>
		<td>t.stid</td>
		<td>t.title</td>
		<td>t.content</td>
	</q:forEach>
	</tr>
</table>
	<a href="write.jsp">
		<img src="image/pencil-line.svg"/>
	</a>
</div>
</body>
</html>