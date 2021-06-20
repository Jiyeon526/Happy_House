package com.ssafy.house.model.dto;

import org.springframework.stereotype.Repository;

@Repository
public class QnADto {
	private int bnum;
	private String btitle;
	private String bwriter;
	private String bcontent;

	@Override
	public String toString() {
		return "QnADto [bnum=" + bnum + ", btitle=" + btitle + ", bwriter=" + bwriter + ", bcontent=" + bcontent + "]";
	}

	public QnADto(int bnum, String btitle, String bwriter, String bcontent) {
		super();
		this.bnum = bnum;
		this.btitle = btitle;
		this.bwriter = bwriter;
		this.bcontent = bcontent;
	}

	public QnADto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

}
