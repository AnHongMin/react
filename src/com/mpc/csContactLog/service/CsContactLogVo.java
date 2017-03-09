package com.mpc.csContactLog.service;

import com.mpc.util.Paging;

public class CsContactLogVo extends Paging{
	
	private String date;
	private String start_dt;
	private String end_dt;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStart_dt() {
		return start_dt;
	}
	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}
	public String getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}
}
