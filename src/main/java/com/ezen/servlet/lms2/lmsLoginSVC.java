package com.ezen.servlet.lms2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class lmsLoginSVC 
{
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String viewPath= "/WEB-INF/jsp/lms/login";
	
	public lmsLoginSVC() {}
	
	public lmsLoginSVC(HttpServletRequest request, HttpServletResponse respone)
	{
		this.session= request.getSession();
		this.request= request;
		this.response= response;
		
	}
	public String loginsvc() {
	
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("loginForm"))
		{
			return viewPath + "/loginpg.jsp"; //로그인 화면 
		}
		else if(cmd.equals("register"))
		{
			String userid = request.getParameter("userid");
			String pwd = request.getParameter("pwd");
			
			lmsLoginVO loginVO = new lmsLoginVO();			
			loginVO.setUserid(userid);
			loginVO.setPwd(pwd);
			
			boolean registered = register(loginVO);
			
			return viewPath + "/register.jsp"; //회원가입 화면 
		}
		else if(cmd.equals("login"))
		{
			
		}
		else if(cmd.equals("test"))
		{
			return viewPath+ "/lms_test.jsp";
		}
		return null;
	}
	
	public boolean register(lmsLoginVO loginVO)
	{
		lmsLoginDAO dao = new lmsLoginDAO ();
		boolean registered = dao.register(loginVO);
		return registered; 
	}
	public boolean login(lmsLoginVO loginVO) //DAO에서 회원정보를 가져와 있는 지 확인하기 
	{
		lmsLoginDAO dao = new lmsLoginDAO ();
		List<lmsLoginVO> list = dao.getList();
		
		//if(list.contains(loginVO.getUserid() && loginVO.getPwd()) && Integer.valueOf(loginVO.getLms_admin())=0)) 
		{
			
		}
		
		return false;
	}
	
	

}
