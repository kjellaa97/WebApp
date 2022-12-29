<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 게시판 수정 폼</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">
function editoBoard()
{
	var ser = $('#editForm').serialize();
	$.ajax({
		
		url: 'oracleboard2',
		method: 'post',
		data: ser,
		dataType: 'json',
		cache: false,
		success: function(res){
			alert(res.updated? "수정 성공": "수정 실패");
			if(res.updated){
				location.href= "oracleboard2?cmd=findOboard&boardid=${oboard.boardid}";
			}
		},
		error: function(xhr,status,err){
			alert(err);
		}
		
	});
	return false;
}
</script>
<body>
<h3>게시판 글 수정하기</h3>
<form id="editForm" onsubmit="return editoBoard();">
	<input type="hidden" name="cmd" value="updateoBoard">
	<input type="hidden" name="boardid" value="${oboard.boardid}">
<table>
	<tr><th>제목</th>
		<td>
		<input type="text" name= "title" value="${oboard.title}">
		</td>
	</tr>
	<tr><th>작성자</th>
		<td>${oboard.author}</td>
	</tr>
	<tr><th>작성일</th>
		<td>${oboard.regdate}</td>
	</tr>
	<tr><th>조회수</th>
		<td>${oboard.hitcnt}</td>
	</tr>
	<tr><th>내용</th>
		<td>
		<textarea name= "contents">${oboard.contents}</textarea>
		</td>
	</tr>
</table>
<div>
	<button type="reset">취소</button>
	<button type="submit">수정</button>
</div>
</form>
</body>
</html>