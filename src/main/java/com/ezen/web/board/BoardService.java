package com.ezen.web.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.ezen.web.hello.Board;

public class BoardService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	private String viewPath = "/WEB-INF/jsp/model2";
	private String fpath = "D:/test/boardList.ser";
	private String uid;
	public BoardService() {}
	
	public BoardService(HttpServletRequest request, 
			HttpServletResponse response)  // BoardServlet에서 String viewPath = new BoardService(request).exec() 를 해줘야해서 기본생성자 필요 
	{
		this.request = request;
		this.session = request.getSession(); 
		this.response = response;
		
	}
	
	public String exec()
	{
	
		//요청 처리 
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("list"))
		{
			List<Board> list = getList();	//이제 이걸 view에 전달하려면 request 에 담아주면 된다
			request.setAttribute("list", list); // (list에 있는 모든 Board들을 보여주고자 함)
			return viewPath+ "/boardList.jsp"; //이렇게 Service에 리턴값으로 View의 경로를 줌으로써 Servlet이 하는 일을 줄일 수 있다.
			//데이터를 request에 저장, 저장된 건 Servlet으로 가서 jsp까지 전달됨
			//응답용 View 리턴
		}
		else if(cmd.equals("addform")) {
			return viewPath+"/boardAddForm.jsp";
		}
		else if(cmd.equals("add"))
		{
			Board board = createBoardFromParam();
			boolean added = add(board);
			
			Map<String, Object> map = new HashMap<>();
			map.put("added", added);
			sendJSONStr(map);
			
			// 추가 폼 처리
			/* 내가 한 방법
			Board board = new Board();
			boolean added = add(board);
			board.setTitle(request.getParameter("title")); //boardAddForm의 폼에서 name을 해준 이유가 여기서 request.getParameter하면 나오는 애들임
			board.setContents(request.getParameter("contents"));
			
			jsObj.put("added", added); //이렇게 json에 위 boolean값에 있던 added 를 넣어줘서 넘겨준다
			String jsStr = jsObj.toJSONString();	// 이제 이 문자열을 브라우저로 보내주면됨, 응답기능이 있는 response로
			try {
				PrintWriter out = response.getWriter(); //서비스가 ajax로 넘어온 요청을 응답해주는 response
				out.print(jsStr);
				out.flush();
				return viewPath+ "/boardAddForm.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			
			}
			return null;*/
		}
		else if(cmd.equals("read")) { //조회수 올림
			Board board = createBoardFromParam();
			board = read(board);
			request.setAttribute("board", board);
			
			uid= (String)session.getAttribute("uid");
			String author = board.getAuthor();
			boolean owned = uid.equals(author);
			request.setAttribute("owned", owned);
			return viewPath+ "/boardRead.jsp";
			
		}
		else if(cmd.equals("find")) { //조회수 안올림 
			
			Board board = createBoardFromParam(); //num을 받아온 Board객체가 생성되어 그걸 변수 board로 받음
			board = find(board); //이제 그 num을 갖고 있는 변수 board를 find()메소드에 넣어 실행하면 새 보드 객체가 나오는데
								// 위에서 쓴 Board board는 새로 사용하면서 refresh 되었으므로 재활용하는 거임
								// Board found = find(board) 이렇게 해도 괜찮음 
			request.setAttribute("board", board);
			
			uid = (String)session.getAttribute("uid");
			String author = board.getAuthor();
			boolean owned = uid.equals(author);
			request.setAttribute("owned", owned);
			return viewPath+ "/boardRead.jsp";
		}
		else if(cmd.equals("edit")) {
			
			Board board = createBoardFromParam(); 
			board = find(board);
			request.setAttribute("board", board); 
		
			
			return viewPath + "/boardEdit.jsp";
			
		}
		else if(cmd.equals("update")) {
			Board board = createBoardFromParam();
			boolean updated = update(board);
			
			Map <String, Object> map = new HashMap<>();
			map.put("updated", updated);
			sendJSONStr(map);
		}
		else if(cmd.equals("delete")) {
			Board board = createBoardFromParam();
			boolean deleted = delete(board);
			
			Map<String, Object> map = new HashMap<>();
			map.put("deleted", deleted);
			sendJSONStr(map);
		
		}
		
		return null;
		
	}
		
	private void sendJSONStr(Map<String, Object> map) {
		JSONObject jsObj = new JSONObject(map); //위에서 map에 넣어논 key&value 를 JSON에 넣어놓으면 jsObj.put("key", key) 할 필요 없음
		String jsStr = jsObj.toJSONString();	// 이제 이 문자열을 브라우저로 보내주면됨, 응답기능이 있는 response로
		try {
			PrintWriter out = response.getWriter(); //서비스가 ajax로 넘어온 요청을 응답해주는 response
			out.print(jsStr);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}

	private Board createBoardFromParam() {
		
		String sNum= request.getParameter("num");
		String title= request.getParameter("title");
		String contents= request.getParameter("contents");

		Board board= new Board();
		if(sNum!=null) board.setNum(Integer.valueOf(sNum)); //얘는 null값이면 돌아가지 않음= 번호가 null이면 불러올 수 있는게 없으니까 
		board.setTitle(title); //참조변수는 null값와도 아무 상관없음
		board.setContents(contents);
		
		/*내가 한 방법
		String cmd = request.getParameter("cmd"); //요청한 cmd 받아와야함 
		Board board = new Board();
		if(cmd.equals("add")) { //num이 넘어오지 않는 경우, 대신 setTitle과 setContents해줘야 하는 경우
			board.setTitle(request.getParameter("title"));
			board.setContents(request.getParameter("contents"));
		}else {
			int num = Integer.parseInt(request.getParameter("num"));
			board.setNum(num);
		}*/
		
		return board;
	}

	private List<Board> getList()
	{
		// 게시글 목록을 로드한다
		return deserialize();
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
	
	
}
