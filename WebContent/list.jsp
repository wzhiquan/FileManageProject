<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
	<c:forEach var="item" items="${map}" varStatus="index">
		<c:url var="downurl" value="/servlet/downLoadFileServlet">
			<c:param name="filePath" value="${item.key}"></c:param>
		</c:url>
		${index.index}: ${item.value}<a href="${downurl}">下载</a>
	<br/>
	</c:forEach>
</body>
</html>