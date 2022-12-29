package com.ezen.servlet.jdbc;

import java.io.IOException;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/oracle")
public class OracleJDBCTest extends HttpServlet 
{
   
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
            IOException 
   {
	   String view = new EmpService(request, response).exec();
	   if(view !=null) {
	   /*
	   List<EmpVO> list= svc.getEmpByDeptno(20);//svc.getList();
	  // System.out.println("가져온 행수:" + list.size());
	   request.setAttribute("list", list);*/ // 이래야 jsp도 request영역에 속해서 받아볼수 있다
		   getServletContext().getRequestDispatcher(view).forward(request, response);
	   }
   }

}