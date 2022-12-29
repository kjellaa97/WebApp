<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
// scriptlet , 자바 사용가능
	String gugu =(String)request.getAttribute("data");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>구구단 보기</title>
</head>
<body>
<h3>구단 보기</h3>

<%
	
	out.println(gugu);
	//gugu.jsp에서 2~9까지 링크를 작성하여 클릭하면 해당 구구단이 나오도록 하세요
%>
	
	
<hr>
<div>
	<a href ="guguc?dan=2"> 2 </a> 
	<a href ="guguc?dan=3"> 3 </a>
	<a href ="guguc?dan=4"> 4 </a>
	<a href ="guguc?dan=5"> 5 </a>
	<a href ="guguc?dan=6"> 6 </a>
	<a href ="guguc?dan=7"> 7 </a>
	<a href ="guguc?dan=8"> 8 </a>
	<a href ="guguc?dan=9"> 9 </a>

</div>

</body>
</html>