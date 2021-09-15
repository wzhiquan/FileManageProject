<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
	<div>
	<a href="/FileManageProject/upload.jsp">上传</a>
	</div>
	
	<br>
	
    <div>
    <jsp:include page="/servlet/queryFileListServlet"></jsp:include>
    </div>
</body>
</html>