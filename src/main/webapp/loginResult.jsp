<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% 
	String msg = (String)request.getAttribute("msg"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 결과</title>
</head>
<body>
<h3>
<%
 out.println(msg);
%>
</h3>


</body>
</html>