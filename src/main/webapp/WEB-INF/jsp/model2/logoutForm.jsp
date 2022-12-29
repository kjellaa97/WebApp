<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">
function logout()
{
	if(!confirm("로그아웃 하시겠습니까?")) return; 
   $.ajax({
      url : 'loginservlet',
      method:'post',
      data:{"cmd":"logout"},
      cache:false,
      dataType:'json',
      success:function(res){
         alert(res.logout? '로그아웃 성공':'로그아웃 실패');
         location.href='board';
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
<div><label>${uid}</label>
   <a id="logout_link" href="javascript:logout();">로그아웃</a>
</div>