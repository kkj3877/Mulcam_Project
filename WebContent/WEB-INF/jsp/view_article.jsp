<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script>
function abcd(value){
	const urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParam = url.searchParams;
	const subject = urlParam.get('subject');
	
	window.location.href="sub_board.do?subject="+subject;
}
</script>
<style type="text/css">
@media (max-width: 800px) {
	.ol {
		display:none !important;
	}
	.wrap-title {
		width: 523 !important; 
		height: 100px;
		margin-left: 50px !important;	
		margin-top: 50px;
		justify-self: center;
		border: 1px solid;
		border-radius: 0.7em;
	}
	.wrap-content {
		width: 500px !important;
		height:400px;
		margin-top: 50px;
		justify-self: center;
		border: 1px solid;
		border-radius: 0.7em;
	}
}
@media (max-width: 566px) {
	.ol {
		display:none !important;
	}
	.wrap-title {
		width: auto !important; 
		height: 100px;
		margin-right: 50px !important;	
		margin-left: 50px !important;	
		margin-top: 50px;
		justify-self: center;
		border: 1px solid;
		border-radius: 0.7em;
	}
	.wrap-content {
		width: auto !important;
		height:400px;
		margin-right: -15px !important;
		margin-top: 50px;
		justify-self: center;
		border: 1px solid;
		border-radius: 0.7em;
	}
}
a {
	color: black;
	text-decoration: none;
}
.ol {
	border-right: 1px solid gray;
	height: 500px;
	width:200px;
	padding-top: 20px;
	padding-left: 20px;
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
.wrap-title {
	width: 500px;
	height: 100px;
	margin-left: 180px;
	margin-top: 50px;
	justify-self: center;
	border: 1px solid;
	border-radius: 0.7em;
}
.wrap-content {
	width:500px;
	height:200px;
	margin-top: 50px;
	margin-left: -15px;
	justify-self: center;
	border: 1px solid;
	border-radius: 0.7em;
}
.article {
	width: 30px;
	border-bottom: 1px solid black;
}
.article-margin {
	margin-left: 10px;
	margin-top: 7px;
	margin-bottom: 8px;
}
.wrap-image {
	margin-top: 10px;
	width: 500px;
	height: 300px;
	border: 1px solid; 
}
.image-box {
	width:100%;
	height:99%;
	object-fit:cover;
}
</style>
</head>
<body>
<nav class="container">
	<div class="item-title">�α���</div>
	<div class="item-button">
		<a onclick="abcd();">
			<img src="image/arrow-left-line.svg"/>
		</a>
		<a style="padding-left:17px;"href="login.do?ecode=logout" onclick="abcd();">
			<img src="image/logout-box-line.svg"/>
		</a>
	</div>
</nav>
<div class="grid">
	<div class="wrap-ol col-xs-0.1 col-sm-1 col-md-3">
		<div class="ol">
			<li>
				<a href="sub_board.do?subject=Basic">���ʼ��� �� ����</a>
			</li>
			<li>
				<a href="sub_board.do?subject=Calc">��������</a>
			</li>
			<li>
				<a href="sub_board.do?subject=Linear">�������</a>
			</li>
		</div>
	</div>
	<div class="wrap-title col-xs-11.9 col-sm-11 col-md-9">
		<div class="article-margin">${ subject } > ${ article.ch }</div>
		<div class="article article-margin">����</div>
		<div class="article-margin">${ article.title }</div>
		
		<div class="wrap-content">
			<div class="content content-margin">����</div>
			<div>${ article.content }</div>
		</div>
		
		<div class="wrap-image">
			<img class="image-box" src="image.jsp?fname=${fsn_q}"/>
		</div>
	</div>
	
</div>

</body>
</html>
<!-- 
<div class="article article-margin">����</div>
		<div class="inner-margin">������ ��Ʈ �Ǵ� ĳ�����̵� ��Ÿ�� ��Ʈ(Cascading Style Sheets, CSS)�� ��ũ�� �� ���� ǥ�õǴ� ����� ����ϴ� ��Ÿ�� ���(style sheet language)��[1], HTML�� XHTML�� �ַ� ���̸�, XML������ ����� �� �ִ�. W3C�� ǥ���̸�, ���̾ƿ��� ��Ÿ���� ������ ���� �������� ����.</div>
	
 -->