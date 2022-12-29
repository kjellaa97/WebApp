package com.ezen.web.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/usercontrol" )
public class UserController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		System.out.println("전달된 cmd:" + cmd);
		if (cmd==null||cmd.equals("list"))
		{
			UserService us = new UserService(request);
			List<User> list= us.getlist();
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/user/userList.jsp").forward(request, response);
		}
		else if(cmd.equals("reg"))
		{
			UserService us = new UserService(request);
			boolean registered = us.userreg();
			//내가 한것 int num = us.getUser().getNum();
			int num = Integer.parseInt(request.getParameter("num"));
			out = response.getWriter(); // 응답을 userRegForm aJax에 넣어줌
			
			JSONObject jsObj = new JSONObject();
			jsObj.put("registered", registered);
			jsObj.put("num", num);
			//내가 한것 String json = "{\"registered\":"+registered+",\"num\":"+num+"}"; 
			// 내가 한것 out.println(json);
			out.println(jsObj.toJSONString()); //	toJSONString() 이 바로 위에 String json 임 
			out.flush();
			//request.setAttribute("registered", registered);
			//getServletContext().getRequestDispatcher("/user/userRegResult.jsp").forward(request, response);
			
		}
		else if(cmd.equals("form"))
		{
			getServletContext().getRequestDispatcher("/user/userRegForm.jsp").forward(request, response);
		
		}
		else if(cmd.equals("detail"))
		{
			UserService us = new UserService(request);
			User user = us.getUser();
			request.setAttribute("user", user);
			getServletContext().getRequestDispatcher("/user/userDetail.jsp").forward(request, response);	
		}
		else if(cmd.equals("findByName"))
		{
			UserService us = new UserService(request);
			List<User> list = us.searchUser();
			System.out.println("이름으로 검색 결과:" + list.size());
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/user/userSearchList.jsp").forward(request, response);
		}
		else if(cmd.equals("edit"))
		{
			UserService us = new UserService(request);
			User user = us.getUser();
			request.setAttribute("user", user);
			getServletContext().getRequestDispatcher("/user/userEdit.jsp").forward(request, response);
		}
		else if(cmd.equals("update"))
		{
			UserService us = new UserService(request);
			boolean updated = us.update();
			PrintWriter pw = response.getWriter();	// 응답을 만들어서 userEdit ajax에 던져줌 
			String json = "{\"updated\":"+updated+"}";
			out.println(json);
			out.flush();
			//request.setAttribute("updated", updated);
			//getServletContext().getRequestDispatcher("/user/updateResult.jsp").forward(request, response);
		}
	
		else if(cmd.equals("delete"))
		{
			UserService us = new UserService(request);
			boolean deleted = us.delete();
			out = response.getWriter();
			JSONObject jsObj = new JSONObject();
			jsObj.put("deleted", deleted);
			out.println(jsObj.toJSONString());
			//request.setAttribute("deleted", deleted);
			//getServletContext().getRequestDispatcher("/user/deleteResult.jsp").forward(request, response);
		}
		else if(cmd.equals("names"))
		{
			UserService us = new UserService(request);
			String jsonArrStr = us.getNameList();
			out= response.getWriter();
			out.println(jsonArrStr);
			out.flush();	
		}
		else if (cmd.equals("numList"))
		{
			UserService us = new UserService(request);
			String jsArrNum = us.getNumList();
			out = response.getWriter();
			out.println(jsArrNum);
			out.flush();
		}
		else if(cmd.equals("getUserJSON"))
		{
			UserService us = new UserService(request);	//를 이용해서
			out = response.getWriter();
			out.println(us.getUserJSONservice());	//사용자 정보를 JSON으로 서비스에서 가져와, 리턴하는 값은 스트링임
			out.flush();
		}
		else {
			System.err.println("예기치 못한 cmd가 전달되었습니다.");
		}
		
	}
}
