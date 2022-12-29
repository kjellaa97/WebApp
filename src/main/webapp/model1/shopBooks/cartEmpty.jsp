<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	session.invalidate(); // session을 무효화 시키는 기능, session내 들어있는 모든 정보 지우기
	response.sendRedirect("showCart.jsp"); //비우고 응답을 해야하니까 response.하고 showCart보내주기
%>

