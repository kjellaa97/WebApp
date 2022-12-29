<%@page import="com.ezen.web.hello.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	User user= (User)request.getAttribute("user");
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이용자 상세 보기</title>
<style type="text/css">
	#container { border: 1px solid black; padding 1em; width: fit-content; margin:0 auto; background-color: #eee;}
	label {display: inline-block; width:5em; margin-right: 1em; border-bottom: 1px solid black;}
	h1 { width: fit-content; margin: 0 auto;}
	span{display: inline-block; width: 10em; text-alight: left;}
	main{width: fit-content; margin: 0 auto;}
	a{ text-decoration: none;}
	img {margin: 2em; width: 200px;}
	
</style>

<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
	$(function(){ 	// jQuery Ready function(Ready Event Handler function), 오브젝트가 준비되었을 때 실행 됨
		//일반 function은 서버가 돌아갈때부터 순서대로 돌아가는데 jQuery function은 다돌아가고 ready가 되야함
		//alert('jQuery ready!'); 
		console.log('jQuery ready!');
	});
</script>
<script>
	function deleteUser(num)
	{
		if(!confirm("정말로 현재 이용자 정보를 삭제하시겠어요?")) return;
			{ 
				//location.href="usercontrol?cmd=delete&num=" +num;
				
					var dData = {"cmd": "delete", "num": num};
					
					$.ajax({
						url: 'usercontrol',
						method: 'post',
						data: dData,
						
						dataType: 'json', //data type 은 json 으로 받겠다
						success: function(res){
							if(res.deleted){
								location.href = 'usercontrol?cmd=list';
								alert("삭제 성공!");
							}else{
								alert("정보 삭제 실패");
							}
						}
					})
				
			}
	}

$(function(){		// JQuery 함수 $(함수) : 현재 페이지의 모든 요소들(페이지 구성하는 태그들)이 객체로 로드되어 사용될 준비가 된 경우에 자동으로 호출됨, 그래야지 DOM사용가능, 객체가 생성되야만이 
		$.ajax({
			url: 'usercontrol',	//servelet
			method: 'post',	// 이 방식으로
			data: {"cmd": "numList"},	// 이러한 데이터를 요청한다
			dataType: 'json',
			cache: false,
			success: function(res){	//그럼 그 데이터가 res로 와서 jsonObject가 됨. res안엔 이용자의 수가 배열로 되어있음
		
				for(var i=0;i<res.length; i++){	//배열 번호 수 만큼 루프 돌려
				var link = $('<a href = \"javascript:showUser(' + res[i] + ');\">' +res[i] + '</a>');	//$써줌으로써 태그 객체가 됨. 그래서 link의 객체가 되엇고, 태그니까 css을 입힐 수 잇음,  showUser(res[i]) 로 번호를 받아옴 
				link.css('display','inline').css('padding','0.5em');	//태그는 박스로 되어있음. padding은 알맹이와 그 박스 테두리 사이의 간격임 
				//여기까지는 메모리에 있음
				//그럼 이 태그를 웹브라우저에 내놓으려면 그 태그(브라우저에 있는) 안에 값을 넣어야지! append 등장
				$('#numSeries').append(link);	//numSeries를 잡아내려면 객체가 다 로드되어야지! 그 시점을 알려주는게 ready함수
				}
			},
			error: function(xhr, status, err)
			{
				alert(err);
			}
				
		})
	});

	function showUser(num){
		$.ajax({
			url: 'usercontrol',
			method: 'post',
			data: {"cmd": "getUserJSON", "num": num},
			dataType: 'json',
			cache: false,
			success: function(res){
				
				//res에 클릭한 사람의 번호가 왔어. 이제 그 번호 이용자로 화면이 바뀌는 게 필요
				$('#num').text(res.num);
				$('#name').text(res.name);
				$('#phone').text(res.phone);
				$('#email').text(res.email);
				$('#pic').prop('src', 'images/' +res.pic);

			
				
			
			},
			error: function(xhr, status, err){
				alert(err);
			}
			
		})
	
	}
/*	
$(function(){
	$('#pic').prop("src", "images/Bishon.jpg");	//src속성에다가 "images/Bishon"을 집어 넣어라 
});*/

</script>
</head>
<body>
<main>
<h4> 이용자 상세 정보 </h4>
<div id = "container">
<div><label >번호 </label> <span id = "num"><%=user.getNum()%></span></div>
<div><label>이름 </label> <span id = "name"><%=user.getName()%></span></div>
<div><label >전화 </label> <span id = "phone"><%=user.getPhone()%></span></div>
<div><label >메일 </label> <span id = "email"><%=user.getEmail()%></span></div>
<div><label >사진</label> <span id = "pic"><%=user.getPic() %></span></div>		<!-- 사진 파일명을 출력하게 함-->
<div><img id = "pic" src = "images/<%=user.getPic() %>"></div>  <!--  사진 띄우고 싶으면 tag를 img 해야함 이렇게 해야함  -->
</div>
<p>
[<a href = "usercontrol?cmd=list">목록</a>]
<a href= "usercontrol?cmd=edit&num=<%=user.getNum() %>"> [수정]</a>
<a href= "javascript:deleteUser(<%=user.getNum()%>);"> [삭제]</a>
<p>
<div id="numSeries"></div>

</main>
</body>
</html>