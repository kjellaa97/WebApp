<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 게시판 글 추가</title>
<style type = "text/css">
	main{width: fit-content; margin:1em auto;}
	main h3{text-align: center;}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">

var parentid = '${parentBoard.boardid}';
var title = '${parentBoard.title}';
$(function(){
	if(parentid!=''){
		
			$('input[name=parentid]').val(parentid); 
			$('#title').val('Re:'+title);
	}
})

function writeOboard(){

	var obj = $('#oboardForm').serialize();

	$.ajax({

		url: 'oracleboard2',
		method: 'post',
		data: obj,
		dataType: 'json',
		cache: false,
		success: function(res)
		{

			alert(res.written? "저장 완료":"저장 실패" )
			if(res.written)
				location.href="oracleboard2?cmd=list";
			
		},
		error: function(xhr, status, err){
			alert(err);
		}
		
		
	});
	return false;
}
</script>
</head>
<body>
<main> 
<%@ include file="logoutForm_oboard.jsp" %>
<h3> 게시판 글 추가 </h3>
<form id= "oboardForm" onsubmit="return writeOboard();">
<input type="hidden" name="cmd" value="write">
<input type="hidden" name="parentid" >
<input type="hidden" name="author" value="${uid}">
	<div>	
		<label for="title">제목</label>
	<input id="title" type="text" name="title" value="test title">
	</div>
	<div>	
		<label for="contents" >내용</label>
	<textarea name="contents" rows="5" cols="50"> </textarea>
	</div>
	<div class="btn"> 
	<div>
	<button type="submit">저장</button> 
	<button type="reset">취소</button></div>
	</div>
</form>
</main>
</body>
</html>