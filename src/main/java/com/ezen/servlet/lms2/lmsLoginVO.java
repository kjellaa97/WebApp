package com.ezen.servlet.lms2;

public class lmsLoginVO {

	private String userid;
	private String pwd;
	private String lms_admin;
	
	public lmsLoginVO() {}

	public lmsLoginVO(String userid, String pwd, String lms_admin) {
		this.userid = userid;
		this.pwd = pwd;
		this.lms_admin = lms_admin;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLms_admin() {
		return lms_admin;
	}

	public void setLms_admin(String lms_admin) {
		this.lms_admin = lms_admin;
	}
	
	
}
