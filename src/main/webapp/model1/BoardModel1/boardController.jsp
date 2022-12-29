<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String uid = (String)session.getAttribute("uid");
	if(uid==null) {
		session.setAttribute("url", request.getRequestURL().toString()); //session 에 이용자가 원하는 페이지 저장해놓기 
		response.sendRedirect("../login/userController.jsp?cmd=loginform"); //..이 있으면 현재 이 boardController보다 한단계 위 폴더를 말함
		return;
	}
	
	String cmd = request.getParameter("cmd");
	if (cmd==null) cmd="LIST";
	
	switch(cmd)
	{
	case "ADD_FORM":
%>
		<jsp:forward page="boardAddForm.jsp"/>
<%
		break;
	case "ADD":
%>
		<jsp:forward page="boardAddProc.jsp"/>
<%	
		break;
	case "LIST":
%>
		<jsp:forward page="boardList.jsp"/>
<%		break;
	case "READ":
%>
		<jsp:forward page="boardRead.jsp"/>
<%		break;
	case "EDIT":
%>
		<jsp:forward page="boardEdit.jsp"/>
<%		break;
	case "UPDATE":
%>
		<jsp:forward page="boardUpdate.jsp"/>
<%		break;
	case "FIND":
%>
		<jsp:forward page="boardRead.jsp"/>
<%		break;
	case "DELETE":
%>
		<jsp:forward page="boardDelete.jsp"/>
<%		break;
	}
%>