package com.ezen.servlet.board2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet( "/oracleboard2" )
public class OracleBoard2 extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//로그인 안한 경우 로그인하게끔 해주는 메소드
		//먼저 서비스에서 session에 저장된 id가 있는지 확인
		
		HttpSession session = request.getSession();
		
		String uid= (String)session.getAttribute("uid"); 
		if(uid==null)
		{
			session.setAttribute("url", request.getRequestURI().toString());
			response.sendRedirect("loginservlet_oboard");
			return;
		}else {
			session.setAttribute("uid", uid); //session 에 uid저장해야지 나중에 write 폼에서 EL로 불러올 수 있음
		}
		
		String view = new OBoardService(request, response).exec();
		if(view!=null) {
			getServletContext().getRequestDispatcher(view).forward(request, response);
			//getRequestDispatcher(String 문자열만 가능) 이여서 서비스에서는 cmd 받는 메소드를 String으로 설정해준 것!
		}
	}

}
	