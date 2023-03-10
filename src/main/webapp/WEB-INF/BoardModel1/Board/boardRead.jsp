<%@page import="com.ezen.web.hello.Board"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<jsp:useBean id="board_svc" class="com.ezen.web.hello.BoardService" scope="session"/>
<jsp:setProperty name="board_svc" property="request" value="<%=request%>" />

<jsp:useBean id="board" class="com.ezen.web.hello.Board">
	<jsp:setProperty name="board" property="*"  /> <!-- BoardList에서 넘어오는 글 번호를 받아 Board객체에 초기화한다  -->
</jsp:useBean>

<%
	Board b = board_svc.read(board); //board에는 글 번호가 담겨 있음
	pageContext.setAttribute("board", b); // 이 페이지에서만 쓸거니까 pageContext
	
	String uid= (String)session.getAttribute("uid");
	String author = b.getAuthor();
	boolean owned = uid.equals(author);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 읽기</title>
<style type="text/css">
	main{width:fit-content; margin:1em auto; }
	main h3{text-align: center;}
	label { display:inline-block; padding:0.2em 1em; border-bottom:1px solid black;
		text-align: right; width:3em; margin-right:1em; background-color:rgb(200,255,255);
	}
	.container {width:fit-content; padding:1em; border:1px solid black;}
	a { text-decoration: none;}
	.links{margin:1em auto; width:fit-content; }
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type ="text/javascript">

$(function(){   
	   var owned = ${owned};
	   
	   if(!owned) 
	      {
	         $('#e_btn').css('display', 'none');
	         $('#d_btn').css('display', 'none');
	      }
	})
	
function deleteBoard(num)
{
	if(!confirm('정말로 삭제하시겠어요?')) return;
	$.ajax({
		url: 'boardController.jsp',
		method: 'post',
		cache: false,
		data: {"cmd", "DELETE", "num": num }
		dataType: 'json'
		success: function(res){
			alet(res.deleted? "삭제성공": "삭제 실패")
			if(res.deleted)
				location.href= 'boardController.jsp';

		},
		error: function(xhr, status, err){
			alert(err);
		}
		
	})
	
}
</script>
</head>
<body>
<main>
<h3>게시글 읽기</h3>
<div class="container">
<div><label>글번호</label> <%-- =b.getNum()--%> ${board.num}</div>
<div><label>제목</label> <%--=b.getTitle()--%> ${board.title}</div>
<div><label>작성자</label> <%--=b.getAuthor()--%> ${board.author }</div>
<div><label>작성일</label> <%--=b.getRegDate()--%> ${board.date}</div>
<div><label>히트수</label> <%--=b.getHit()--%> ${board.hit }</div>
<div><label>내용</label> <%--=b.getContents()--%> ${board.contents }</div>
</div>
<div class="links">
	<button id="e_btn" type="button" onclick="boardController.jsp?cmd=EDIT&num=${b.num}">수정</button>button> 
	<button id="d_btn" type="button" onclick="deleteBoard(${b.num});">삭제</button>
	[<a href="boardController.jsp?cmd=LIST">목록</a>]
</div>
</main>
</body>
</html>