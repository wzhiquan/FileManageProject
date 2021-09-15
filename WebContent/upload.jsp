<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/servlet/uploadFileServlet" enctype="multipart/form-data" method="post">
	上传时间<input type="datetime" name="createTime" id="createTime" required="required">
	<br>
	文件1<input type="file" name="file1" id="file1" required="required">
	<br>
	文件2<input type="file" name="file2" id="file2">
	<br>
	<input type="submit" name="commit" id="commit">
	</form>
</body>
</html>