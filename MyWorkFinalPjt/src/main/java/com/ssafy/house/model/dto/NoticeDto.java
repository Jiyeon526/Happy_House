package com.ssafy.house.model.dto;

import org.springframework.stereotype.Repository;

@Repository
public class NoticeDto {

	private int nno;
	private String ntitle;
	private String ncontent;
	private int nview;

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public int getNview() {
		return nview;
	}

	public void setNview(int nview) {
		this.nview = nview;
	}

	public NoticeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeDto(int nno, String ntitle, String ncontent, int nview) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nview = nview;
	}

	@Override
	public String toString() {
		return "NoticeDto [nno=" + nno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", nview=" + nview + "]";
	}

}
