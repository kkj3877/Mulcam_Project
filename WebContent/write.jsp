<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
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
.item{
	justify-self:center;
	padding-left:-10px;
}
.title{
	width:500px;
}
.content{
	width: 528px;
}
</style>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h1>����</h1>
<div id="grid">
	<ol>
		<li>
		<a href="sub_board.do?subject=Basic">���ʼ��� �� ����</a>
		</li>
		<li>
		<a href="sub_board.do?subject=Calc">��������</a>
		</li>
		<li>
		<a href="sub_board.do?subject=Linear">�������</a>
		</li>
	</ol>

	<div id="main" class="item">
		<form method="POST" action="question.do" enctype="multipart/form-data">
		<input type="hidden" name="subject" value="${ subject }"/>
			<div>
				����<input class="title" type="text" name="title"/>
			</div>
			<br/>
			<div>
				<select name="ch">
					<option>é��</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
				</select>
			</div>			
			<div>
				<textarea class="content" name="content" rows="10">���������Ϸ��ϼ���</textarea>
			</div>
			<div>
				<input type="file" name="fsn_q"/>
			</div>
			<div>
				<input type="submit"/>
			</div>		
		</form>
	</div>
</div>
</body>
</html>