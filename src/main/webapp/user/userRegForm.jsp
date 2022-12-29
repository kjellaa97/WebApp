<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>메인 화면</title>
<style type="text/css">
   #main {width:fit-content; margin:0 auto; }
   h3 { text-align: center; }
   form { width:fit-content; border:1px solid black; padding:1em;
      margin:0 auto; }
   label { margin-right:1em; }
   form>div { margin:0.3em; }
   button { display:block; margin:5px auto; }
   a{width: fit-content; margin: 0 auto;}
</style>

<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type = "text/javascript">
	function regUser()
	{
		var serData = $('#register').serialize();
		//메모리에서 가져오는 법 //var num = $('#num').val();
		$.ajax({
			url: 'usercontrol',
			method: 'post',
			data: serData, 
			cache: false, 	// 브라우저가 응답을 캐싱하지 않도록 함 
							// cache: 브라우저가 사용하는 메모리 
			
			dataType: 'text',
			success: function(resp){
				var jsObj = JSON.parse(resp);
				alert(jsObj.registered? '이용자 추가 성공': '이용자 추가 실패');
				
				if(jsObj.registered)
					{
					//메모리에서 가져오는 법 // location.href = 'usercontrol?cmd=detail&num='+ num;
					location.href ='usercontrol?cmd=detail&num='+ jsObj.num; // 이거는서버에서 가져오는 법
					}
			},
			error: function(xhr, status, err){
				// xhr: XMLHttpRequest(비동기 요청을 위한 객체)
				// status: 응답 상태코드(200: 정상, 500: 실행 오류, 400: url 요청 오류)
				alert(err);
			}
		}); 
		return false;	// 여기서 false가 안나와주면, register 저장이 2번 됨. 왜냐하면 "등록"을 누르는 순간 submit 하면서 정보 저장함
						// 그렇게 submit으로 저장된 이용자 정보가 formCheck로 넘어가는데, 이 때 조건이 맞으면 regUser()로 가게됨
						// regUser 과정으 다 거치면 또 저장되는 건데, 따라서 이걸 막아주는게 return false임
						// 즉, 첫번째로 저장시키는 기능은 submit, 두번째로 저장시키는 기능은 return formCheck()
	}

function formCheck(){
	var num = $('input[name=num]').val();
	var name = $('input[name=name]').val();
	var phone = $('input[name=phone]').val();
	var email = $('input[name=email]').val();
	var pic = $('input[name=pic]').val();
	
	if(num=='' || name=='' || phone=='' || email=='' || pic ==''){
		alert('모든 항목은 필수로 입력해야 합니다');
		return false;	// 이렇게 하면 이용자가 submit눌러도 안넘어감
	}
	alert('정상적으로 입력되어 폼을 전송합니다');
	return regUser() ; 
	}
</script>

</head>
<body>
<div id ="main"></div>
<h3>사용자 정보 등록</h3>
<form id='register' action = 'usercontrol' method= 'post' onsubmit = "return formCheck()">
	<input type= 'hidden' name ='cmd' value = 'reg'>
	<div><label>번호</label><input id ='num' type = 'text' name = 'num'></div>
	<div><label>이름</label><input type = 'text' name = 'name'></div>
	<div><label>전화</label><input type = 'text' name= 'phone'></div>
	<div><label>메일</label><input type = 'text' name = 'email'></div>
	<div><label>사진</label><input type = 'text' name = 'pic'></div>
	<div id="btns">
		<button type = "reset" >취소</button>
		<!-- <button type = "button" onclick="regUser();"> 등록 </button>	 -->
		<button type = "submit"> 등록 </button>	
	</div>

</form>
<a href=usercontrol?cmd=list> [목록 보기] </a>

</body>
</html>