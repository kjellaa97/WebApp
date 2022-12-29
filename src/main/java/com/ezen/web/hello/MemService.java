package com.ezen.web.hello;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class MemService 
{

	private HttpServletRequest request;
	private Member member;
	private String fpath = "D:/test/member.ser";
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public MemService() {}
	public MemService (HttpServletRequest request) {
		this.request = request;
	}
		
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public boolean saveMem()
	{
		List<Member> list = deserialize();
		Collections.sort(list);
		int num=1;
		if(list.size()>0)
			num = list.get(list.size()-1).getNum()+1;
		member.setNum(num);
		list.add(member);
		return serialize(list);
	}
	private boolean serialize(List<Member> list)
	{
		File f = new File(fpath);
		try {
			ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(f));
			objout.writeObject(list);
			objout.flush();
			objout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	private List<Member> deserialize()
	{
		File f = new File(fpath);
		List<Member> list = null;
		if(!f.exists()) {
			list = new ArrayList<Member> ();
		}else {
			try {
					ObjectInputStream oin = new ObjectInputStream(new FileInputStream (f));
					list = (List<Member>)oin.readObject();
					oin.close();
					return list;
			}catch (Exception e) {
				e.printStackTrace();
			}		
		}
		return list;
	}
	public List<Member> showList()
	{
		return deserialize();
		
	}
	public Member showMem()
	{
		List<Member> list = deserialize();
		
		if(list.contains(member))
		{
			return list.get(list.indexOf(member));
		}
			return null;
	}
}
