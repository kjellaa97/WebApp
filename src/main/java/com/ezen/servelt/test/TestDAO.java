package com.ezen.servelt.test;

import java.sql.*;
import java.util.*;

public class TestDAO 
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Connection getConn() {
		
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
	
	public List<TestVO> getList(){ 
		
		getConn();
		
		try {
			String sql = "SELECT * FROM LoginT";
			
			this.pstmt = conn.prepareStatement(sql);
			this.rs = pstmt.executeQuery();
			
			List<TestVO> list = new ArrayList();					
			while(rs.next())
			{
				int idx1 = rs.getInt("IDX1");
				String name1 = rs.getString("NAME1");
				String pwd1 = rs.getString("PWD1");
	
				TestVO vo = new TestVO();
				vo.setIdx1(idx1);
				vo.setName1(name1);
				vo.setPwd1(pwd1);

				list.add(vo);
				
			}
			return list;
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
