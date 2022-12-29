package com.ezen.web.login2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginservlet_oboard" )
public class LoginServlet_oboard extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String viewPath = new LoginService_oboard(request,response).startloginsvc();
	if(viewPath!=null) {
		getServletContext().getRequestDispatcher(viewPath).forward(request, response);
	}
	}

}
