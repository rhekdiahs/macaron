package kr.spring.couple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.couple.dao.CoupleMapper;
import kr.spring.couple.vo.CoupleVO;

@Service
@Transactional
public abstract class CoupleServiceImpl implements CoupleService{
	@Autowired
	private CoupleMapper coupleMapper;
	
	@Override
	public int checkCookie(String cp_cookie) {
		return coupleMapper.checkCookie(cp_cookie);
	}
	
	@Override
	public String checkCp_1(String cp_cookie) {
		return coupleMapper.checkCp_1(cp_cookie);
	}
	
	@Override
	public String checkCp_2(String cp_cookie) {
		return coupleMapper.checkCp_2(cp_cookie);
	}
	
	@Override
	public void registerCp_1(CoupleVO couple) {
		coupleMapper.registerCp_1(couple);
		
	}

	@Override
	public void registerCp_2(CoupleVO couple) {
		coupleMapper.registerCp_2(couple);
	}
}
