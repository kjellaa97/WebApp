<%@ page language="java" contentType="application/json; charset=utf-8" pageEncoding="utf-8" %>
<%
	boolean updated = (Boolean) request.getAttribute("updated");
	
%>	
{"updated": <%=updated%>}


