<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 로그인 화면</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">
function getLogin()
{
	
	var obj = $('#loginForm').serialize();
	//alert(obj);
	$.ajax({
	
		url : 'lmslogin',
		method:'post',
		data : obj,
		cache:false,
		dataType:'json',
		success:function(res){

			alert(res.pass ? '로그인 성공':'로그인 실패');
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
<main>
<h3>로그인</h3>
<form id="loginForm" onsubmit="return getLogin();">
	<input type="hidden" name="cmd" value="login">
	<div><label for="uid">아이디</label>
		<input type="text" id="uid" name="uid" value="smith">
	</div>
	<div><label for="pwd">암호</label>
		<input type="password" id="pwd" name="pwd" value="1234">
	</div>
	<div class="btn">
		<button type="reset">취소</button>
		<button type="submit">로그인</button>
	</div>
</form>
<div> 회원가입 </div>
</main>
</body>
</html>