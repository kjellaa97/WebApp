<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 회원 상세 정보 보기</title>
<style type="text/css">
	main{width: fit-content; margin: 1em auto;}
	main h3{text-align: center;}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type ="text/javascript">

function edit(){
	location.href= "oracle?cmd=edit&empno=${emp.empno}";
}

function deleteEmp(empno)
{
	if(!confirm("정말로 삭제하시겠습니까?")) return;
	$.ajax({
		
		url: 'oracle',
		method: 'post',
		data: {"cmd": "deleteEmp", "empno": empno},
		dataType: 'json',
		cache: false,
		success: function(res){
			alert(res.deleted? "삭제 성공": "삭제 실패");
			if(res.deleted) 
				location.href= "oracle?cmd=list";
		},
		err: function(xhr, status, err){
			alert("에러"+ err);
		}
		
	});
	return false;
	
	
}
</script>
</head>
<body>
<main> 
<h3> 회원 상세 정보</h3>
<div>번호 ${emp.empno}</div>
<div>이름 ${emp.ename}</div>
<div>입사일 ${emp.hiredate}</div>
<div>연봉 ${emp.sal}</div>
<div>부서 ${emp.deptno}</div>


<button id="e" type="button" onclick="edit();">수정</button>
<button id="d" type="button" onclick="deleteEmp(${emp.empno});">삭제</button>
</main>
</body>
</html>