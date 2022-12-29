package com.ezen.servlet.board2;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class OBoardService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String path= "/WEB-INF/jsp/oboard";
	
	public OBoardService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
	}
	
	public String exec()
	{
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("list"))
		{
			Map<String, Object> map = getList();// DAO에서 게시판 글 리스트 & 총페이지수 받아오기 
			List<OBoardVO> list = (List<OBoardVO>)(map.get("list"));
			
			System.out.println("map 사이즈:"+ map.size());
			int ttlpages = (Integer)(map.get("ttlpages"));
			
			request.setAttribute("list", list);	//받아온 게시판 글 리스트를 request에 저장하기, 왜? jsp에 forward되야 되서
			request.setAttribute("ttlpages", ttlpages);
			
	
			/*
			List<Integer> pglist = new ArrayList<>();
			for(int i=1 ; i<= ttlpages ;i++)
			{
				pglist.add(i);
			}
			request.setAttribute("pglist", pglist);
			*/
			
		return path+ "/oboardList.jsp";
		}
		else if(cmd.equals("pgnum"))
		{
			Map<String, Object> map = getList();
			int pgnum = (Integer)(map.get("ttlpages"));
			request.setAttribute("pgnum", pgnum);
		}
		else if(cmd.equals("directWrite") || cmd.equals("reply") )
		{
			
			if(cmd.equals("reply")) {
				OBoardVO oboard =findOBoard(); //findOBoard를 해야하는 이유: 답글달려는 boardid랑 title 등이 넘어와야하니까
				System.out.println("넘어온 보드값:"+ oboard.getBoardid());
				
				request.setAttribute("parentBoard", oboard);	
				
				//System.out.println("parentBoard:" +oboard);
				
			}
			return path+"/oboardWrite.jsp";	
		}
		else if(cmd.equals("write"))
		{
			
				OBoardVO oboard =getOBoardParam();
				boolean written = getOboard(oboard);
			//	System.out.println("DB에 넣음:"+ written);
				Map<String, Object> map = new HashMap<>();
				map.put("written", written);
				sendJSONStr(map);
				//return path+"/oboardWrite.jsp"; 위에서 제이슨을 내보내는 건데 리턴까지 있으면 오류남
			
		}
		else if(cmd.equals("findOboard")) 
		{
			int oboardid = Integer.valueOf(request.getParameter("boardid"));
			OBoardVO oboard = detailByID(oboardid);
			request.setAttribute("oboard", oboard);
			return path+"/oboardDetail.jsp";
		}
		else if(cmd.equals("editoBoard"))
		{
			int oboardid = Integer.valueOf(request.getParameter("boardid"));
			OBoardVO oboard = detailByID(oboardid);
			request.setAttribute("oboard", oboard);
			
			return path+"/oboardEdit.jsp";
		}
		else if(cmd.equals("updateoBoard"))
		{
			int boardid=Integer.valueOf( request.getParameter("boardid"));
			String title = request.getParameter("title");
			String contents =request.getParameter("contents");

			OBoardVO oboard = new OBoardVO();
			oboard.setBoardid(boardid);
			oboard.setTitle(title);
			oboard.setContents(contents);
			
			boolean updated = updateOboard(oboard);

			Map<String, Object> map = new HashMap<>();
			map.put("updated", updated);
			sendJSONStr(map);
		}
		else if(cmd.equals("deleteoBoard"))
		{
			int boardid=Integer.valueOf(request.getParameter("boardid"));
			OBoardVO oboard = new OBoardVO();
			oboard.setBoardid(boardid);
			
			boolean deleted = deleteOboard(boardid);
			
			Map<String, Object> map = new HashMap<>();
			map.put("deleted", deleted);
			sendJSONStr(map);
			
		}

		return null;
	}
	
	public OBoardVO getOBoardParam()
	{
		String strid = request.getParameter("boardid");
		String title =request.getParameter("title");
		String contents =request.getParameter("contents");
		String sPid = request.getParameter("parentid");
		String author = request.getParameter("author");

		//System.out.println("sPid:"+ sPid);
		//System.out.println("sPid==null "+ sPid==null);
		//System.out.println("sPid 빈문자 "+ sPid.equals(""));
		
		OBoardVO oboard = new OBoardVO ();
		if(strid!=null)oboard.setBoardid(Integer.valueOf(strid));
		if(sPid!=null && !sPid.equals(""))oboard.setParentid(Integer.valueOf(sPid));
		oboard.setTitle(title);
		oboard.setContents(contents);
		oboard.setAuthor(author);
		
		return oboard;
	}
	public void sendJSONStr(Map<String, Object> map)
	{
		JSONObject jsobj = new JSONObject(map);
		
		try {
			PrintWriter out = response.getWriter();
			out.print(jsobj.toJSONString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	public Map<String, Object> getList()
	{
		String strPgnum =request.getParameter("pgnum");
		
		int pgnum=1;
		if(strPgnum!=null)
		{
			pgnum = Integer.valueOf(strPgnum);

		}
		System.out.println("pgnum:" +pgnum);
		
		OBoardDAO dao = new OBoardDAO();
		Map<String, Object> map = dao.getList(pgnum);
		return map;

	}
	public OBoardVO findOBoard()
	{
		OBoardVO oboard = getOBoardParam();
	//	System.out.println("oboard값: "+ oboard);
		OBoardDAO dao = new OBoardDAO();
	
		//System.out.println("parentid" + oboard.getParentid());
		
		OBoardVO find = dao.findOboard(oboard.getBoardid());
	
	//	System.out.println("find값:" +find);
		return find;
	}
	public boolean getOboard(OBoardVO oboard)
	{
		OBoardDAO dao = new OBoardDAO();
		boolean written = dao.writeOboard(oboard);
		return written;
	}
	public OBoardVO detailByID(int id)
	{
		OBoardDAO dao = new OBoardDAO();
		OBoardVO oboard =dao.findOboard(id);
		return oboard;
	}
	public boolean updateOboard(OBoardVO oboard)
	{
		OBoardDAO dao = new OBoardDAO();
		boolean updated =dao.updateOboard(oboard);
		return updated;
	}
	public boolean deleteOboard(int id)
	{
		OBoardDAO dao = new OBoardDAO();
		boolean deleted = dao.deleteOboard(id);
		return deleted;
	}
}
