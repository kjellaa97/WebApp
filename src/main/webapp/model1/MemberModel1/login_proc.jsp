<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	 String id = request.getParameter("id");
	 String pwd = request.getParameter("pwd");
	 
	 boolean pass= false;
	 if(id.equals("smith")&& pwd.equals("1111"))
	 {
		session.setAttribute("login", true);
		pass = true;
	 }
	 
	 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 로그인 처리 결과</title>
</head>
<body>

<script type="text/javascript">
	var pass= <%=pass%>;
	var msg= pass ? "로그인 성공": "로그인 실패";
	alert(msg);
	if(!pass) location.href = "login.jsp";
	else location.href = "main.jsp";
</script>

</body>
</html>