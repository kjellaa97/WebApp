<%@page import="org.json.simple.JSONObject"%>
<%@ page contentType="application/json; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="board" class="com.ezen.web.hello.Board"> 
	<jsp:setProperty name="board" property="*"/>
</jsp:useBean>
<jsp:useBean id="board_svc" class="com.ezen.web.hello.BoardService" scope="session"/>
<%
	boolean updated = board_svc.update(board); 
	JSONObject jsObj = new JSONObject();
	jsObj.put("updated", updated);
	out.print(jsObj.toJSONString());
	out.flush();
%>