package kr.spring.couple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.couple.dao.CoupleMapper;

@Service
@Transactional
public class CoupleServiceImpl implements CoupleService{
	@Autowired
	private CoupleMapper coupleMapper;
	
	@Override
	public int checkCookie(String cp_cookie) {
		return coupleMapper.checkCookie(cp_cookie);
	}
}
