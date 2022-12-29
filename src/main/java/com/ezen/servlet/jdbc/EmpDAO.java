package com.ezen.servlet.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

public class EmpDAO 
{
	//DB 입출력 (Data Access Object)
	//VO (Value Object)
	
	//먼저 DB에 접속하는 기능 필요 
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Connection getConn()
	{
		
	     try {
	         Class.forName("oracle.jdbc.OracleDriver"); 
	         conn = DriverManager.getConnection(	
	                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");	
	         this.conn = conn;	
	         return conn;
	         
	        } catch (Exception e) {
	            e.printStackTrace();;
	        }

	     return null; //접속 실패시 null
	}
	
	public List<EmpVO> getList() 
	{
		getConn(); // 이로써 밑에 conn객체가 초기화 됨
		
		try {
	        this.stmt = conn.createStatement();		//연결정보가 만들어져야 다음작업이 가능해지므로 createStatment 를 이용하여 Statement(SQL 문장을 다루는 객체)의 오브젝트 형성 - Statement를 이용해 SQL 전달
	        this.rs = stmt.executeQuery("SELECT * FROM emp2"); // 그 문장이 전달되어서 데이터 값으로 메모리에 로드된다
	    
	        List<EmpVO> list = new ArrayList<>(); // emp 값들을 담기 위해 List생성
	        while(rs.next()) // 결과 집합에 가서 다음행에다 포커스를 맞춰준다, 
	        {
	           int empno = rs.getInt("EMPNO");	// 그 결과집합(rs) 에 emp라는 컬럼의 정수의 값을 get해
	           String ename = rs.getString("ENAME");
	           java.sql.Date hiredate = rs.getDate("HIREDATE");
	           float sal = rs.getFloat("SAL");
	           int deptno = rs.getInt("DEPTNO");
	           //위 데이터를 다루기 EmpVO 객체 생성 , '한 행에서 나온 데이터가 한 개의 오브젝트가 되는 것임'
	           EmpVO emp = new EmpVO();
	           emp.setEmpno(empno);
	           emp.setEname(ename);
	           emp.setHiredate(hiredate);
	           emp.setSal(sal);
	           emp.setDeptno(deptno);
	           
	           list.add(emp);
	           
	          // System.out.printf("%d\t%s\t%s\t%f \n", empno,ename,hiredate,sal);
	        }
	        return list;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally { //finally 는 에러가 있던 없던 돌아감
			closeAll(); 
		}
		return null;
	}
	
	// 부서 번호가 전달되면 해당 부서의 모든 사원 정보를 리스트로 리턴하는 메소드를 정의하세요
	public List<EmpVO> getEmpByDeptno(int deptno) 
	{
		getConn(); // 이로써 밑에 conn객체가 초기화 됨
		
		try {
	        this.stmt = conn.createStatement();		
	        String sql = "SELECT * FROM emp2 WHERE deptno=" + deptno;
	        this.rs = stmt.executeQuery(sql); 
	    
	        List<EmpVO> list = new ArrayList<>(); // emp 값들을 담기 위해 List생성
	        while(rs.next()) // 결과 집합에 가서 다음행에다 포커스를 맞춰준다, 
	        {
	           int empno = rs.getInt("EMPNO");	// 그 결과집합(rs) 에 emp라는 컬럼의 정수의 값을 get해
	           String ename = rs.getString("ENAME");
	           java.sql.Date hiredate = rs.getDate("HIREDATE");
	           float sal = rs.getFloat("SAL");
	           //위 데이터를 다루기 EmpVO 객체 생성 , '한 행에서 나온 데이터가 한 개의 오브젝트가 되는 것임'
	          
	           EmpVO emp = new EmpVO();
	           emp.setEmpno(empno);
	           emp.setEname(ename);
	           emp.setHiredate(hiredate);
	           emp.setSal(sal);
	           
	           list.add(emp);
	           
	        }
	        return list;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally { //finally 는 에러가 있던 없던 돌아감
			closeAll(); 
		}
		return null;
	}
	
	//﻿사번을 전달하면 해당 사원의 정보를 화면에 표시하기
	public EmpVO detailByEmpno(int empno)
	{
		getConn(); 
		
		try {
	       	
	        String sql = "SELECT * FROM emp2 WHERE empno=?" ;
	        this.pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, empno);
	        
	        this.rs= pstmt.executeQuery();
	        
	        while(rs.next()) 
	        {
	           int eno = rs.getInt("EMPNO");	
	           String ename = rs.getString("ENAME");
	           java.sql.Date hiredate = rs.getDate("HIREDATE");
	           float sal = rs.getFloat("SAL");
	          int deptno = rs.getInt("DEPTNO");
	           
	          EmpVO emp = new EmpVO();
	           emp.setEmpno(eno);
	           emp.setEname(ename);
	           emp.setHiredate(hiredate);
	           emp.setSal(sal);
	           emp.setDeptno(deptno);
	           
	           return emp;   
	        }
	        
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally { 
			closeAll(); 
		}
		
		return null;
	}
	//부서번호만 뽑아오는 메소드
	public List<Integer> getDeptnoList() 
	{
		getConn(); 
		
		try {
	        this.stmt = conn.createStatement();		
	        String sql = "SELECT DISTINCT deptno FROM emp2 ORDER BY deptno" ;
	        this.rs = stmt.executeQuery(sql); 
	    
	        List<Integer> list = new ArrayList<>();
	        while(rs.next())
	        {
	           int deptno = rs.getInt("DEPTNO"); //부서번호 빼내서
	           list.add(deptno);        //리스트에 저장
	        }							//DAO는 데이터 입출력 내용만 따라서 json은 서비스에서
	        return list;					//이제 이걸 서비스에서 호출해서 jsonarray로 변환
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally { 
			closeAll(); 
		}
		return null;
	}
	// 사원 이름이 전달되면 해당 이름의 사원 정보를 리스트로 리턴하는 메소드
	public List<EmpVO> getEmpByEname(String ename) 
	{
		getConn(); 
		
		try {
			// this.stmt = conn.createStatement();		
			String sql = "SELECT * FROM emp2 WHERE ename=?"; 
			// PreparedStatement pstmt = conn.prepareStatement(sql); //preparedStatement는 만들때부터 sql 값을 요구함
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ename);	// 첫번째 물음표에다 ename을 집어넣어라 , sql 문장 완성
			
	       // this.rs = stmt.executeQuery(sql); 
			this.rs= pstmt.executeQuery(); // 위에서 이미 sql문장을 주었음으로 그냥 실행하면 됨
	    
	        List<EmpVO> list = new ArrayList<>(); 
	        while(rs.next()) 
	        {
	           int empno = rs.getInt("EMPNO");	
	           String name = rs.getString("ENAME");
	           int deptno = rs.getInt("DEPTNO");
	           java.sql.Date hiredate = rs.getDate("HIREDATE");
	           float sal = rs.getFloat("SAL");
	          
	           EmpVO emp = new EmpVO();
	           emp.setEmpno(empno);
	           emp.setEname(name);
	           emp.setDeptno(deptno);
	           emp.setHiredate(hiredate);
	           emp.setSal(sal);
	           
	           list.add(emp);
	           
	        }
	        return list;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally { 
			closeAll(); 
		}
		return null;
	}
	public boolean update(EmpVO emp)
	{
		getConn();
		
		try {
			String sql = "UPDATE emp2 SET deptno=?, sal=? WHERE empno=? " ;
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getDeptno());
			pstmt.setFloat(2, emp.getSal());
			pstmt.setInt(3, emp.getEmpno());
		
			int rows=pstmt.executeUpdate();
			return rows >0 ? true : false;
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			closeAll(); 
		}
		
		return false;
		
	}
	public boolean deleteEmp(EmpVO emp)
	{
		getConn();
		
		String sql= "DELETE FROM emp2 WHERE empno=?";
		
		try {
			this.pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			
			int rows= pstmt.executeUpdate();
			return rows >0? true: false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			closeAll(); 
		}
		return false;
	}
	public boolean addEmpForm(EmpVO emp)
	{
		getConn();
		
		String sql ="INSERT INTO emp2 (empno, ename, hiredate, sal, deptno) VALUES(?,?,?,?,?)";
		
		try {
			this.pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setDate(3, emp.getHiredate());
			pstmt.setFloat(4, emp.getSal());
			pstmt.setInt(5, emp.getDeptno());
			
			int rows= pstmt.executeUpdate();
			return rows>0? true: false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	public int getMaxNo() //가장 큰 이용자 번호 가져오기
	{
		getConn();
		
		String sql = "SELECT MAX(empno) AS mxno FROM emp2";
		try {
			this.pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int nextEmpno = rs.getInt("MXNO");
			return nextEmpno;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return 0;
	}
	private void closeAll() 
	{
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
