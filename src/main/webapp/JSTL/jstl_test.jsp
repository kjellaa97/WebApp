<%@page import="java.util.*"%>
<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>JSTL 테스트</title>
</head>
<body>
	<p>
	<c:set var= "name" value= "JSTL Test" scope="page" />
	${name}
	</p>
	<p> <!--  동적인 값일 경우 expression사용 -->
		<c:set var="msg" value= "<%=\"값에 Expression 사용하기\" %>"/>
		${msg}
	</p>
	
<%
	List<String> list = new ArrayList<>();
	list.add("Joy");
	list.add("Chu");
	list.add("Ella");
	list.add("Dennis");
	
	//이제 얘네를 영역에 저장해야함	c:set 활용
%>	
<c:set var="list" value="<%=list %>" /> 

<!-- 자바 버전 포루프 -->
<%
	for(int i=0; i<list.size(); i++)
	{
		String s = list.get(i);
		out.println(s+ "</br>"); // 한줄씩 출력하기 또는 <p>태그 활용
	}

	//java 버전- Map을 활용한 포루프
	Map<String, String> map = new HashMap<>();
	map.put("하나", "Joy");
	map.put("둘", "Chu");
	map.put("셋", "Ella");
	map.put("넷", "Dennis");
	
	//String name= map.get("셋"); //map은 index넘버가 아닌 key를 줘야지 value를 구할 수 있음 
	
	Set<String> keyset = map.keySet();	//따라서 로프를 돌릴려면 key를 받아서 반복적으로 줘야함 
	Iterator<String> it = keyset.iterator();
	while(it.hasNext())
	{
		String key = it.next();
		String name = map.get(key);
	}
%> 
<!-- Map - 태그를 이용한 포루프  -->
<p>
<c:set var="map" value="<%=map %>"/> 
<ul>
<c:forEach var="item" items="${map}"> <!-- "item"안에는 key&value가 들어있음, 즉 객체임 -->

	<li>${map[${item.key}]}</li> 
	
</c:forEach>
</ul>

<!-- 태그를 이용한 포르푸 -->
<p>
<ol>
<c:forEach var="item" items="${list}" varStatus="status"> <!-- items는 루프돌릴 리스트, 여기선 영역에 저장된 리스트 --> 
	<li> ${item}</li>
</c:forEach>
 </ol>
</body>
</html>