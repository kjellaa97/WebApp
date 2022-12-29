<%@ page  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>목록 보기</title>
<style type="text/css">
	main{width: fit-content; margin: 1em auto;}
	main h3{text-align: center;}
	div.btn{width: fit-content; margin:1em auto;}
	tbody tr:nth-child(even)  { background-color: #ffd4e9; }
    tbody tr:nth-child(odd)   { background-color: #d4e9ff; }
</style>

</head>
<body>
<main> 
<h3>목록 보기</h3>
<tbody> 
<c:forEach var="n" items="${list}">	
	<tr>
	<div>
	${n.idx1} 
	${n.name1} 
	${n.pwd1}

	</div>
	</tr>
</c:forEach>
</tbody>
</main>
</body>
</html>