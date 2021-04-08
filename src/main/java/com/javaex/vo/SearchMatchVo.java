package com.javaex.vo;

public class SearchMatchVo {
	
	private String date;
	private String time;
	private String search;
	
	
	
	public SearchMatchVo() {
		super();
	}
	public SearchMatchVo(String date, String time, String search) {
		super();
		this.date = date;
		this.time = time;
		this.search = search;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	@Override
	public String toString() {
		return "SearchMatch [date=" + date + ", time=" + time + ", search=" + search + "]";
	}
	
	
	
}
