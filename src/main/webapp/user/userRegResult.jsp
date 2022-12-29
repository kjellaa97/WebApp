<%@ page language="java" contentType="application/json; charset=utf-8" pageEncoding="utf-8"%>
<%
	boolean result = (Boolean)request.getAttribute("registered");
%>
{"registered": <%= result %>}