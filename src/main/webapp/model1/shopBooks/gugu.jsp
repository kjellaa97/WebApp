<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.ezen.web.hello.GuguService"%>
<%@ page  contentType="text/html; charset= utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="svc" class= "com.ezen.web.hello.GuguService" scope="page">
	<%-- svc.setRequest(request); --%>
	<jsp:setProperty name ="svc" property="request" value ="<%=request %>"></jsp:setProperty>
</jsp:useBean>
<%-- 
액션 태그 useBean 
scope = "page"를 함으로써 svc라는 객체는 이 페이지안에서만 쓸수 있게 됨, 
이렇게 하면 우리가 new 안해도, 시스템안에서 GuguService해주게 되어있음
 --%>
 <%
	String gString = svc.createGugu();

 %>
<%--
	GuguService gs = new GuguService(); 		
	String gString = gs.createGugu(request);
	JSONObject jsObj = new JSONObject();
	jsObj.put("content", gString);
	out.print(jsObj.toJSONString());
	out.flush();
--%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 구구단 학습기 </title>
<style type = "text/css">
	main{width: fit-content; margin: 0 auto;
		border: 1px solid black; padding: 1em;
		 }
	
</style>
</head>
<body>
<main>
	<h3>구구단 보기</h3>
	<div id = "contents"><%= gString %> </div>
</main>
</body>
</html> 