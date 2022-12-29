<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 상세 페이지</title>
<style type="text/css">
	main{width: fit-content; margin: 1em auto;}
</style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">

function directEdit(){
	location.href = "oracleboard2?cmd=editoBoard&boardid=${oboard.boardid}";
}
function deleteOboard(id)
{
	
	if(!confirm('정말로 삭제하시겠어요?')) return;
	
	$.ajax({
		url: 'oracleboard2',
		method: 'post',
		data: {"cmd": "deleteoBoard", "boardid": id},
		dataType: 'json',
		cache: false,
		success: function(res){
			alert(res.deleted? "삭제 성공": "삭제 실패");
			if(res.deleted)
				location.href= "oracleboard2?cmd=list";
		},
		error: function(xhr,stats,err)
		{
			alert(err);
		}
		
	});
	
}

</script>
<main> 
<%@ include file="logoutForm_oboard.jsp" %>


<h3>게시글 상세 보기</h3>
<div>제목 ${oboard.title}</div>
<div>작성자 ${oboard.author}</div>
<div>조회수 ${oboard.hitcnt}</div>
<div>날짜 ${oboard.regdate}</div>
<div>내용 ${oboard.contents}</div>

<p>
<div><button type="button" onclick= "directEdit()">수정</button>
	<button type="button" onclick= "deleteOboard(${oboard.boardid})">삭제</button>
</div>
<p>
<a href="oracleboard2?cmd=list">[목록 보기]</a>
<a href="oracleboard2?cmd=reply&boardid=${oboard.boardid}">[답글 달기]</a>
</main>
</body>
</html>