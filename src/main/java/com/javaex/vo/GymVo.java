package com.javaex.vo;

import java.util.List;

public class GymVo {

	private int gym_no;
	private int sell_no;
	private String gym_name;
	private String gym_address;
	private String gym_ph;
	private String gym_time;
	private String gym_notice;
	private String gym_event;
	private String gym_date;
	private String addressdetail;
	private String roadaddress;
	
	// 영훈
	private int avgPrice;
	private int avgOnePrice;
	private String addressHalf;
	private String gymImgSavename;
	private String gymImgType;
	
	
	private String money;
	private String moneyHalf;
	
	
	
	
	//리스트
	//2.gymImg 정보
		private List<GymImgVo> gymimgList;
		
		//3.오늘부터 10일 날짜와 요일
		private List<DayVo> dayList;
		
		//4.오늘날짜의 부킹정보
		private List<BookingVo> bookingList;
		
		//5.해당gym의 편의시설 리스트
		private List<ConvenienceVo> conList;

		
	public GymVo() {
	}

	public GymVo(int gym_no, int sell_no, String gym_name, String gym_address, String gym_ph, String gym_time,
			String gym_notice, String gym_event, String gym_date, String addressdetail, String roadaddress) {
		this.gym_no = gym_no;
		this.sell_no = sell_no;
		this.gym_name = gym_name;
		this.gym_address = gym_address;
		this.gym_ph = gym_ph;
		this.gym_time = gym_time;
		this.gym_notice = gym_notice;
		this.gym_event = gym_event;
		this.gym_date = gym_date;
		this.addressdetail = addressdetail;
		this.roadaddress = roadaddress;
	}
	
	public GymVo(int gym_no, int sell_no, String gym_name, String gym_address, String gym_ph, String gym_time,
			String gym_notice, String gym_event, String gym_date) {
		this.gym_no = gym_no;
		this.sell_no = sell_no;
		this.gym_name = gym_name;
		this.gym_address = gym_address;
		this.gym_ph = gym_ph;
		this.gym_time = gym_time;
		this.gym_notice = gym_notice;
		this.gym_event = gym_event;
		this.gym_date = gym_date;
	}

	
	
	public GymVo(int gym_no, int sell_no, String gym_name, String gym_address, String gym_ph, String gym_time,
			String gym_notice, String gym_event, String gym_date, int avgPrice, int avgOnePrice, String addressHalf,
			String gymImgSavename, String gymImgType) {
		super();
		this.gym_no = gym_no;
		this.sell_no = sell_no;
		this.gym_name = gym_name;
		this.gym_address = gym_address;
		this.gym_ph = gym_ph;
		this.gym_time = gym_time;
		this.gym_notice = gym_notice;
		this.gym_event = gym_event;
		this.gym_date = gym_date;
		this.avgPrice = avgPrice;
		this.avgOnePrice = avgOnePrice;
		this.addressHalf = addressHalf;
		this.gymImgSavename = gymImgSavename;
		this.gymImgType = gymImgType;
	}
	
	
	
	public List<GymImgVo> getGymimgList() {
		return gymimgList;
	}

	public void setGymimgList(List<GymImgVo> gymimgList) {
		this.gymimgList = gymimgList;
	}

	public List<DayVo> getDayList() {
		return dayList;
	}

	public void setDayList(List<DayVo> dayList) {
		this.dayList = dayList;
	}

	public List<BookingVo> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<BookingVo> bookingList) {
		this.bookingList = bookingList;
	}

	public List<ConvenienceVo> getConList() {
		return conList;
	}

	public void setConList(List<ConvenienceVo> conList) {
		this.conList = conList;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMoneyHalf() {
		return moneyHalf;
	}

	public void setMoneyHalf(String moneyHalf) {
		this.moneyHalf = moneyHalf;
	}

	public String getGymImgSavename() {
		return gymImgSavename;
	}

	public void setGymImgSavename(String gymImgSavename) {
		this.gymImgSavename = gymImgSavename;
	}

	public String getGymImgType() {
		return gymImgType;
	}

	public void setGymImgType(String gymImgType) {
		this.gymImgType = gymImgType;
	}

	public int getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(int avgPrice) {
		this.avgPrice = avgPrice;
	}

	public int getAvgOnePrice() {
		return avgOnePrice;
	}

	public void setAvgOnePrice(int avgOnePrice) {
		this.avgOnePrice = avgOnePrice;
	}
	
	public String getAddressHalf() {
		return addressHalf;
	}

	public void setAddressHalf(String addressHalf) {
		this.addressHalf = addressHalf;
	}

	public int getGym_no() {
		return gym_no;
	}

	public void setGym_no(int gym_no) {
		this.gym_no = gym_no;
	}

	public int getSell_no() {
		return sell_no;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public String getGym_name() {
		return gym_name;
	}

	public void setGym_name(String gym_name) {
		this.gym_name = gym_name;
	}

	public String getGym_address() {
		return gym_address;
	}

	public void setGym_address(String gym_address) {
		this.gym_address = gym_address;
	}

	public String getGym_ph() {
		return gym_ph;
	}

	public void setGym_ph(String gym_ph) {
		this.gym_ph = gym_ph;
	}

	public String getGym_time() {
		return gym_time;
	}

	public void setGym_time(String gym_time) {
		this.gym_time = gym_time;
	}

	public String getGym_notice() {
		return gym_notice;
	}

	public void setGym_notice(String gym_notice) {
		this.gym_notice = gym_notice;
	}

	public String getGym_event() {
		return gym_event;
	}

	public void setGym_event(String gym_event) {
		this.gym_event = gym_event;
	}

	public String getGym_date() {
		return gym_date;
	}

	public void setGym_date(String gym_date) {
		this.gym_date = gym_date;
	}
	
	public String getAddressdetail() {
		return addressdetail;
	}

	public void setAddressdetail(String addressdetail) {
		this.addressdetail = addressdetail;
	}

	public String getRoadaddress() {
		return roadaddress;
	}

	public void setRoadaddress(String roadaddress) {
		this.roadaddress = roadaddress;
	}

	@Override
	public String toString() {
		return "GymVo [gym_no=" + gym_no + ", sell_no=" + sell_no + ", gym_name=" + gym_name + ", gym_address="
				+ gym_address + ", gym_ph=" + gym_ph + ", gym_time=" + gym_time + ", gym_notice=" + gym_notice
				+ ", gym_event=" + gym_event + ", gym_date=" + gym_date + ", addressdetail=" + addressdetail
				+ ", roadaddress=" + roadaddress + ", avgPrice=" + avgPrice + ", avgOnePrice=" + avgOnePrice
				+ ", addressHalf=" + addressHalf + ", gymImgSavename=" + gymImgSavename + ", gymImgType=" + gymImgType
				+ ", money=" + money + ", moneyHalf=" + moneyHalf + "]";
	}
	
}
