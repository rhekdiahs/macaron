package kr.spring.couple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CoupleMapper {
	@Select("select count(*) from couple where cp_cookie = #{cp_cookie}")
	public int checkCookie(String cp_cookie);
}



	
	
	
	