package com.ezen.servlet.jdbc;

import java.sql.Date;

public class EmpVO 
{
	private int empno;
	private String ename;
	private int deptno;
	private float sal;
	private java.sql.Date hiredate;
	
	public EmpVO() {}
	public EmpVO(int empno) {
		this.empno = empno;
	}
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public java.sql.Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(java.sql.Date hiredate) {
		this.hiredate = hiredate;
	}
	
	
}
