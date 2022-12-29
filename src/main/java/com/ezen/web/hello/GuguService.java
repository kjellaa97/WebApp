package com.ezen.web.hello;

import javax.servlet.http.HttpServletRequest;

public class GuguService 
{
	HttpServletRequest request;
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String createGugu()
	{
		String strDan = request.getParameter("dan"); 
		int dan = strDan ==null? 2: Integer.parseInt(strDan);
		
		String data = "";
		for(int i=1; i<=9; i++)
		{
			data += String.format("%d * %d = %d<br>", dan, i, dan*i);
		}
		
		return data;
		
	}


}
