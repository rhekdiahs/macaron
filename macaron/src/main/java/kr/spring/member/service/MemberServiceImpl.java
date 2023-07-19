package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void insertMember(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_detail(member);
	}

	@Override
	public MemberVO selectCheckMember(String mem_id) {
		return memberMapper.selectCheckMember(mem_id);
	}

	@Override
	public String[] find_id(String mem_name, String mem_email) {
		return memberMapper.find_id(mem_name, mem_email);
	}

	@Override
	public String find_pw(String mem_id, String mem_email) {
		return memberMapper.find_pw(mem_id, mem_email);
	}

	@Override
	public List<MemberVO> selectList(Map<String, Object> map) {
		return memberMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return memberMapper.selectRowCount(map);
	}

	@Override
	public MemberVO getMemInfo(Integer mem_num) {
		return memberMapper.getMemInfo(mem_num);
	}

	@Override
	public int checkEmailCount(String mem_email) {
		return memberMapper.checkEmailCount(mem_email);
	}

	@Override
	public String checkCookie(String mem_email) {
		return memberMapper.checkCookie(mem_email);
	}

	@Override
	public void setCookie(String mem_email, String partner_email, String mem_cookie) {
		memberMapper.setCookie(mem_email, mem_cookie);
		memberMapper.setCookie(partner_email, mem_cookie);
	}
	
	
}
