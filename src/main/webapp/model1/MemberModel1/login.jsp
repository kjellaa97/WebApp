<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 로그인 화면 </title>
</head>
<body>
<form id="login" action= "login_proc.jsp" method="post">
<div> <label for= "id"> ID</label></div>
	<input type= "text" name="id" value="smith" >
<div><label for= "pwd"> PASSWORD </label></div>
	<input type= "text" name="pwd" value = "1111">
<p>
<div><input type="reset" value="취소"></div>
<div><input type="submit"  value= "로그인" ></button></div>
 </form>

</body>
</html>