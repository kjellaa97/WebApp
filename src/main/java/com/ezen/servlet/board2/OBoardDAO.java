package com.ezen.servlet.board2;

import java.sql.*;
import java.util.*;

public class OBoardDAO 
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Connection getConn() {	//Oracle 접속 기능
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			this.conn= conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Map<String, Object> getList(int pn){ // 게시판 리스트 갖고오기 
		
		getConn();
		
		try {
			String sql = "SELECT * FROM get_pg WHERE page=?";
			
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pn);
			this.rs = pstmt.executeQuery();
			
			
			List<OBoardVO> list = new ArrayList();
			System.out.println("리스트:" +list);
			Map<String, Object> map = new HashMap<>();
			map.put("list", list);					
		
			
			while(rs.next())
			{
				int boardid = rs.getInt("BOARDID");
				String title = rs.getString("TITLE");
				String author = rs.getString("AUTHOR");
				int hitcnt = rs.getInt("HITCNT");
				java.sql.Date regdate =  rs.getDate("REGDATE");
				
				OBoardVO oboard = new OBoardVO();
				oboard.setBoardid(boardid);
				oboard.setTitle(title);
				oboard.setAuthor(author);
				oboard.setHitcnt(hitcnt);
				oboard.setRegdate(regdate);
				
				list.add(oboard);
				
				int ttlpages = rs.getInt("TTLPAGES");	//rs에서 넘어온 ttlpages값을 받아준다
				map.put("ttlpages",ttlpages );			// 그걸 map 에 넣어놔얄 board 객체들 뿐만 아니라 ttlpages 값도 저장된다
			}
			System.out.println("가져온 map:"+ map.size());
			return map;
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		
		return null;
	}
	
	public boolean writeOboard(OBoardVO oboard) //새 게시판 글 추가 
	{
		getConn();
		
		String sql = "INSERT INTO board (boardid, title, contents,parentid, author) VALUES (DB_BD_SEQ.NEXTVAL,?,?,?,?)";
		
		try {
			this.pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, oboard.getTitle());
			//System.out.println("getTitle:"+ oboard.getTitle());
			pstmt.setString(2, oboard.getContents());
			pstmt.setInt(3, oboard.getParentid());
			pstmt.setString(4, oboard.getAuthor());
			
			int rows= pstmt.executeUpdate();
			return rows>0? true: false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	
	public OBoardVO findOboard(int id) //게시판 글 보여주기
	{
		getConn(); 
		
		try {
			String sql = "UPDATE board SET hitcnt = hitcnt+1 WHERE boardid=?";
			this.pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();	//히트수 먼저 업뎃해주고, 게시판 글 가져오자
			
			String sql2 = "SELECT * FROM board WHERE boardid=?";
			this.pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int boardid= rs.getInt("BOARDID");
				String author = rs.getString("AUTHOR");
				String title =rs.getString("TITLE");
				String contents =rs.getString("CONTENTS");
				int hitcnt =rs.getInt("HITCNT");
				java.sql.Date regdate= rs.getDate("REGDATE");
				
				OBoardVO oboard = new OBoardVO();
				oboard.setBoardid(boardid);
				oboard.setAuthor(author);
				oboard.setTitle(title);
				oboard.setContents(contents);
				oboard.setHitcnt(hitcnt);
				oboard.setRegdate(regdate);
				
				//System.out.println("DB oboard 값: "+oboard);
				return oboard;
			}	
		 }catch (Exception e) {
				e.printStackTrace();
		 }finally {
			 closeAll();
		 }
		//System.out.println("findOboard 종료");
		return null;
	}
	public boolean updateOboard(OBoardVO oboard) //게시판 글 수정하기
	{
		getConn();
		
		try {
			String sql = "UPDATE board SET title=?, contents=? WHERE boardid=? " ;
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oboard.getTitle());
			pstmt.setString(2, oboard.getContents());
			pstmt.setInt(3, oboard.getBoardid());
		
			int rows=pstmt.executeUpdate();
			return rows >0 ? true : false;
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			closeAll(); 
		}
		return false;
	}
	public boolean deleteOboard(int id) //삭제하기
	{
		getConn();
		
		try {
			String sql ="DELETE FROM board WHERE boardid=?";
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int rows= pstmt.executeUpdate();
			return rows>0? true: false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void closeAll()
	{
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
