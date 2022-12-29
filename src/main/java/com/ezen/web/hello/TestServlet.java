package com.ezen.web.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test")
public class TestServlet extends HttpServlet 
{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<! doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");

		out.println("<title>서블릿으로 응답 만들기</title>");
		out.println("<style>");
		out.println("h1{color:red;}");
		out.println("</style>");
		
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>서블릿으로 html 응답하기 </h1>");
		

		out.println("</body>");

		out.println("</html>");
		out.flush();
		
		
		
		
		
	}

}
