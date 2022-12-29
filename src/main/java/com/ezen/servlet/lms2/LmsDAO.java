package com.ezen.servlet.lms2;

import java.sql.*;
import java.util.*;

public class LmsDAO 
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
	
	//테스트 문제를 DB에서 가져오는 법 
	public List<Lms_testVO> getTest(Lms_testVO testVO)
	{
		getConn();
		
		String sql = "SELECT lms_num, lms_tnum, lms_question, lms_anum FROM get_lms WHERE lms_num=?";
		
		try {
			this.pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, testVO.getLms_num());
			pstmt.setString(1, "1.1");
			this.rs=pstmt.executeQuery();
			
			List<Lms_testVO> list = new ArrayList<>();
		
			while(rs.next()) 
			{
				String lms_num = rs.getString("lms_num");
				String lms_tnum = rs.getString("lms_tnum");
				String lms_question = rs.getString("lms_question");
				String lms_anum = rs.getString("lms_anum");
				
				Lms_testVO test = new Lms_testVO();
				test.setLms_num(lms_num);
				test.setLms_tnum(lms_tnum);
				test.setLms_question(lms_question);
				test.setLms_anum(lms_anum);
				
				list.add(test);
			}
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	public boolean addans(Lms_statusVO lms)
	   {
	      getConn();
	      try {
	         String sql="INSERT INTO lms_status(lms_qid,lms_num,lms_tnum,lms_Question,"
	               + "lms_anum,lms_aid,userid, test_date,lvl_code)"
	               + "VALUES (?,?,?,?,?,?,?,localtimestamp,?)";
	         this.pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, lms.getLms_qid());
	         pstmt.setString(2, lms.getLms_num());
	         pstmt.setString(3, lms.getLms_tnum());
	         pstmt.setString(4, lms.getLms_question());
	         pstmt.setString(5, lms.getLms_anum());
	         pstmt.setInt(6, lms.getLms_aid());
	         pstmt.setString(7, lms.getUserid());
	         pstmt.setInt(8, lms.getLvl_code());
	         
	         int rows=pstmt.executeUpdate();
	         return rows>0?true:false;
	         
	      }catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	         closeAll();
	      }
	      return false;
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
