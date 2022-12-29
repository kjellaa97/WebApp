<%@page import="com.ezen.web.hello.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	List<User> list = (List<User>) request.getAttribute("list");
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이용자 목록 보기</title>
<style type="text/css">
	main{width: fit-content; margin:0 auto; }
	table { border: 1px solid black; border-spacing: 0px;
		   border-collapse: collapse; padding: 0.3em;}
	th { background-color: #eee; border-bottom: 3px double black; }
	th,td { padding: 0.2em 1em; border-right:1px dashed black}
	td { border-bottom: 1px dashed black;}
	h3 {width: fit-content; margin:0 auto; patting-bottom: 1em;}
	for>div {width: fit-content; margin: 0 auto; }
	a{ text-decoration: none; color: blue}
	
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
	$(function(){
		seeNames();
	});
</script>
</head>

<body>
<main><!--  main, header, footer, article, section, aside 이러한 아이들은 semantic tag라고 함-->
<h3> 이용자 목록 </h3>
<table> 
<tr><th>번호</th><th>이름</th><th>전화</th><th>메일</th></tr>
<%
	for(int i=0;i<list.size();i++)
	{
		User u = list.get(i);
		int num = u.getNum();
		String name = u.getName();
		String phone = u.getPhone();
		String email = u.getEmail();
		String line = String.format("%d %s %s %s<br>", num, name, phone, email);
		//out.println(line); 
	
%>
	<tr>
		<td><%= num %> </td> 
		<td><a href = "usercontrol?cmd=detail&num=<%= num %>"><%= name %></a></td>	
		<td><%= phone %></td>
		<td><%= email %></td>
		
	</tr>
<%	}
%>	
</table>

<form action = 'usercontrol' method= 'post' style="margin-top:0.5em;">
	<input type ="hidden" name="cmd" value="findByName" >
	<div>
		<label> 이름으로 검색 </label>
		<input type="text" name="name" list="nameList" autocomplete = "off">
		<button type= 'submit'>검색</button>
	</div>
</form>

<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
	function seeNames()
	{

		$.ajax({
			url: 'usercontrol',
			method: 'post',
			data: {"cmd": "names"},
			dataType: 'json',
			cache: false,
			success: function(res){
				//alert(res);
				/*
				for(var i=0; i<res.length; i++)
				{
					console.log(i+1 + "." + res[i]);	//콘솔로그는 개발자도구에 찍힘 
				}*/
				//$('#out').val(JSON.stringify(res))		//jquery 에서는 .val(값) 주면 그 값을 세팅하는 것, 즉 res값을 out이라는 태그에 넣겟다
															// JSON오브젝트(res)를 문자열로 바꿔야할 때 stringify 필요, text로 전달되어야지 안깨지니까
				//$('#out').text(JSON.stringify(res));		//value가 없는 태그들은 이렇게 함
				
				//tag안에 값을 넣는 방법
				//$('#uid').val('MyID');
				//$('#desc').text('반갑습니다~');
				//tag안에 값을 꺼내는 방법 (alert로)
				/*
				var uid = $('#uid').val();
				var desc = $('#desc').text();
				alert(uid +',' + desc);
				*/
				/*
				var newDiv = $('<div> 새로운 div 태그</div>');
				$('#div5').append(newDiv);
				*/
				
				/*
				var parent = $('#div5');
				for(var i=0; i<res.length; i++)
				{
					var div = $('<div>' + res[i] + '</div>')
					parent.append(div);			//append는 한행한행 나오게함
				}*/
				
				/*
				var parentsel = $('#sel1');
				for(var i=0; i<res.length; i++)
				{
					var opt = $('<option>' + res[i] + '</option>')	
					parentsel.append(opt);
				}*/

				var nameList = $('#nameList');
				for(var i=0; i<res.length; i++)
				{
					var nameOpt = $('<option>' + res[i]+ '</option>');
					nameList.append(nameOpt);
					
				}
				
			},											
			error: function(xhr, status, err){
				alert(err);
			}
		});
	}

</script>

<datalist id = "nameList">
</datalist>

<!--  목록을 표시하는 태그들 -->
<ol>	<!--  //ordered list ,오더 없는건 ul unordered list--> 
	<li>A</li>
	<li>B</li>
	
</ol>
<ul>
	<li> C</li>
	

</ul>
<input list = "subjectList">	<!-- //input 박스에 있는 목록을 제시해주는 태그  -->
<datalist id="subjectList">
<option> Java </option>
	<option> Python </option>
	<option> JavaScript </option>

</datalist>


<select id="sel1" name="subject">
<!-- 
	<option> Java </option>
	<option> Python </option>
	<option> JavaScript </option>
	
 -->


</select>

<!--  
<div>아이디 <input type="text" id="uid" value="MyID" ></div>
<div id= "div5">
	<label> 소 개 </label> <span id="desc">반갑습니다!</span>
</div>

<div id="out"></div> -->

<p>
<a href = "usercontrol?cmd=form">[이용자 등록하기]</a>
<a href= usercontrol?cmd=search> [검색하기]</a>
<a href= "javascript:seeNames();"> [명단 보기]</a>


</main>
</body>
</html>