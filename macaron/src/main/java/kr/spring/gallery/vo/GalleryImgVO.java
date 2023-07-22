package kr.spring.gallery.vo;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class GalleryImgVO {
	private int img_num;
	private String img_filename;
	private byte[] img_file;
	private int g_num;
	
	public void setUpload(MultipartFile upload) throws IOException{
		setImg_file(upload.getBytes());
		
		setImg_filename(upload.getOriginalFilename());
	}
	
	public int getImg_num() {
		return img_num;
	}
	public void setImg_num(int img_num) {
		this.img_num = img_num;
	}
	public String getImg_filename() {
		return img_filename;
	}
	public void setImg_filename(String img_filename) {
		this.img_filename = img_filename;
	}
	public byte[] getImg_file() {
		return img_file;
	}
	public void setImg_file(byte[] img_file) {
		this.img_file = img_file;
	}
	public int getG_num() {
		return g_num;
	}
	public void setG_num(int g_num) {
		this.g_num = g_num;
	}
	
}
