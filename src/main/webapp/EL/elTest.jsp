<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("data1", "Page Scope Data"); //page내에서 공유되는 객체
	request.setAttribute("data2", "Page Scope Data");	//request내에서 공유되는 객체는 request
	session.setAttribute("data3", "Session Scope Data");
	application.setAttribute("data4", "Application Scope Data");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> ...</title>
</head>
<body>
<%
	String data1 = (String)pageContext.getAttribute("data1"); //Object형이 나오니까 String형태로 변환시킨다
	out.println("data1:" + data1);
%>
<p>
${data1} <br>
${data3} <br>
</body>
</html>