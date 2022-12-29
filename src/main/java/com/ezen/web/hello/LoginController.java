package com.ezen.web.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*화면 출력: 로그인 성공/ 실패
 * http://localhost/JavaWeb/loginc?cmd=form : 이용자에게 로그인폼 보여줌
 * 
 * 로그인 폼에서 action = 'loginc'
 * <form action= 'loginc' method='post'>
 * 		<input type='hidden' name='cmd' value='login'>
 * 		<input type='text' name="uid">
 * 		<input type="password" name="pwd">
 * </form>
 * 
 */

@WebServlet("/loginc")
public class LoginController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd"); // 명령을 파라미터 이름으로 설정
		
		if(cmd==null || cmd.equals("form")) {
			getServletContext().getRequestDispatcher("/loginForm.jsp").forward(request, response);

		}else if(cmd.equals("login")) {
			LoginService ls = new LoginService(request);
			boolean success = ls.login();
			String msg = success? "로그인 성공": "로그인 실패";
			request.setAttribute("msg", msg);
			getServletContext().getRequestDispatcher("/loginResult.jsp").forward(request, response);

			
		}

	}

}
