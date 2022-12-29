<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 추가 폼</title>
<style type="text/css">
	main { width:fit-content; margin:1em auto;}
	h3 { text-align: center;}
	div.btn { width:fit-content; margin:0.5em auto; }
	form { border:1px solid black; padding:1em; width:fit-content;}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">
function addBoard()
{
	var obj = $('#addForm').serialize();
	$.ajax({
		url : 'boardController.jsp',
		method: 'post',
		data: obj,
		cache : false,
		dataType:'json',
		success:function(res){
			alert(res.added ? '추가 성공' : '추가 실패');
			location.href="boardController.jsp?cmd=LIST";
		},
		error : function(xhr,status,err){
			alert('에러:' + err);
		}
	});
	return false;
}
</script>
</head>
<body>
<main>
<h3>게시글 입력</h3>
<form id="addForm" action="boardController.jsp" method="post" onsubmit="return addBoard();">
	<input type="hidden" name="cmd" value="ADD">
	<div>
		<label for="title">제목</label> 
		<input id="title" type="text" name="title" value="게시판 테스트">
	</div>
	<div>
		<label for="contents">내용</label>
		<textarea id="contents" name="contents" rows="5" cols="50"></textarea>
	</div>
	<div class="btn">
		<button type="reset">취소</button>
		<button type="submit">저장</button>
	</div>
</form>
</main>
</body>
</html>