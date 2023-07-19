package kr.spring.couple.vo;

import java.sql.Date;

public class CoupleVO {
	private int cp_num;
	private String cp_1;
	private String cp_2;
	private Date cp_date;
	private String cp_cookie;
	
	public int getCp_num() {
		return cp_num;
	}
	public void setCp_num(int cp_num) {
		this.cp_num = cp_num;
	}
	public String getCp_1() {
		return cp_1;
	}
	public void setCp_1(String cp_1) {
		this.cp_1 = cp_1;
	}
	public String getCp_2() {
		return cp_2;
	}
	public void setCp_2(String cp_2) {
		this.cp_2 = cp_2;
	}
	public Date getCp_date() {
		return cp_date;
	}
	public void setCp_date(Date cp_date) {
		this.cp_date = cp_date;
	}
	public String getCp_cookie() {
		return cp_cookie;
	}
	public void setCp_cookie(String cp_cookie) {
		this.cp_cookie = cp_cookie;
	}
	@Override
	public String toString() {
		return "CoupleVO [cp_num=" + cp_num + ", cp_1=" + cp_1 + ", cp_2=" + cp_2 + ", cp_date=" + cp_date
				+ ", cp_cookie=" + cp_cookie + "]";
	}
	
}
