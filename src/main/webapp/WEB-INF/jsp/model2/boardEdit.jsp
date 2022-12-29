<%@ page import="com.ezen.web.hello.Board"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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

		url : 'board',
		method:'post',
		cache:false,
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.updated ? '수정성공':'수정실패');
			if(res.updated)
				location.href='board?cmd=find&num=${board.num}';
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
<%@ include file="logoutForm.jsp" %>
<h3>게시글 수정</h3>
<div class="container">
<form id="editForm" onsubmit="return updateBoard();">
	<input type="hidden" name="cmd" value="update">
	<input type="hidden" name="num" value= <%--=b.getNum()--%> ${board.num}>
<table>
<tr><th>글번호</th><td id="num">${board.num}</td><th>제목</th><td id="title" colspan="3">
	<input type="text" id="title" name="title" value= <%-- b.getTitle()--%> ${board.title} > 
</td></tr>
<tr><th>작성자</th><td>${board.author}</td><th>작성일</th><td> ${board.regDate}</td><th>Hit</th><td> ${board.hit}</td></tr>
<tr><th>내용</th><td colspan="5">
	<textarea id="contents" name="contents">${board.contents}</textarea> <!-- textarea는 value가 아닌 body에 주기 -->
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