package com.ezen.web.hello;

import java.io.Serializable;

public class Board implements Serializable, Comparable<Board>
{
	private int num;
	private String title;
	private String author;
	private java.sql.Date regDate;
	private String contents;
	private int hit;

	public Board() {}
	
	public Board(int num) {
		this.num = num;
	}

	@Override
	public boolean equals(Object obj) {
		Board other = (Board)obj;
		return this.num==other.num;
	}

	@Override
	public String toString() {
		return String.format("%d %s %s %s %s %d", 
				num,title,author,regDate,contents,hit);
	}
	

	@Override
	public int compareTo(Board o) {
		if(this.num>o.num) return 1;
		else if(this.num==o.num) return 0;
		else return -1;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public java.sql.Date getRegDate() {
		return regDate;
	}
	public void setRegDate(java.sql.Date regDate) {
		this.regDate = regDate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
}
