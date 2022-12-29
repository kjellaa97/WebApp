<%@page import="org.json.simple.JSONObject"%>
<%@ page contentType="application/json; charset=utf-8" pageEncoding="utf-8"%>

<jsp:useBean id="board" class="com.ezen.web.hello.Board">
   <jsp:setProperty name="board" property="num" param="num"/>
</jsp:useBean>
<jsp:useBean id="board_svc" class="com.ezen.web.hello.BoardService" scope="session"/>

<%
   boolean deleted = board_svc.delete(board);
   JSONObject jsObj = new JSONObject();
   jsObj.put("deleted", deleted);
%>
<%=jsObj.toJSONString()%>