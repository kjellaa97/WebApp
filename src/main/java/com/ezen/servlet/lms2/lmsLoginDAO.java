package com.ezen.servlet.lms2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class lmsLoginDAO 
{
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Connection getConn() { //오라클 접속 기능 형성
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn= DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			this.conn= conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean register(lmsLoginVO login)	//회원가입 화면에서 입력된 userid &pwd DB에 저장해주기 
	{
		getConn();
		
		String sql= "INSERT INTO lms_user (userid, pwd) VALUES(?,?)";
		
		try {
			this.pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, login.getUserid());
			pstmt.setString(2, login.getPwd());
			
			int rows= pstmt.executeUpdate();
			return rows>0? true: false; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	public List<lmsLoginVO> getList() //회원 목록 가져오기 
	{
		getConn();
		
		String sql = "SELECT * FROM lms_user ";
		
		try {
			this.pstmt= conn.prepareStatement(sql);
			this.rs = pstmt.executeQuery();
			
			List<lmsLoginVO> list = new ArrayList<>();
			
			while(rs.next())
			{
				String userid = rs.getString("USERID");
				String pwd= rs.getString("PWD");
				String lms_admin = rs.getString("lms_admin");
				
				lmsLoginVO loginVO = new lmsLoginVO();
				loginVO.setUserid(userid);
				loginVO.setPwd(pwd);
				loginVO.setLms_admin(lms_admin);
				
				list.add(loginVO);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public void closeAll() {
		
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
}
