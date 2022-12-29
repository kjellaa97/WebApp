<%@page import="com.ezen.web.hello.*"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  model2 의 boardList에서는 useBean이 없어도 됨 -->
<%-- 이것도 안써줘도 됨. request에 들어가있어서(영역에 들어가 있어서 필요가 없음
	List<Board> list = (List<Board>)request.getAttribute("list"); 

<c:set var="list" value= "<%=list %>" > </c:set> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 목록</title>
<style type="text/css">
	main { width:fit-content; margin:1em auto; }
	main h3{ text-align: center; }
	table { border:1px solid black; border-spacing:0; border-collapse: collapse; 
		padding:0.5em;
	}
	td,th { border-bottom:1px dashed black; padding:0.3em 1em; 
		border-right: 1px solid black;
	}
	th { background-color:rgb(210,210, 255);}
	a {text-decoration: none;}
	div.links { width:fit-content; margin:1em auto; }
</style>
</head>
<body>
<main>
<%@ include file="logoutForm.jsp" %>
<h3>게시글 목록</h3>

<!-- JSTL버전 -->
<table>
<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>히트수</th></tr>
<c:forEach var="b" items="${list}" > <!-- 위에서 영역에 넣어줫음으로 EL사용,(list에 있는 모든 Board들을 보여주고자 함)  -->
		<tr>
			<td>${b.num}</td>
			<td>
				<a href="board?cmd=read&num=${b.num}">
					${b.title}</a>
			</td>
			<td>${b.author}</td>
			<td>${b.regDate}</td>
			<td>${b.hit}</td>
		</tr>

</c:forEach>
</table>

<div class="links">
	[<a href="board?cmd=addform">글쓰기</a>]

</div>
</main>
</body>
</html>