<%@page import="java.util.List"%>
<%@page import="com.ezen.web.hello.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8>"  pageEncoding="utf-8"%>
<%
	List<User> list =  (List<User>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style type="text/css">
	#container{border: 1px dotted blac; width: fit-content; margin: 0 auto;}
</style>
</head>
<body>

<h1> 이용자 상세 정보 </h1>
<table> 
<tr><th>번호</th><th>이름</th><th>전화</th><th>메일</th></tr>
<%
	for(int i=0;i<list.size();i++)
	{
		User u = list.get(i);
		int num = u.getNum();
		String name = u.getName();
		String phone = u.getPhone();
		String email = u.getEmail();
		%>
		<tr>
			<td><%= num %> </td> 
			<td><%= name %></td>	
			<td><%= phone %></td>
			<td><%= email %></td>
			
		</tr>
	<%	}
	%>	
</table>
<p>
[<a href = "usercontrol?cmd=list">이용자 목록 보기</a>]


</body>
</html>