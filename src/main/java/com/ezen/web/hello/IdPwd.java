package com.ezen.web.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet( "/idpwd" )
public class IdPwd extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get 방식 요청을 사용하여 id,pwd 전달하면 
		// 서블릿에서 인증하여 그 결과를 로그인 성공/실패를 화면에 표시해보세요
		
		// 만약 id, pwd가 전달되지 않은 상태로 서블릿이 호출되면 
		//"id, pwd 는 필수 입력 항목입니다" 를 표시하기
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		
		String pwd = request.getParameter("pwd");
		
		String result = null;
		if(id==null || pwd == null) 	// 이걸 위에다 놔야지 안그럼 처음부터 null이면 에러나니까 
		{
			result = "id, pwd 는 필수 입력 항목입니다";
		}
		else if(id.equals("smith")&& pwd.equals("smith"))	// id가 null이면 null.equals는 할 수 없음, null은 객체도 아니니까
		{
			result = "로그인 성공";
		}else {
			result = "로그인 실패";
		}
		
			
		String html ="";
		html += "<! doctype html>";
		html += "<html>";
		html += "<head>";
		html += "<title> 로그인 결과 </title>";
		html += "<body>";
		
		//<form>을 사용하여 로그인 할 수 있는 양식을 작성하고 LoginServlet으로 전송
		html += "<form action= 'idpwd' method='post'>";
		html += "<div>아이디<input type= 'text' name='id' value='smith'></div>";	//<div> tag는 블럭치는거임 
		html += "<div>비밀번호<input type= 'password' name= 'pwd' value='smith'></div>";
		html += "<div><button type = 'submit'> 로그인 </button></div>";
	
		html += "<h5>" + result+ "</h5>";
			
		html += "</body>";
		html += "</form>";
		html += "</head>";
		html += "</html>";
		
		out.println(html);
		out.flush();
		
		
		
	}

}
