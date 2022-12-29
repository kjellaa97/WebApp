package com.ezen.servlet.lms2;

import java.sql.Date;

public class Lms_statusVO {
	
	 private String userid;
	 private int lvl_code;
	 private java.sql.Date test_date;
	 private String t_feedbkack;
	 private int scoring;
	 private String pass;
	 private int lms_qid;
	 private String lms_num;
	 private String lms_tnum;
	 private String lms_question;
	 private int lms_aid;
	 private String lms_anum;

	 Lms_statusVO(){}
	 
	 

	public Lms_statusVO(String userid, int lvl_code, Date test_date, String t_feedbkack, int scoring, String pass,
			int lms_qid, String lms_num, String lms_tnum, String lms_question, int lms_aid, String lms_anum) {
		super();
		this.userid = userid;
		this.lvl_code = lvl_code;
		this.test_date = test_date;
		this.t_feedbkack = t_feedbkack;
		this.scoring = scoring;
		this.pass = pass;
		this.lms_qid = lms_qid;
		this.lms_num = lms_num;
		this.lms_tnum = lms_tnum;
		this.lms_question = lms_question;
		this.lms_aid = lms_aid;
		this.lms_anum = lms_anum;
	}



	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getLvl_code() {
		return lvl_code;
	}

	public void setLvl_code(int lvl_code) {
		this.lvl_code = lvl_code;
	}

	public java.sql.Date getTest_date() {
		return test_date;
	}

	public void setTest_date(java.sql.Date test_date) {
		this.test_date = test_date;
	}

	public String getT_feedbkack() {
		return t_feedbkack;
	}

	public void setT_feedbkack(String t_feedbkack) {
		this.t_feedbkack = t_feedbkack;
	}

	public int getScoring() {
		return scoring;
	}

	public void setScoring(int scoring) {
		this.scoring = scoring;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getLms_qid() {
		return lms_qid;
	}

	public void setLms_qid(int lms_qid) {
		this.lms_qid = lms_qid;
	}

	public String getLms_num() {
		return lms_num;
	}

	public void setLms_num(String lms_num) {
		this.lms_num = lms_num;
	}

	public String getLms_tnum() {
		return lms_tnum;
	}

	public void setLms_tnum(String lms_tnum) {
		this.lms_tnum = lms_tnum;
	}

	public String getLms_question() {
		return lms_question;
	}

	public void setLms_question(String lms_question) {
		this.lms_question = lms_question;
	}

	public int getLms_aid() {
		return lms_aid;
	}

	public void setLms_aid(int lms_aid) {
		this.lms_aid = lms_aid;
	}

	public String getLms_anum() {
		return lms_anum;
	}

	public void setLms_anum(String lms_anum) {
		this.lms_anum = lms_anum;
	}
	 
	 
}
