package com.ezen.web.hello;

import java.io.Serializable;

public class Member implements Serializable, Comparable<Member>
{
	private int num;
	private String name;
	private String sex;
	private String dob;
	private String email;
	private int year;
	private String[] subject;
	private String intro;
	
	public Member() {}
	public Member(int num) {
	}
	
	public Member(int num, String name, String sex, String dob, String email, int year, String[] subject,
			String introduction) {

		this.num = num;
		this.name = name;
		this.sex = sex;
		this.dob = dob;
		this.email = email;
		this.year = year;
		this.subject = subject;
		this.intro = introduction;
	}

	@Override
	public int compareTo(Member other) {
		if(this.num> other.num)
		{
			return 1;
		}else if(this.num<other.num)
		{
			return -1;
		}
		else return 0;
	}
	
	@Override
	public String toString() {
		String sub = "[";
		for(int i=0;i<subject.length;i++) {
			sub += " "+subject[i];
		}
		sub +="]";
		return num +","+name+","+dob+","+email+","+sex+","+year+","+sub+","+intro;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String[] getSubject() {
		return subject;
	}

	public void setSubject(String[] subject) {
		this.subject = subject;
	}

	public String getIntroduction() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	
	
	

}
