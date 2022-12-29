package com.ezen.servlet.lms2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.web.login.LoginService;


@WebServlet("/lmslogin" )
public class lmsLoginServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	request.setCharacterEncoding("utf-8");
		
	String viewPath = new lmsLoginSVC(request ,response).loginsvc();
		
		if(viewPath!=null) {
		getServletContext().getRequestDispatcher(viewPath).forward(request, response);
	
		}

	}
	
}
