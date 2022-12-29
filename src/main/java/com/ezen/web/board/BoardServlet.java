package com.ezen.web.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet( "/board" )
public class BoardServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String uid = (String) session.getAttribute("uid"); //LoginService.java에서 set해줌
		if(uid==null) {
			session.setAttribute("url", request.getRequestURL().toString());
			response.sendRedirect("loginservlet");
			return; //return이 없으면 그 밑에 코드도 돌아가게된다. 밑에 코드는 보드서비스로 보내서 cmd받아내는 것이기 때문에 두 코드 중 뭐를 돌려야 할 지 충돌이 일어난다
		}
		
		
		
		String viewPath = new BoardService(request, response).exec(); // 여기서 request는 위의 HttpServletRequest request 의 request임
															// 이렇게 해주는 이유는 tomcat 이 전달해주는 request가 처음엔 null
		/* 아래처럼 3줄을 쓸바에는 위에처러 간편하게 한줄로!
		BoardService svc = new BoardService();
		svc.setRequest(request);
		String viewPath = svc.exec();*/
		
		if(viewPath!=null) {
		getServletContext().getRequestDispatcher(viewPath).forward(request, response);
		}
		
	
	}

}
