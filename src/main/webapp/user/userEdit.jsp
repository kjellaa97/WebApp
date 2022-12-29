<%@page import="com.ezen.web.hello.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	User user= (User)request.getAttribute("user");
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이용자 정보 수정</title>
<style type="text/css">
	#container { border: 1px solid black; padding 1em; width: fit-content; margin:0 auto; background-color: #eee;}
	label {display: inline-block; width:2.0em; margin-right: 1em; border-bottom: 1px solid black;}
	h1 { width: fit-content; margin: 0 auto;}
	span{display: inline-block; width: 10em; text-alight: left;}
</style>

<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type ="text/javascript">
	function updateUser()
	{
		//alert('적용 버튼 누름');
		//폼에 입력된 이용자 정보를 비동기 요청(Ajax요청)으로 전달하고
		// 응답을 받아서 성공 여부를 alert로 표시한다 
		// 이용자가 확인을 누르면 목록 페이지로 이동한다
		//var cmd = $('#cmd').val(); //#은 id select 
		//var num = $('#num').val();
		//var email = $('#email').val();
		//alert(cmd +", " + num +", " + email);
		var serData = $('#updateForm').serialize();
		//alert(serData);
		$.ajax({
			url : 'usercontrol', 	//로 데이터를 보냄 
			method : 'post',		// 방식으로
			data : serData,			// 이 데이터를 요청 한다 --> 서버로 감
			
			dataType : 'text',
			success : function(res){ 		//res의 데이터가 서버에서 이렇게 옴 {"updated": true}
				var jsObj = JSON.parse(res); //parse가 JSON오브젝트로 만들어줌
				//alert(jsObj.updated);
				alert(jsObj.updated? '수정 성공': '에러');
				if(jsObj.updated){
					location.href = 'usercontrol?cmd=list';
				}
			},
			error : function(xhr, status,err){
				alert(err);
			}
			
		});
	}
</script>
</head>
<body>
<h3> 이용자 정보 수정 </h3>
<div id = "container">
<div><label>번호 </label> <span><%=user.getNum()%></span></div>
<div><label>이름 </label> <span><%=user.getName()%></span></div>
<div><label>전화 </label> <span><%=user.getPhone()%></span></div>
<form id='updateForm' action= "usercontrol" method="post">
	<input id='cmd' type="hidden" name="cmd" value="update">
	<input id= 'num' type="hidden" name="num" value="<%=user.getNum()%>">
	<div><label>메일 </label>
		<input id= 'email' type= "text" name="email" value="<%=user.getEmail()%>">
	</div>
	<div><label>사진</label> 
		<input id = 'pic' type ="text" name= "pic" value = "<%=user.getPic() %>">
	</div>
	<div id= "btns">
		<button type = "reset">취소</button>
		<button type = "button" onclick="updateUser();"> 적용</button>
	</div>
</form>
</div>
<p>
[<a href = "usercontrol?cmd=list">목록 보기</a>]

</body>
</html>