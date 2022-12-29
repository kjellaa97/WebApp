package com.ezen.servlet.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EmpService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String viewPath = "/WEB-INF/jsp/emp";
	
	public EmpService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request= request;
		this.response = response;
		this.viewPath = viewPath;
	}
	
	public String exec()
	{
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("list"))
		{
			List<EmpVO> list = getList();
			request.setAttribute("list", list);
			return viewPath +"/empList.jsp";
		}
		else if(cmd.equals("getEmpByDeptno")) 
		{
			int deptno = Integer.valueOf(request.getParameter("deptno"));
			List<EmpVO> list = getEmpByDeptno(deptno);
			request.setAttribute("list", list);
			return viewPath +"/empList.jsp";
			
		}
		else if(cmd.equals("detailByEmpno"))
		{
			int empno = Integer.valueOf(request.getParameter("empno"));
			EmpVO emp = detailByEmpno(empno);
			request.setAttribute("emp", emp);
			return viewPath +"/empDetail.jsp";
		}
		else if(cmd.equals("getDeptnoList"))
		{
			String strDeptnolist = getDeptnoList();
			try {
				PrintWriter out = response.getWriter();
				out.print(strDeptnolist); // 이게 ajax success로 들어가게 된다
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(cmd.equals("findByDeptno"))
		{
			int deptno = Integer.valueOf(request.getParameter("deptno"));
			List<EmpVO> list = getEmpByDeptno(deptno);
			request.setAttribute("list", list);
			return viewPath +"/empList.jsp";
		}
		else if(cmd.equals("findByEname"))
		{
			String ename = request.getParameter("ename");
			List<EmpVO> list = findByEname(ename);
			request.setAttribute("list", list);
			return viewPath +"/empList.jsp";
		}
		else if(cmd.equals("edit"))
		{
			int empno = Integer.valueOf(request.getParameter("empno"));
			EmpVO emp = detailByEmpno(empno);
			request.setAttribute("emp", emp);
			
			return viewPath +"/empEdit.jsp";
			
		}
		else if(cmd.equals("update"))
		{
			int empno = Integer.valueOf(request.getParameter("empno"));
			int deptno = Integer.valueOf(request.getParameter("deptno"));
			float sal = Float.valueOf(request.getParameter("sal"));
			
			EmpVO emp = new EmpVO();
			emp.setDeptno(deptno);
			emp.setSal(sal);
			emp.setEmpno(empno);
			
			boolean updated = update(emp);
			
			Map<String, Object> map = new HashMap<>();
			map.put("updated", updated);
			sendJSONStr(map);
		}
		else if(cmd.equals("deleteEmp"))
		{
			int empno= Integer.valueOf(request.getParameter("empno"));
			EmpVO emp = new EmpVO();
			emp.setEmpno(empno);
			
			boolean deleted=deleteEmp(emp);
			
			Map<String, Object>map = new HashMap<>();
			map.put("deleted", deleted);
			sendJSONStr(map);
			
		}
		else if(cmd.equals("addEmp"))
		{	
			return viewPath + "/addEmpForm.jsp";
		}
		else if(cmd.equals("addEmpForm"))
		{	
			EmpVO emp = createEmpByParam();
			boolean added =addEmpForm(emp);
			
			Map<String,Object> map = new HashMap<>();
			map.put("added", added);
			sendJSONStr(map);
			
		}
		return null; 
	}
	
	private void sendJSONStr(Map<String, Object> map) 
	{
		JSONObject jsObj = new JSONObject(map);
		try {
			PrintWriter out = response.getWriter();
			out.print(jsObj.toJSONString());
	    	out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private EmpVO createEmpByParam()
	{
		int empno=Integer.valueOf(request.getParameter("empno"));
		String ename=request.getParameter("ename");
		java.sql.Date hiredate =java.sql.Date.valueOf(request.getParameter("hiredate"));
		float sal = Float.valueOf(request.getParameter("sal"));
		int deptno= Integer.valueOf(request.getParameter("deptno"));
		
		System.out.println(empno);
		EmpVO emp = new EmpVO();
		emp.setEmpno(empno);
		emp.setEname(ename);
		emp.setHiredate(hiredate);
		emp.setSal(sal);
		emp.setDeptno(deptno);
		
		return emp;
	}
	public List<EmpVO> getList()
	{
		  EmpDAO dao = new EmpDAO();
	      List<EmpVO> list = dao.getList();
		return list;
	}

	public List<EmpVO> getEmpByDeptno(int deptno) //request객체에 있는 파라미터를 통해 넘어옴
	{
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.getEmpByDeptno(deptno); //위에서 넘어온 deptno를 그대로 전달
		return list;
	}
	
	public EmpVO detailByEmpno(int empno)
	{
		EmpDAO dao= new EmpDAO();
		EmpVO emp = dao.detailByEmpno(empno);
		return emp;
	}
	public String getDeptnoList()
	{
		EmpDAO dao= new EmpDAO();
		List<Integer> list = dao.getDeptnoList();
		JSONArray jsArr = new JSONArray();
		for(int i=0; i<list.size(); i++)
		{
			jsArr.add(list.get(i));
		}
		return jsArr.toJSONString();
	}
	public List<EmpVO> findByEname(String ename)
	{
		EmpDAO dao= new EmpDAO();
		List<EmpVO> list = dao.getEmpByEname(ename);
		return list;
	}
	public boolean update(EmpVO emp)
	{
		EmpDAO dao= new EmpDAO();
		boolean updated = dao.update(emp);
		return updated;
		
	}
	public boolean deleteEmp(EmpVO emp)
	{
		EmpDAO dao= new EmpDAO();
		boolean deleted=dao.deleteEmp(emp);
		return deleted;
	}
	public boolean addEmpForm(EmpVO emp)
	{
		EmpDAO dao = new EmpDAO();
		boolean added =dao.addEmpForm(emp);
		return added;
	}
	
}
