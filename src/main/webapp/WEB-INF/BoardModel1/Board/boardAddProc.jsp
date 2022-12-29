<%@page import="org.json.simple.JSONObject"%>
<%@ page contentType="application/json; charset=utf-8" pageEncoding="utf-8"%>

<jsp:useBean id="board_svc" class="com.ezen.web.hello.BoardService" scope="session"/>
<jsp:setProperty name="board_svc" property="session" value="<%=session%>" />

<jsp:useBean id="board" class="com.ezen.web.hello.Board">
	<jsp:setProperty name="board" property="*"/> 
</jsp:useBean>

<%
	boolean added = board_svc.add(board);   // 파일에 저장
	JSONObject jsObj = new JSONObject();
	jsObj.put("added", added);
%>
<%=jsObj.toJSONString()%>