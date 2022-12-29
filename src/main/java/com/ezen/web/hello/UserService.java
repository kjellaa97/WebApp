package com.ezen.web.hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UserService 
{
	private HttpServletRequest request;
	
	public UserService() {}
	public UserService(HttpServletRequest request) {
		this.request=request;			// 생성자를 사용한 이유는 한번만 하면 되는 일이니까
										//한번만 하는일은 생성자 사용
	}
	
	public boolean userreg()
	{
		//user의 정보를 받아서 저장하는 기능
		User u = new User();
		u.setNum(Integer.parseInt(request.getParameter("num")));
		u.setName( request.getParameter("name"));
		u.setPhone(request.getParameter("phone"));
		u.setEmail(request.getParameter("email"));
		u.setPic(request.getParameter("pic"));
		
	
		File f = new File("D:/test/userreg.ser");
		List<User> list = null;
		
		System.out.println("파일 존재 여부: " + f.exists());
		
		if(!f.exists()) list = new ArrayList<>();
		else			list = deserialize();
		
		list.add(u);
		return serialize(list);
		

	}

	private boolean serialize(List<User> list)
	{
		try {
			ObjectOutputStream oout = new ObjectOutputStream (new FileOutputStream("D:/test/userreg.ser"));
			oout.writeObject(list); //직렬화해서 저장하는 과정 
			oout.flush();
			oout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}return false;
	}
	
	private List<User> deserialize()
	{
		try {
			ObjectInputStream objInput = new ObjectInputStream(new FileInputStream ("D:/test/userreg.ser"));
			List <User> list = (List<User>)objInput.readObject();
			System.out.println("사용자 리스트" +list);
			objInput.close();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<User> getlist() 
	{
		return deserialize();
	}
	
	public User getUser()
	{
		int num = Integer.parseInt(request.getParameter("num")); 
		List<User> list = deserialize();
		User key = new User(num);
		
		if(list.contains(key))
		{
			return list.get(list.indexOf(key));
		}
		
		return null;
		
	}
	public String getNameList()
	{
		List<User> list = deserialize();
		JSONArray jsArr = new JSONArray();
		for(int i=0; i<list.size(); i++)
		{
			jsArr.add(list.get(i).getName());
		}
		return jsArr.toJSONString();
		
	}
	public String getNumList()
	{
		List<User> list = deserialize();
		JSONArray jsArr = new JSONArray();
		for(int i=0; i<list.size(); i++) 
		{
			jsArr.add(list.get(i).getNum());
		}
		return jsArr.toJSONString();
		
	}
	public List<User> searchUser()
	{
		String name = (String)request.getParameter("name");
		List<User> list = deserialize();
		List<User> found = new ArrayList<>();
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i).getName().equals(name))
			{
				found.add(list.get(i));
			}
				
		}
		if(found.size()==0) found= null;
		return found;
	
	}
	public boolean update()
	{
		int num=Integer.parseInt(request.getParameter("num"));
		String newEmail= request.getParameter("email");
		String newPic = request.getParameter("pic");
		User key = new User(num);
		List<User> list = deserialize();
		if (list.contains(key))
		{
			list.get(list.indexOf(key)).setEmail(newEmail);
			list.get(list.indexOf(key)).setPic(newPic);
			serialize(list);
			return true;
		}
		return false;
	}
	public boolean delete() 
	{	
		int num = Integer.parseInt(request.getParameter("num"));
		User key = new User(num);
		List<User> list = deserialize();
		if(list.contains(key))
		{
			list.remove(key);
			serialize(list);
			return true;
		}
		return false;
	}
	public String getUserJSONservice() {
		User u = getUser();		//번호를 이용해서 한 사람의 모든 정보를 가져와
		JSONObject jsObj = new JSONObject();	// json오브젝트로 바꿔야 자바스크립트에서 쓰기 편함 . 그래서 json simple(다운받아야함)에 있는 jsonobject 사용
												// map을 상속받아서 만들어진 jsonsimple
		jsObj.put("num",  u.getNum());		//map의 특징인 key와 value 
		jsObj.put("name", u.getName());
		jsObj.put("phone", u.getPhone());
		jsObj.put("email", u.getEmail());
		jsObj.put("pic", u.getPic());
		
		return jsObj.toJSONString();
	}
}
