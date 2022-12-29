package com.ezen.servlet.lms2;

public class Lms_testVO {

	private int lms_qid;
	private String lms_num;
	private String lms_tnum;
	private String lms_question;
	private int lms_aid;
	private String lms_anum;
	
	public Lms_testVO() {}
	public Lms_testVO(int lms_qid, String lms_num, String lms_tnum, String lms_question, int lms_aid, String lms_anum,
			String lms_answer) 
	{
		this.lms_qid = lms_qid;
		this.lms_num = lms_num;
		this.lms_tnum = lms_tnum;
		this.lms_question = lms_question;
		this.lms_aid = lms_aid;
		this.lms_anum = lms_anum;
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
