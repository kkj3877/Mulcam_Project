<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.a {
	width:200px;
	height:150px;
	text-align:center;
	position:absolute;
	top:40%;
	left:50%;
	margin-left:-100px;
	margin-top:-75px;
}
#b {
	border:1px solid;
	width:150px;
	margin:0 auto;
	text-decoration: none;
}
a {
	color:black;
	text-decoration:none;
}
</style>
</head>
<body>
<div class="a">
���� ���
	<div id="b">
	<a href="sub_board.jsp">���ʼ��� �� ����</a>
	</div>
	<div id="b">
	<a href="sub_board.do?subject=Calc">��������</a>
	</div>
	<div id="b">
	<a href="sub_board.do?subject=Linear">�������</a>
	</div>
</div>
</body>
</html>