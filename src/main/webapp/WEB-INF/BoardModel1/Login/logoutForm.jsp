<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
   String uid = (String)session.getAttribute("uid");
%>

<script>
function logoutMe()
{
   $.ajax({
      url : '/JavaWeb/login/userController.jsp',
      method:'post',
      data:{"cmd":"logout"},
      cache:false,
      dataType:'json',
      success:function(res){
         alert(res.logout? '로그아웃 성공':'로그아웃 실패');
         location.href='/JavaWeb/login/userController.jsp';
      },
      error : function(xhr,status,err){
         alert('에러:' + err);
      }
   });
}
</script>
<style>
   a#logout_link {margin-left:1em; background-color:rgb(255,200,200); font-size: small;}
</style>
<div><label><%=uid%></label>
   <a id="logout_link" href="javascript:logoutMe();">로그아웃</a>
</div>