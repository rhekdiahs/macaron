package kr.spring.mypage.service;

import kr.spring.couple.vo.CoupleVO;
import kr.spring.member.vo.MemberVO;

public interface MypageService {
	public MemberVO selectMember(Integer mem_num);
	
	public void updateProfile(MemberVO member);
	
	public CoupleVO selectCouple(String cp_cookie);
	
	public Integer getPartner(String mem_email);
	
	public void updateMember(MemberVO member);
	
	public void deleteCouple(String cp_cookie);
		
	public void updateMemAuth(int mem_num);
}
