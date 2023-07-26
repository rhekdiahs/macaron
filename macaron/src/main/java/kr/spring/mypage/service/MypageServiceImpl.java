package kr.spring.mypage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.couple.vo.CoupleVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.dao.MypageMapper;

@Service
@Transactional
public class MypageServiceImpl implements MypageService{
	
	@Autowired
	private MypageMapper mypageMapper;

	@Override
	public MemberVO selectMember(Integer mem_num) {
		return mypageMapper.selectMember(mem_num);
	}

	@Override
	public void updateProfile(MemberVO member) {
		mypageMapper.updateProfile(member);
	}

	@Override
	public CoupleVO selectCouple(String cp_cookie) {
		return mypageMapper.selectCouple(cp_cookie);
	}

	@Override
	public Integer getPartner(String mem_email) {
		return mypageMapper.getPartner(mem_email);
	}

}
