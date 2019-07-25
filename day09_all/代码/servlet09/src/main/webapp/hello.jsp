<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World!</h1>
	<h1>time=<%=new Date() %></h1>
	<h1>123+567=<%=123+567 %></h1>
	<!-- 在页面上输出10遍 Hello JSP! -->
	<% for(int i=1;i<=10;i++){ %>
			<h1>Hello JSP!</h1>	
	<%	} %>

	<% 
		for(int i=1;i<=10;i++){
			out.write("<h1>Hello JSP!</h1>");	
		} 
	%>
	
</body>
</html>




