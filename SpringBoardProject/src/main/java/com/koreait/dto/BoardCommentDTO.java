package com.koreait.dto;

import org.apache.ibatis.type.Alias;

@Alias("comment")
public class BoardCommentDTO {
	private int cno;
	private int bno;
	private String comment;
	private String writer;
	private String date;
	private int like;
	private int hate;

	public BoardCommentDTO() {
	}

	public BoardCommentDTO(int cno, int bno, String comment, String writer, String date, int like, int hate) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.comment = comment;
		this.writer = writer;
		this.date = date;
		this.like = like;
		this.hate = hate;
	}

	public BoardCommentDTO(int bno, String comment, String writer) {
		super();
		this.bno = bno;
		this.comment = comment;
		this.writer = writer;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	@Override
	public String toString() {
		return "BoardCommentDTO [cno=" + cno + ", bno=" + bno + ", comment=" + comment + ", writer=" + writer
				+ ", date=" + date + ", like=" + like + ", hate=" + hate + "]";
	}
	
	
}
