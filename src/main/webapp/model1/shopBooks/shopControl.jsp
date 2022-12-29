<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%>
<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String book = request.getParameter("book");

	List<String> cart = (List<String>)session.getAttribute("cart");
	if(cart ==null){
		cart = new ArrayList<String>();
		session.setAttribute("cart", cart);
	}
	boolean added = cart.add(book);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> ...</title>
</head>
<body>
<%=added? "장바구니 저장": "장바구니 저장 실패" %>
<hr>
<a href= "javaBook.jsp">Java Book </a>
</body>
</html>