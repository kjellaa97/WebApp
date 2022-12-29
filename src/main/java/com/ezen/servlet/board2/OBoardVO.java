package com.ezen.servlet.board2;

import java.sql.Date;

public class OBoardVO 
{
	private int boardid ;
	private String title;
	private String contents;
	private String author;
	private int hitcnt;
	private java.sql.Date regdate;
	private int parentid;
	
	public OBoardVO() {}
	
	public OBoardVO(int boardid , String title, String contents, String author, int hitcnt, Date regdate, int parentid) {

		this.boardid  = boardid ;
		this.title= title;
		this.contents = contents;
		this.author = author;
		this.hitcnt = hitcnt;
		this.regdate = regdate;
		this.parentid = parentid;
	}

	public int getBoardid() {
		return boardid ;
	}

	public void setBoardid(int boardid ) {
		this.boardid  = boardid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getHitcnt() {
		return hitcnt;
	}

	public void setHitcnt(int hitcnt) {
		this.hitcnt = hitcnt;
	}

	public java.sql.Date getRegdate() {
		return regdate;
	}

	public void setRegdate(java.sql.Date regdate) {
		this.regdate = regdate;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	
	
}
