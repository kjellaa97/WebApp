<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 회원 추가 화면</title>
<style type= "text/css">
	main{width: fit-content; margin: 1em auto;}
	main h3{text-align: center; }
	form{border: 1px solid black; padding: 1em; width: fit-content;}
	div.btn{width: fit-content; margin: 1em auto;}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type ="text/javascript">
function addForm()
{
	
	var ser = $('#addForm').serialize();
	//alert(ser);
	
	$.ajax({
		url:'oracle',
		method:'post',
		data: ser,
		dataType: 'json',
		cache: false,
		success: function(res){
			alert(res.added? "추가 성공": "추가 실패");
			if(res.added)
				location.href = "oracle?cmd=list";
			
		},
		err: function(xhr,status,err){
			alert(err);
		}
		
	});	
	return false;
}
</script>
</head>
<body>
<main>
<h3>이용자 추가하기</h3>
<form id="addForm">
<input type="hidden" name= "cmd" value="addEmpForm">
<div>
<label for="empno">번호</label> <input type="text" name="empno">
</div>
<div>
<label for="ename">이름</label> <input type="text" name="ename">
</div>
<div>
<label for="hiredate">입사일</label><input type="date" name="hiredate">
</div>
<div>
<label for="sal">연봉</label> <input type="number" name="sal">
</div>
<div>
<label for="deptno">부서</label> <input type="number" name="deptno">
</div>
<p>
<div class="btn">
<button type="button" onclick="addForm();">추가</button>
</div>
</form> 
</main>
</body>
</html>