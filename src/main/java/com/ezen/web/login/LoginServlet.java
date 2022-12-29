package com.ezen.web.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/loginservlet" )
public class LoginServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String viewPath = new LoginService(request ,response).startloginsvc();
		
		if(viewPath!=null) {
			getServletContext().getRequestDispatcher(viewPath).forward(request, response);
		}
	}

}
