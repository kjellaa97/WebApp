
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	boolean deleted = (Boolean) request.getAttribute("deleted");
%>	
{"deleted": <%= deleted%>}
