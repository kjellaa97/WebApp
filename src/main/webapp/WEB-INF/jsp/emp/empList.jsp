<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 사원 목록 보기</title>
<style type="text/css">
	main{width: fit-content; margin: 1em auto;}
	main h3{text-align: center;}
	div.btn{width: fit-content; margin:1em auto;}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type ="text/javascript">

function sendAddForm()
{
	location.href= "oracle?cmd=addEmp";
}

function onSelectChange()
{							// 만약 $('#keyword').val() 이면 값을 가져오라는것, '' 가 있다면,
	 $('#keyword').val(''); //값을 제거하라는 명령, 이전에 사원이름 검색을 선택했다면 그 기록이 안남도록 input박스 비워줌
     var cat = $('select[name=category]').val(); //그리고 category값을 가져온다
     if(cat=='deptno') //부서 번호로 검색을 선택
     {
        $('input[name=cmd]').val('findByDeptno'); //밑에 input박스 cmd를 findByDeptno로 설정해주는 것임
        $('input#keyword').prop('name', 'deptno'); //input 박스 중 keyword태그의 name을 deptno로 바꿔준다
        $.ajax({
			url:'oracle',
			method:'post',
			data:{"cmd":"getDeptnoList"},
			cache:false,
			dataType:'json',
			success:function(res){ //deptnolist들이 내려옴
				 $('#deptnolist>option').remove(); //여기 왜 remove시키지???이걸 안하면 부서번호에서 사원이름으로 갔다가 또 부서번호로 갈 때 deptno값이 또 추가됨
	             $('#keyword').attr('list','deptnolist');
				for(var i=0;i<res.length;i++){
					$('#deptnolist').append('<option>'+res[i]+'</option>');
				}
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
	}  else{ //사원 이름으로 검색을 선택
        $('#keyword').attr('list','');
        $('#deptnolist>option').remove();
        $('input[name=cmd]').val('findByEname');
        $('input#keyword').prop('name', 'ename');
     }

}
	
</script>
</head>
<body>
<main> 
<h3>사원정보 목록 보기</h3>
<c:forEach var="emp" items="${list}">	
	<div>
	${emp.empno} 
	<a href= "oracle?cmd=detailByEmpno&empno=${emp.empno}"> ${emp.ename} </a> 
	${emp.deptno}
	${emp.hiredate} 
	${emp.sal} 

	</div>
</c:forEach>
<div> 
	<form id="categoryForm" action="oracle" method="post">
		<input type ="hidden" name="cmd" value="findByEname"> <!-- 처음엔 value가 없어서 cmd가 비어있다, 그래서 초기값을 설정해논다, 어차피 ajax에서 변경해주니까 -->
		<select name= "category" onchange="onSelectChange();">
			<option value="ename"> 사원이름 </option>
			<option value="deptno"> 부서번호 </option>
		</select>
		<input id="keyword" type="text" name="ename"
							 list="deptnolist" autocomplete="off">
		<button type="submit"> 검색 </button>
	</form>
</div>
<p>
	<div class="btn">
	<button type= "submit" onclick="sendAddForm();">추가</button>
	</div>
</main>
<datalist id="deptnolist"></datalist>
</body>
</html>