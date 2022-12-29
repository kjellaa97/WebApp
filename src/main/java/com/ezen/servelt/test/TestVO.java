package com.ezen.servelt.test;

public class TestVO 
{
	private int idx1;
	private String name1;
	private String pwd1;
	
	
	public TestVO(int idx1, String name1, String pwd1) {

		this.idx1 = idx1;
		this.name1 = name1;
		this.pwd1 = pwd1;
	}
	
	public TestVO() {	}

	public int getIdx1() {
		return idx1;
	}
	public void setIdx1(int idx1) {
		this.idx1 = idx1;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getPwd1() {
		return pwd1;
	}
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}
	
	
}
