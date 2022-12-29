<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<style type ="text/css">
</style>

<head>
<meta charset="utf-8">
<title> 시험 화면</title>

<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">

 $(function()
{	
	$.ajax({
	
		url: 'lms2',
		method: 'post',
		data: {"cmd": "getTest"},
		dataType: 'json',
		cache: false,
		success: function(res){
			
			for(var i=0; i<res.length ;i++)
				{
					$('#Q'+i).text(res[i].lms_tnum +' '+res[i].lms_question);
					$('#A' +i).text(res[i].lms_anum);
				}		
			
		}
	}); 
	
});
 
function sendForm()
{
	var obj = ('#testForm').serialize();
	
	if(!confirm('정말 제출하시겠습니까?'));
	$.ajax({
		
		url:'lms2',
		method:'post',
		cache: false,
		data: obj,
		dataType: 'json',
		success: function(res){
			alert(res.submitted? '제출 성공': '제출 실패');
			if(res.submitted)
				location.href = 'lms?cmd=main'; //메인페이지로 이동 
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
<h5>사용자 아이디: ${id}</h5> 
<h5>과목 번호: ${video.lvl_code}</h5>
<h5>강의 번호: ${video.lms_num}</h5>
<h3>시험 문제</h3>
<form id="testForm" onsubmit="sendForm()"> 
<p>
<label><span id='Q0'></span></label>
<p>
<input type="radio" name="first" ><span id='A0'></span><br>
<input type="radio" name="first" ><span id='A1'></span><br>
<input type="radio" name="first" ><span id='A2'></span><br>
<input type="radio" name="first"><span id='A3'></span><br>
<p>
<label><span id='Q4'></span></label>
<p>
<span id='A4'></span><input type="text" name="second" ><br>
<p>
<label><span id='Q5'></span></label>
<p>
<input type="radio" name="third" ><span id='A5'></span><br>
<input type="radio" name="third" ><span id='A6'></span><br>
<input type="radio" name="third" ><span id='A7'></span><br>
<input type="radio" name="third" ><span id='A8'></span><br>
<p>
</form>
<div>
<button type="reset">취소</button>
<button type="submit">제출</button>
</div>
</body>
</html>