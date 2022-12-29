package com.ezen.servelt.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String path= "/WEB-INF/jsp/test";
	
	public TestController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
	}

	public String exec()
	{
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("list"))
		{
			TestService svc = new TestService();
			List<TestVO> list =svc.getList();
			request.setAttribute("list", list);
			return path +"/list.jsp";
		}
		return null;
	}
	
	
}
