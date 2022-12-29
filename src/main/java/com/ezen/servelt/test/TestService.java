package com.ezen.servelt.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestService 
{

	public List<TestVO> getList()
	{
		  TestDAO dao = new TestDAO();
	      List<TestVO> list = dao.getList();
		return list;
	} 
		
}
	
