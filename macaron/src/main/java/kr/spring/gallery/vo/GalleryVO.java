package kr.spring.gallery.vo;

import java.sql.Date;

public class GalleryVO {
	private int g_num;
	private String g_title;
	private String g_content;
	private String g_place;
	private String g_cookie;
	private String g_hash;
	private Date g_date;
	private int mem_num;
	
	
	public int getG_num() {
		return g_num;
	}
	public void setG_num(int g_num) {
		this.g_num = g_num;
	}
	public String getG_title() {
		return g_title;
	}
	public void setG_title(String g_title) {
		this.g_title = g_title;
	}
	public String getG_content() {
		return g_content;
	}
	public void setG_content(String g_content) {
		this.g_content = g_content;
	}
	public String getG_place() {
		return g_place;
	}
	public void setG_place(String g_place) {
		this.g_place = g_place;
	}
	public String getG_cookie() {
		return g_cookie;
	}
	public void setG_cookie(String g_cookie) {
		this.g_cookie = g_cookie;
	}
	public String getG_hash() {
		return g_hash;
	}
	public void setG_hash(String g_hash) {
		this.g_hash = g_hash;
	}
	public Date getG_date() {
		return g_date;
	}
	public void setG_date(Date g_date) {
		this.g_date = g_date;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
}
