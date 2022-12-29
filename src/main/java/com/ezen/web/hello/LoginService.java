package com.ezen.web.hello;

import javax.servlet.http.HttpServletRequest;

public class LoginService 
{
	private HttpServletRequest request;

	public LoginService() {}
	public LoginService(HttpServletRequest request) {
		this.request = request;
	}

	public boolean login() {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
	 if(id.equals("smith")&& pwd.equals("smith"))	// id가 null이면 null.equals는 할 수 없음, null은 객체도 아니니까
		return true;
	 else return false;
	}
}
