<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.*"%>
<%@ page contentType="application/json; charset=utf-8" pageEncoding="utf-8"%>
<%
	/*
	String hobby = request.getParameter("hobby");	//이렇게 하면 hobby값이 하나만 전송됨
	String[] hobbies = request.getParameterValues("hobby");	//hobby 값이 선택된 다수의 값이 나올수 있음
	*/
	
	List<String> cart = new ArrayList<>();
	cart.add("Apple");
	cart.add("Orange");
	cart.add("Pineapple");
	cart.add("Kiwi");
	
	String[] fr = request.getParameterValues("fruits");
	int cnt =0;
	for(int i=0; i<fr.length; i++)	
	{
		for(int j=(cart.size()-1); j>=0; j--) 
		//for(int j = 0;j<cart.size(); j++) // 이렇게 앞에서부터 지워지는 방식으로 하면 지워진거 뒤에거가 방번호가 땡겨지므로 뒤에서부터 지워야함
		{
			if( cart.get(j).equals(fr[i])){
				cart.remove(j);
				cnt++;
			}
		}
	}
	
	boolean deleted = cnt==fr.length? true: false;
	JSONObject jsObj = new JSONObject();
	jsObj.put("deleted", deleted);
	out.print(jsObj.toJSONString());
	out.flush();
%>

