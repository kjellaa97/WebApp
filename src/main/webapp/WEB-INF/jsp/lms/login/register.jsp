<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 회원 가입</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">
function register()
{
	
	var obj = $('#registerForm').serialize();

	$.ajax({
	
		url : 'lmslogin',
		method:'post',
		data : obj,
		cache:false,
		dataType:'json',
		success:function(res){

			alert(res.pass ? '회원가입 성공':'회원가입 실패');
			if(res.pass){ //true일 경우, 로그인 성공했을 경우
				if(res.url!=null)
				{
					location.href=res.url;
				}else
				{
					location.href='loginpg.jsp'//로그인 성공후 넘어가는 페이지 ';
				}
			}
		},
		error : function(xhr, status, err){
			alert('에러:' + err);
		}
	});
	return false;
}
</script>
</head>


<body>
<h3>회원 가입</h3>
<form id="registerForm" onsubmit="return register();">
<input type= "hidden" name="cmd" value="register">
<div>아이디 
	<input type="text" name="userid" >
</div>
<div>비밀번호 
	<input type="text" name="pwd">
</div>
<button type="submit">저장</button>
</form>
</body>
</html>