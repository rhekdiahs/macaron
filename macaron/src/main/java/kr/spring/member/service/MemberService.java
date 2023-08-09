package kr.spring.member.service;

import java.util.List;
import java.util.Map;


import kr.spring.member.vo.MemberVO;

public interface MemberService {
	
	//회원가입
	public void insertMember(MemberVO member);
	
	//아이디 중복체크
	public MemberVO selectCheckMember(String mem_id);

	//아이디 찾기
	public String[] find_id(String mem_nick, String mem_email);
	//비밀번호 찾기
	public String find_pw(String mem_id, String mem_email);
	
	
	//회원관리
	public List<MemberVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	
	//마이페이지
	public MemberVO getMemInfo(Integer mem_num);

	//가입여부 체크
	public int checkEmailCount(String mem_email);
	
	//커플인증여부 체크
	public String checkCookie(String mem_email);
	
	//커플인증코드 저장
	public void setCookie(String mem_email, String partner_email, String mem_cookie);
}
