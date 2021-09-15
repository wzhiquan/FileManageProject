<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form action="/servlet/searchServlet">
   
   <table border="1">
   <tr>
    <c:forEach var="item" items="${dicttag}" varStatus="index">
		<td>${item.value}</td>
	</c:forEach>
   </tr>
   
    <c:forEach var="item1" items="${result}" varStatus="index1">
    <%
for (int i = 0; i < result.size()/4; i++) {
%>
    <tr>
		    <c:forEach var="dict" items="${dicttag}" varStatus="index2">
		    
		    
				<td><%=  %>${item1.dict.key}</td>
			</c:forEach>
		</tr>
	</c:forEach>
   
   
   
   </table>
   </form>
</body>
</html>