<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%
	//session.invalidate(); 
	session.removeAttribute("login"); //로그인 했다는 사실을 지우는 것
	
	boolean logout = true;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그아웃 결과</title>
</head>
<script>
	var logout = <%=logout%>;
	if(logout){
		alert('정상적으로 로그아웃되었습니다');
		location.href="login.jsp";
		
	}
</script>
<body>

</body>
</html>