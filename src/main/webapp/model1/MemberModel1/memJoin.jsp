<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 회원가입 화면 </title>
<style>
	li{display: inline;}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">
	
function formCheck(){
	
	var name= $('input[name=name]').val();
	var sex = '';
	$('input[type=radio]').each(function(index,item){
		if(item.checked)
		{ 
			sex= $(item).val();
		}
	});
	var dob= $('input[name=dob]').val();
	var email=$('input[name=email]').val();
	var year=$('input[name=year]').val();
	var subject = '';
	$('input[type=checkbox]').each(function(index, item){
		if(item.checked) 
		{
			subject= $(item).val();
		}	
	});
	var intro=$('textarea[name=intro]').text();
	console.log(intro);
	
	if(name==''|| sex=='' || dob==''|| email=='' || year=='' || subject=='' || intro==''){
		alert("모든 항목을 입력해주세요");
		return false;
	}
	alert('폼을 전송합니다');

	return signup();

}
	
function signup()
{	
	var serData= $('#signup').serialize();
	$.ajax({
		url:'joinProc.jsp',
		method: 'post',
		data: serData,
		cache: false,
		dataType: 'json',
		success: function(res){
			alert(res.saved ?'회원정보 추가 성공':'정보추가 실패');
		},
		error: function(xhr, status, err){
			alert(err);
		}
	});
	return false; 
}



</script>
</head>

<body>
<form id="signup" onsubmit="return formCheck();">

<div><label for= "name"> 이름</label>
	<input type="text" name="name" value= "Ella"> </div>
<div><label > 성별</label>
<label for="female"> 여</label>
	<input type="radio" name="sex" value="F" checked="checked" required> 
<label for="male"> 남 </label>
	<input type= "radio" name="sex" value="M" ></div>
<div><label for= "dob"> 생일</label>
	<input type="date" name="dob"  value= "1997-05-08" required> </div>
<div><label for= "email"> 이메일</label>
	<input type="text" name="email" value="happy@gmail.com"> </div>
<div><label for= "year"> 경력 연수</label>
	<input type="number" name="year" value= "1"> </div>
<div>
<label for= "subject"> 관심 과목</label>
	<div> 
	<input type="checkbox" name="subject" value="Chemistry" checked="checked" > 
	Chemistry
	</div>
	<div> 
	<input type="checkbox" name="subject" value="Biology"  > 
	Biology
	</div>
	<div> 
	<input type="checkbox" name="subject" value="Lab"  > 
	Lab
	</div>
	<div> 
	<input type="checkbox" name="subject" value="English" > 
	English
	</div>
	<div> 
	<input type="checkbox" name="subject" value="History" > 
	History
	</div>	
</div>
<div><label for= "introduction"> 개인 소개</label>
	<textarea name="intro" rows="5" cols="10" placeholder="자기소개 입력해주세요"  required> Hi I'm Ella </textarea> </div>

<div><button type="submit">저장</button></div>
</form>
<p>
<a href= "memList.jsp">이용자 목록</a>
</body>
</html>