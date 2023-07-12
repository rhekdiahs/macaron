package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	private int mem_num;
	@Pattern(regexp = "^[a-z0-9]{4,12}$")
	private String mem_id;
	private int mem_auth;
	@NotEmpty
	private String mem_nick;
	@Pattern(regexp="^[A-Za-z0-9]{8,16}$")
	private String mem_pw;
	private String mem_phone;
	@NotEmpty
	private String mem_email;
	private byte[] mem_photo;
	private Date mem_regdate;
	private Date mem_modi_date;
	
	private String mem_kakao;
	private String mem_intro;
	
	//아이디 찾기 시 전체 아이디
	private String[] mem_all_id;

	public String[] getMem_all_id() {
		return mem_all_id;
	}
	//이메일 인증코드
	private int email_code;
	
	//회원가입시 비밀번호 확인
	@Pattern(regexp="^[A-Za-z0-9]{8,16}$")
	private String pw_confirm;

	@Pattern(regexp="^[A-Za-z0-9]{8,16}$")
	private String now_passwd;
	
	//====이미지 BLOB 처리====//
	//(주의)폼에서 파일업로드 파라미터네임은 반드시 upload로 지정해야 함(mem_photo로 하면 안 됨! upload로 받은 걸 photo로 바꿀 거라서)
	public void setUpload(MultipartFile upload) throws IOException{

		//MultipartFile -> byte[]
		setMem_photo(upload.getBytes());
		//파일 이름
		//setMem_photo_name(upload.getOriginalFilename());
	}

	
	//====비밀번호 일치 여부 체크====//
	public boolean isCheckedPassword(String userPasswd) {
		if(mem_auth > 0 && mem_pw.equals(userPasswd)) {
			return true;
		}
		return false;
	}


	public int getMem_num() {
		return mem_num;
	}


	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}


	public String getMem_id() {
		return mem_id;
	}


	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}


	public int getMem_auth() {
		return mem_auth;
	}


	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}


	public String getMem_nick() {
		return mem_nick;
	}


	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}


	public String getMem_pw() {
		return mem_pw;
	}


	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}


	public String getMem_phone() {
		return mem_phone;
	}


	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}


	public String getMem_email() {
		return mem_email;
	}


	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}


	public byte[] getMem_photo() {
		return mem_photo;
	}


	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}


	public Date getMem_regdate() {
		return mem_regdate;
	}


	public void setMem_regdate(Date mem_regdate) {
		this.mem_regdate = mem_regdate;
	}


	public Date getMem_modi_date() {
		return mem_modi_date;
	}


	public void setMem_modi_date(Date mem_modi_date) {
		this.mem_modi_date = mem_modi_date;
	}


	public String getMem_kakao() {
		return mem_kakao;
	}


	public void setMem_kakao(String mem_kakao) {
		this.mem_kakao = mem_kakao;
	}


	public String getMem_intro() {
		return mem_intro;
	}


	public void setMem_intro(String mem_intro) {
		this.mem_intro = mem_intro;
	}


	public int getEmail_code() {
		return email_code;
	}


	public void setEmail_code(int email_code) {
		this.email_code = email_code;
	}


	public String getPw_confirm() {
		return pw_confirm;
	}


	public void setPw_confirm(String pw_confirm) {
		this.pw_confirm = pw_confirm;
	}


	public String getNow_passwd() {
		return now_passwd;
	}


	public void setNow_passwd(String now_passwd) {
		this.now_passwd = now_passwd;
	}


	public void setMem_all_id(String[] mem_all_id) {
		this.mem_all_id = mem_all_id;
	}
	

	
}
