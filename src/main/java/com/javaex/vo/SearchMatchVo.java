package com.javaex.vo;

public class SearchMatchVo {
	
	private String date;
	private String time;
	private String search;
	
	//booking_start와 booking_end

	private String booking_start;
	private String booking_end;
	
	
	public SearchMatchVo() {
		
	}
	
	public SearchMatchVo(String date, String time, String search, String booking_start, String booking_end) {
		super();
		this.date = date;
		this.time = time;
		this.search = search;
		this.booking_start = booking_start;
		this.booking_end = booking_end;
	}

	
	
	public String getBooking_start() {
		return booking_start;
	}

	public void setBooking_start(String booking_start) {
		this.booking_start = booking_start;
	}

	public String getBooking_end() {
		return booking_end;
	}

	public void setBooking_end(String booking_end) {
		this.booking_end = booking_end;
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
