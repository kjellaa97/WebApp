package com.ezen.web.user;

import javax.servlet.http.HttpSession;

public class BoardUserService 
{
	private HttpSession session;
	
	public BoardUserService() {}
	
	public boolean login(BoardUser user)
	{
		if(user.getUid().equals("smith") && user.getPwd().equals("1111"))
		{
			session.setAttribute("uid", user.getUid()); //이렇게 session에다가 로그인 한 사람의 아이디를 기억시킨다
			 
			return true;
		}
		return false;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
}
