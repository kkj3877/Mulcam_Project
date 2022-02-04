<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
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
.item{

	justify-self:center;
	padding-left:-10px;
}
</style>
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
		<form method="GET" action="sub_board.jsp" enctype="multipart/form-data">
			<div>
				<input type="text" name="title" value="����"/>
			</div>
			<br/>
			<div>
				<select name="ch">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
				</select>
			</div>			
			<div>
				<textarea name="content" rows="10">���������Ϸ��ϼ���</textarea>
			</div>
			<div>
				<input type="file" name="fsn_q"/>
			</div>
			<div>
				<input type="submit"/>
				<a href="cal.jsp">�ۼ�</a>
			</div>		
		</form>
	</div>
</div>
</body>
</html>