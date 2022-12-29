<%@page import="com.ezen.web.hello.Member"%>
<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="mem" class="com.ezen.web.hello.Member">
	<jsp:setProperty name="mem" property="num"/>  
</jsp:useBean>
<jsp:useBean id="svc" class="com.ezen.web.hello.MemService" scope="session"/> 
<jsp:setProperty name="svc" property="member" value="<%=mem %>"/> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이용자 상세 보기</title>
</head>
<body>
<%
	Member member = svc.showMem();
%>

<h3> 이용자 상제 정보</h3>

<table>
<tr><</tr>


</table>
</body>
</html>