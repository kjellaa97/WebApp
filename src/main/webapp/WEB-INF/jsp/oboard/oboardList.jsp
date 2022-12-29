<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 게시판 목록</title>
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
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type ="text/javascript">
function directWrite()
{
	location.href="oracleboard2?cmd=directWrite";
}
</script>
</head>
<body>
<main> 
<%@ include file="logoutForm_oboard.jsp" %>
<h3> 게시판 목록</h3>
<table>
<tr><th>작성자</th><th>제목</th><th>작성일</th><th>히트수</th></tr>
<c:forEach var="ob" items="${list}"> 
		<tr>
			<td>${ob.author}</td>
			<td>
			 <a href = "oracleboard2?cmd=findOboard&boardid=${ob.boardid}">
			 ${ob.title}
			 </a>
			</td>
			<td>${ob.regdate}</td>	
			<td>${ob.hitcnt}</td>
		</tr>
</c:forEach>
</table>
<p>
<c:forEach var="n" begin="1" end="${ttlpages}">
  <a href ="oracleboard2?cmd=list&pgnum=${n}"> ${n}</a>
</c:forEach>

<p>
<div><button type= "button" onclick="directWrite();">글쓰기</button></div>
</main>
</body>
</html>