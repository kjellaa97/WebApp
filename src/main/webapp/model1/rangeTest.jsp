<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type= "text/javascript">
	function valueCheck()
	{
		var v = $('#slider').val(); //  이 태그의 객체를 가져올 수 있음
		console.log('slider value:' + v);
		$('#slider_out').val(v);
	}

</script>
</head>
<body>
<form>
<input id="slider" name="slider" type ="range"
	 min="0" max="100" step="1" value="50" 
	 oninput="valueCheck();"> <!-- 입력을 하면  oninput 값이 실행됨 -->
<output id="slider_out"></output>

<p>
경매 마감일 <input type="date" name="expire"> 
<p> 
<input type="number" name ="count" min="0" max="100" step="1" value="78">
<p>
<input type="color" name= "favorite"  > 
<p>
<textarea name="dsc" rows="5" col="20" placeholder="입력해주세요 "> Hello </textarea>
<button type="submit"> 확인 </button>
</form>
</body>
</html>