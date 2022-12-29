package com.ezen.servlet.lms2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LmsService 
{
	
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String viewPath= "/WEB-INF/jsp/lms/login";
	
	public LmsService() {}
	
	public LmsService(HttpServletRequest request, HttpServletResponse response)
	{
		this.session = request.getSession();
		this.request= request;
		this.response= response;
		
	}
	public String lmsSvc() {
	
		String cmd = request.getParameter("cmd");
		if(cmd.equals("test"))
		{
			return viewPath+ "/lms_test.jsp";
		}
		else if(cmd.equals("getTest")) //강의 번호를 받아와서 DAO에 보내고, DAO에서 문제 데이터 불러오기 
		{
			//String lms_num = request.getParameter("lms_num"); //video에서 넘어오는 값
			Lms_testVO testVO = new Lms_testVO ();
			//testVO.setLms_num(lms_num);
			testVO.setLms_num("1.1");
			List<Lms_testVO> list = getTest(testVO);
			//System.out.println("서비스 list:" +list);
			//System.out.println("서비스 null:" + list==null );

			JSONArray jsArr= new JSONArray();
			for(int i=0;i<list.size(); i++)
			{
				testVO = list.get(i);
				JSONObject jsObj = new JSONObject();

				jsObj.put("lms_tnum", testVO.getLms_tnum());
				jsObj.put("lms_question", testVO.getLms_question());
				jsObj.put("lms_anum", testVO.getLms_anum());

				jsArr.add(jsObj);
			}
			String jsonStr = jsArr.toJSONString();
			responseJSONStr(jsonStr);
			
		}
		else if(cmd.equals("addans"))
	      {
	         boolean submitted = addans();
			 JSONObject jsObj = new JSONObject();
			 jsObj.put("submitted", submitted);
			 responseJSONStr( jsObj.toJSONString());
	      }
		
		return null;
	}
	
	public void responseJSONStr(String jsonStr)
	{
		try {
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Lms_testVO> getTest(Lms_testVO testVO)
	{
		LmsDAO dao = new LmsDAO();
		List<Lms_testVO> list =dao.getTest(testVO);
		return list;
	}
	public boolean addans()
	   {
	      int qid = Integer.parseInt(request.getParameter("lms_qid"));
	      String num = request.getParameter("lms_num");
	      String tnum= request.getParameter("lms_tnum");
	      String question = request.getParameter("lms_question");
	      String anum = request.getParameter("lms_anum");
	      int aid = Integer.parseInt(request.getParameter("lms_aid"));
	      String userid = request.getParameter("userid");
	      int lvl_code = Integer.parseInt(request.getParameter("lvl_code"));
	      
	      Lms_statusVO lms= new Lms_statusVO();
	      
	      lms.setLms_qid(qid);
	      lms.setLms_num(num);
	      lms.setLms_tnum(tnum);
	      lms.setLms_question(question);
	      lms.setLms_anum(anum);
	      lms.setLms_aid(aid);
	      lms.setUserid(userid);
	      lms.setLvl_code(lvl_code);
	      
	      LmsDAO dao = new LmsDAO();
	      boolean submitted = dao.addans(lms);
	      return submitted;
	   }
	
}
