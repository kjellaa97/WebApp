<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ezen.web.hello.Member"%>
<%@page import="java.util.List"%>
<%@ page  contentType="application/json; charset=utf-8" pageEncoding="utf-8"%>

<jsp:useBean id="mem" class="com.ezen.web.hello.Member">
	<jsp:setProperty name="mem" property="*"/> 
</jsp:useBean>		
<jsp:useBean id="svc" class="com.ezen.web.hello.MemService" scope="session"/> 
<jsp:setProperty name="svc" property="member" value="<%=mem %>"/> 
<%
	boolean saved= svc.saveMem();
	
	JSONObject jsObj = new JSONObject();
	jsObj.put("saved", saved);
	out.println(jsObj.toJSONString());
	out.flush();
%>

<%-- 위의 useBean에 우리가 setProperty 를 하는 이유는, 브라우저에서 넘어온 데이터, 즉 회원들의 정보를 
파라미터로 받아와서, 멤버객체에 넣어주고, 그걸  MemService에 전달하기 위해 필요했던 거임 
--%>