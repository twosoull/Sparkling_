package com.javaex.vo;

public class ConVo {

	private int con_no;
	private int gym_no;
	private String con_name;
	private int con_state;

	public ConVo() {
	}
	
	public ConVo(int gym_no, String con_name) {
		this.gym_no = gym_no;
		this.con_name = con_name;
	}
	
	public ConVo(int gym_no, String con_name, int con_state) {
		this.gym_no = gym_no;
		this.con_name = con_name;
		this.con_state = con_state;
	}

	public ConVo(int con_no, int gym_no, String con_name, int con_state) {
		this.con_no = con_no;
		this.gym_no = gym_no;
		this.con_name = con_name;
		this.con_state = con_state;
	}

	public int getCon_no() {
		return con_no;
	}

	public void setCon_no(int con_no) {
		this.con_no = con_no;
	}

	public int getGym_no() {
		return gym_no;
	}

	public void setGym_no(int gym_no) {
		this.gym_no = gym_no;
	}

	public String getCon_name() {
		return con_name;
	}

	public void setCon_name(String con_name) {
		this.con_name = con_name;
	}

	public int getCon_state() {
		return con_state;
	}

	public void setCon_state(int con_state) {
		this.con_state = con_state;
	}

	@Override
	public String toString() {
		return "ConVo [con_no=" + con_no + ", gym_no=" + gym_no + ", con_name=" + con_name + ", con_state=" + con_state
				+ "]";
	}

}
