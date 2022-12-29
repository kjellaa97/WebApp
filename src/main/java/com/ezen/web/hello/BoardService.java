package com.ezen.web.hello;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BoardService 
{
	private HttpServletRequest request;
	private HttpSession session;
	
	private String fpath = "D:/test/boardList.ser";
	
	public BoardService() {}

	public boolean add(Board board)
	{
		List<Board> list = deserialize(); // 파일에서 게시글 로드
		int num = 1;
		if(list.size()>0) {  // 기존 글이 존재한다면...
			Board last = list.get(list.size()-1);  // 최근글 추출
			num = last.getNum() + 1;  // 다음 글번호 산출
		}
		board.setNum(num);
		board.setAuthor((String) session.getAttribute("uid"));
		
		java.util.Date today = new java.util.Date();  //현재시간 가져오기
		java.sql.Date sqlDate = new java.sql.Date(today.getTime());
		board.setRegDate(sqlDate);
		
		boolean added = list.add(board);
		return added && serialize(list);
	}
	
	public List<Board> getList()
	{
		return deserialize();
	}
	
	public Board find(Board key) 
	{
		List<Board> list = deserialize();
		if(list.contains(key)) {
			return list.get(list.indexOf(key));
		}
		return null;
	}
	
	public Board read(Board key) //key 안에는 num이 들어있음
	{
		String cmd = request.getParameter("cmd");
		Board found = find(key);
		
		if(cmd.equals("FIND")) {
			return found;
		}
		// cmd=READ 인 경우에는 히트수 증가
		found.setHit(found.getHit()+1);
		List<Board> list = deserialize();
		list.get(list.indexOf(found)).setHit(found.getHit());
		serialize(list);
		return found;
	}
	
	public boolean update(Board key)
	{
		List<Board> list = deserialize();
		if(list.contains(key)) {
			Board found = list.get(list.indexOf(key));
			found.setTitle(key.getTitle());
			found.setContents(key.getContents());
			return serialize(list);
		}
		return false;
	}
	
	private boolean serialize(List<Board> list)
	{
		File f = new File(fpath);
		try {
			ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(f));
			oout.writeObject(list);
			oout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private List<Board> deserialize()
	{
		File f = new File(fpath);
		List<Board> list = null;
		if(!f.exists()) {
			list = new ArrayList<Board>();
		}else {
			try {
				ObjectInputStream oin = new ObjectInputStream(new FileInputStream(f));
				list = (List<Board>)oin.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public boolean delete(Board board)
	   {
	      List<Board> list = deserialize();
	      if(list.contains(board))
	      {
	         boolean deleted = list.remove(board);
	         return deleted && serialize(list);
	      }
	       return false;
	   }
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
}
