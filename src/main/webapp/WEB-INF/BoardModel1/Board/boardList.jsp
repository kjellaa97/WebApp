<%@page import="com.ezen.web.hello.*"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>
<main>
<%@ include file="/WEB-INF/BoardModel1/Login/logoutForm.jsp" %>
<h3>게시글 목록</h3>

<!-- JSTL버전 -->
<table>
<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>히트수</th></tr>
<c:forEach var="b" items="${list}" > <!-- 위에서 영역에 넣어줫음으로 EL사용  -->
		<tr>
			<td>${b.num}</td>
			<td>
				<a href="boardController.jsp?cmd=READ&num=${b.num}">
					${b.title}</a>
			</td>
			<td>${b.author}</td>
			<td>${b.regDate}</td>
			<td>${b.hit}</td>
		</tr>

</c:forEach>
</table>

<div class="links">
	[<a href="boardController.jsp?cmd=ADD_FORM">글쓰기</a>]
</div>
</main>
</body>
</html>