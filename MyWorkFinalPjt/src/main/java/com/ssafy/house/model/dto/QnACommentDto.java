package com.ssafy.house.model.dto;

public class QnACommentDto {
	private int cnum;
	private int bnum;
	private String cwriter;
	private String ccontent;

	public QnACommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QnACommentDto(int cnum, int bnum, String cwriter, String ccontent) {
		super();
		this.cnum = cnum;
		this.bnum = bnum;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
	}

	@Override
	public String toString() {
		return "QnACommentDto [cnum=" + cnum + ", bnum=" + bnum + ", cwriter=" + cwriter + ", ccontent=" + ccontent
				+ "]";
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getCwriter() {
		return cwriter;
	}

	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

}
