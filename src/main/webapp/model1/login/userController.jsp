<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String cmd = request.getParameter("cmd");
	if (cmd==null) cmd = "loginform";
	
	if(cmd.equals("loginform")){ %>
		<jsp:forward page="loginForm.jsp"/>
<%	}else if(cmd.equals("login")){ %>
		<jsp:forward page="loginProc.jsp"/>
<%	}
%>