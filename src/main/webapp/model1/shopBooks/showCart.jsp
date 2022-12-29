<%@page import="java.util.List"%>
<%@ page  contentType="text/html; charset= utf-8" pageEncoding="utf-8"%>
<%
	List<String> cart = (List<String>)session.getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
	main {width: fit-content; margin:1em auto;}
	.container{padding:1em; border: 1px solid black;}
</style>
<title> ...</title>
</head>
<body>
<main>
<h3>장바구니 보기</h3>
<div class = "contianer">
<%
	if(cart !=null)
	{	
		for(int i=0;i<cart.size();i++)
		{%>
			<div><%=i+1%>. <%=cart.get(i) %></div>
	<% }
	}else{%>
		<%="장바구니가 비어 있습니다" %>
<% 	}
%>
</div>
<p>
<a href="cartEmpty.jsp?">장바구니 비우기</a>
</main>
</body>
</html>