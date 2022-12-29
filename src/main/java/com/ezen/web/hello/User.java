package com.ezen.web.hello;

import java.io.Serializable;

public class User implements Serializable
{
	private int num;
	private String name;
	private String phone;
	private String email;
	private String pic;
	
	public User() {}
	
	public User(int num) {
		this.num= num;
	}
	public User(String name ) {
		this.name= name;
	}
	public User(int num, String name, String phone, String email, String pic) {
	
		this.num = num;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.pic= pic;
		
		
	}
	
	@Override
	public boolean equals(Object obj) {
		User other = (User) obj;
		return this.num==other.num ;
		
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}


	
	
}
