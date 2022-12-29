package com.ezen.servlet.lms2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.servlet.board2.OBoardService;

@WebServlet( "/lms2" )
public class lmsServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String view = new LmsService(request, response).lmsSvc();
		
		if(view!=null) {
			getServletContext().getRequestDispatcher(view).forward(request, response);
		}

	}
	
}
