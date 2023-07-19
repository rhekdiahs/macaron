package kr.spring.couple.service;

import kr.spring.couple.vo.CoupleVO;

public interface CoupleService {
	public int checkCookie(String cp_cookie);
	
	public String checkCp_1(String cp_cookie);

	public String checkCp_2(String cp_cookie);
	
	public void registerCp_1(CoupleVO couple);
	
	public void registerCp_2(CoupleVO couple);
}
