package com.ezen.web.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/guguc")
public class GuguController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		GuguService g = new GuguService();
		g.setRequest(request);
		String data = g.createGugu();
		//MVC :
		// M: Model(Data) java 활용, V: View(JSP 활용) , C: Controller(Servlet 활용)

		// Contorller는 다른 로직들의 브릿지 역할 
		//여기까지가 contorller가 통제하고 이걸 View 에게 전달
		request.setAttribute("data", data); //request객체에다가 data를 "data"라 이름을 붙여서 줌
		getServletContext().getRequestDispatcher("/gugu.jsp").forward(request, response); //위에서 온 요청과 응답을 jsp로 보내버리겠다. 
	}

}
