package com.ezen.web.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public class LoginService 
{
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String viewPath = "/WEB-INF/jsp/model2";
	
	   
	public LoginService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.session = request.getSession();
		this.request = request;
		this.response = response;
	}

	public LoginService() {}


	public String startloginsvc() {
		
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("loginForm"))
		{
			return viewPath+ "/loginForm.jsp";

		}else if(cmd.equals("login"))
		{
			LoginUser user = new LoginUser();
			boolean pass = login(user);
			JSONObject jsObj = new JSONObject();
			jsObj.put("pass", pass);
			jsObj.put("url", (String)session.getAttribute("url"));
		
			try {
				PrintWriter out = response.getWriter();
				out.print(jsObj.toJSONString());
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("logout")) {
			
			boolean logout = logout();
			JSONObject jsObj = new JSONObject();
			jsObj.put("logout", logout);
			
			try {
				PrintWriter out  = response.getWriter();
				out.print(jsObj.toJSONString());
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//이게 필요가 없음. 왜? 이건 그냥 ajax전송이니까 
			//return viewPath + "/logoutForm.jsp";
		}
		return null;
	}
	

       private static Map<String, String> userMap= new HashMap<>();
    
	   static { //static블럭 
	      userMap.put("smith","1234");
	      userMap.put("scott","2222");
	   }
	  
	   public boolean login(LoginUser user)
	   {
	      Set<String> keyset =userMap.keySet(); //key가 들어가 있는 집합 (smith 와 scott)
	      Iterator<String> it= keyset.iterator(); 
	      while(it.hasNext()) //다음거가 있으면
	      {
	         String id= it.next(); //다음거를 보여줘라 
	         String pw = userMap.get(id);
	        
	         user.setUid(request.getParameter("uid"));
	         user.setPwd(request.getParameter("pwd"));
	       
	         if(user.getUid().equals(id)&&user.getPwd().equals(pw))
	         {
	            session.setAttribute("uid", user.getUid());
	            return true;
	         }
	      }
	      return false;
	   }
	   
	   public boolean logout()
	   {
	      session.invalidate(); 
	      return true;
	   
	   }


	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	
}
