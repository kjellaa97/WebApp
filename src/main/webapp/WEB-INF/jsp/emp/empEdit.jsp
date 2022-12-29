<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 수정 화면 </title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<%@ include file="/WEB-INF/BoardModel1/Login/logoutForm.jsp" %> 
</head>
<script type= "text/javascript">
function updateEmp()
{
	var ser = $('#updateForm').serialize();	
	
	if(!confirm ('현재의 내용으로 수정하시겠어요?')) return;
	$.ajax({
		url:'oracle',
		method: 'post',
		cache: false,
		data: ser,
		dataType: 'json',
		success:function(res){
			alert(res.updated? "수정 성공": "수정 실패");
			if(res.updated) 
				location.href="oracle?cmd=detailByEmpno&empno=${emp.empno}";
				
		},
		error: function(xhr,status,err){
			alert('에러' +err);
		}
	});
	return false;
}

</script>

<body>
<h3>회원 정보 수정</h3>
<form id ="updateForm" onsubmit="return updateEmp();">
	<input type="hidden" name="cmd" value="update">
	<div>사원 번호 ${emp.empno}</div>
		<input type="hidden" name="empno" value="${emp.empno}">
	<div>이름 ${emp.ename}
	</div>
	<div>부서 번호 
		<input type="number" id="deptno" name="deptno" value= "${emp.deptno}">
	</div>
	<div>입사일 ${emp.hiredate}
	</div>
	<div>연봉
		<input type="sal" id="sal" name="sal" value= "${emp.sal}">
	</div>

<div class="btn">
	<button type="reset">취소</button>
	<button type="submit">수정</button>
</div>
</form>
</body>
</html>