package kr.spring.calendar.vo;

import org.springframework.lang.Nullable;

public class CalendarVO {
	private int cal_num;
	private String date_start;
	private String date_end;
	private String cal_category;
	private String cal_title;
	@Nullable
	private String cal_memo;
	private String mem_cookie;
	
	public int getCal_num() {
		return cal_num;
	}
	public void setCal_num(int cal_num) {
		this.cal_num = cal_num;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	public String getCal_category() {
		return cal_category;
	}
	public void setCal_category(String cal_category) {
		this.cal_category = cal_category;
	}
	public String getCal_title() {
		return cal_title;
	}
	public void setCal_title(String cal_title) {
		this.cal_title = cal_title;
	}
	public String getCal_memo() {
		return cal_memo;
	}
	public void setCal_memo(String cal_memo) {
		this.cal_memo = cal_memo;
	}
	public String getMem_cookie() {
		return mem_cookie;
	}
	public void setMem_cookie(String mem_cookie) {
		this.mem_cookie = mem_cookie;
	}
	@Override
	public String toString() {
		return "CalendarVO [cal_num=" + cal_num + ", date_start=" + date_start + ", date_end=" + date_end
				+ ", cal_category=" + cal_category + ", cal_title=" + cal_title + ", cal_memo=" + cal_memo
				+ ", mem_cookie=" + mem_cookie + "]";
	}

}
