<%@page import="org.json.simple.JSONObject"%>
<%@ page contentType="application/json; charset=utf-8" pageEncoding="utf-8"%>

<jsp:useBean id="login_svc" class="com.ezen.web.user.BoardUserService" scope="session"/>
<jsp:setProperty name="login_svc" property="session" value="<%=session%>" />
<%
	login_svc.setSession(session);
%>
<jsp:useBean id="user" class="com.ezen.web.user.BoardUser">
	<jsp:setProperty name="user" property="*" />
</jsp:useBean>

<%
	boolean ok = login_svc.login(user);
	JSONObject jsObj = new JSONObject();
	jsObj.put("login", ok);
	jsObj.put("url", (String)session.getAttribute("url")); //boardController에서 set해놓은 url갖고오기 
	out.print(jsObj.toJSONString());
	out.flush();
%>