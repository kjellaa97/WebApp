<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	Object obj = session.getAttribute("login");
	String msg = null;
	boolean pass = true;
	if(obj==null) //response.sendRedirect("login.jsp");
	{
		pass = false;
		msg = "회원으로 로그인해야 사용할 수 있습니다"; 
	}
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 메인 컨텐츠 페이지 </title>
<style type = "text/css">
	main { width: 300px; margin:0 auto; background-color: blue;}
</style>
<script>
	var pass = <%=pass%>;
	var msg = '<%=msg%>';
	if(!pass){
		alert(msg);
		location.href="login.jsp";
	}
</script>
</head>
<body>
<h1> 회원님 환영합니다 </h1>
<p>
<a href= "logout.jsp">로그아웃</a>

</body>
</html>