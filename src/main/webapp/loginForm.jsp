<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String id = (String)request.getAttribute("id");
	String pwd = (String)request.getAttribute("pwd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 화면</title>
<style>
		label{display:inline-block;width:3em; text-align: right; 
		margin-right:1em; }
		input { width: 5em; }
		form {width:fit-content; padding:0.5em; margin:0 auto; 
		border:1px solid black;}
		button { margin: 5px; }
		div:last-child { text-align: center;}
</style>
</head>
<body>
<form action = 'loginc' method='post'> 
<div><input type = 'hidden' name ='cmd' value='login'></div> 	<% // hidden에 login이 들어감. cmd가 login으로 전달 됨--> 로그인을 하라는 요청임%>
<div><label>아이디</label>
	<input type = 'text' name ="id"></div>
<div><label>비밀번호</label>
	<div>비밀번호<input type = "password" name = "pwd"></div>
<div><button>로그인</button></div>	
 
</form>
</body>
</html>

