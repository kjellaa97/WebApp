<%@page import="com.ezen.web.hello.Member"%>
<%@page import="java.util.List"%>
<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="svc" class= "com.ezen.web.hello.MemService" scope= "session"> </jsp:useBean> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 이용자 목록 화면</title>
<style type= "text/css">
	main{width: fit-content; margin:0 auto;}
	table{ border: 1px solid black; border-spacing:1px; padding: 1em; 
			border-collapse: collapse; padding: 3em; margin: 1em auto; }	
	th{background-color: #eee; border-bottom: 2px solid black; }
	a{ text-decoration: none; color: purple}
</style>
</head>

<body>
<main>
<h3> 이용자 목록 </h3>
<table>
<tr><th>번호</th><th>이름</th><th>생일</th><th>이메일</th></tr>
<%
	List<Member> list =svc.showList();
	for(int i=0; i< list.size(); i++)
	{
		Member m= list.get(i);
%>
	<tr>
		<td><%= m.getNum() %></td>
		<td><a href= "memDetail.jsp?num=<%= m.getNum()%>"><%= m.getName() %></a></td>
		<td><%= m.getDob() %></td>
		<td><%= m.getEmail() %></td>
	</tr>
<% }
%>


</table>
<div><a href= "memJoin.jsp"> [이용자 추가하기]</a></div>
 </main>`
</body>
</html>