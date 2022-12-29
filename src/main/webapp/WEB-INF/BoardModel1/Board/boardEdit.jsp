<%@ page import="com.ezen.web.hello.Board"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="board_svc" class= "com.ezen.web.hello.BoardService" scope="session"/>
<jsp:useBean id="board" class="com.ezen.web.hello.Board">
	<jsp:setProperty name="board" property="num" param="num"/>
</jsp:useBean>
<%
	Board b = board_svc.find(board);
	//pageContext.setAttribute("b", b);	// 태그로 변환 가능(JSTL)
	// JSP Standard Tag Library 설치 필요 - *.jar (자바의 표준 압축파일 이름 java archive)
%>
<c:set var= "b" value= "<%=b %>" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 수정 폼</title>
<style type="text/css">
	main { width:fit-content; margin:1em auto;}
	main h3{text-align: center;}
	table { border:1px solid black; padding:1em; border-spacing: 0; border-collapse: collapse;
		width:630px;
	}
	th { background-color:rgb(200,255,255); border-right:1px solid black;}
	th, td {border-bottom:1px solid black; padding:0.4em;}
	td#title { width:400px; }
	input {width:400px;}
	textarea {width:520px; height:100px;}
	div.btn { margin:1em auto; width:fit-content;}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<%@ include file="/WEB-INF/BoardModel1/Login/logoutForm.jsp" %> 

<script type="text/javascript">
function updateBoard()
{
	var obj = $('#editForm').serialize();

	if(!confirm('현재의 내용으로 수정하시겠어요?')) return;
	$.ajax({
		url : 'boardController.jsp',
		method:'post',
		cache:false,
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.updated ? '수정성공':'수정실패');
			if(res.updated)
				location.href='boardController.jsp?cmd=FIND&num= ${b.num}';
		},
		error:function(xhr,status,err){
			alert('에러:' + err);
		}
	});
	return false;
}

</script>
</head>
<body>
<main>
<h3>게시글 수정</h3>
<div class="container">
<form id="editForm" onsubmit="return updateBoard();"">
	<input type="hidden" name="cmd" value="UPDATE">
	<input type="hidden" name="num" value= <%--=b.getNum()--%> ${b.num}>
<table>
<tr><th>글번호</th><td id="num"><%=b.getNum()%></td><th>제목</th><td id="title" colspan="3">
	<input type="text" id="title" name="title" value= <%-- b.getTitle()--%> ${b.title} > 
</td></tr>
<tr><th>작성자</th><td><%=b.getAuthor()%></td><th>작성일</th><td> ${b.regDate}</td><th>Hit</th><td> ${b.getHit}</td></tr>
<tr><th>내용</th><td colspan="5">
	<textarea id="contents" name="contents">${b.contents}</textarea> <!-- textarea는 value가 아닌 body에 주기 -->
</td></tr>
</table>
<div class="btn">
	<button type="reset">취소</button>
	<button type="submit">수정</button>
</div>
</form>
</div>
</main>
</body>
</html>