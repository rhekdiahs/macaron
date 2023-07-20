package kr.spring.couple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.couple.vo.CoupleVO;

@Mapper
public interface CoupleMapper {
	@Select("select count(*) from couple where cp_cookie = #{cp_cookie}")
	public int checkCookie(String cp_cookie);
	
	/*
	 * @Select("select count(*) from couple where cp_cookie = #{cp_cookie} and cp_1 is not null and cp_2 is null"
	 * ) public int checkCp_2(String cp_cookie);
	 */
	
	@Select("select cp_1 from couple where cp_cookie = #{cp_cookie}")
	public String checkCp_1(String cp_cookie);
	
	@Select("select cp_2 from couple where cp_cookie = #{cp_cookie}")
	public String checkCp_2(String cp_cookie);
	
	@Insert("insert into couple (cp_1, cp_cookie) values (#{cp_1},#{cp_cookie})")
	public void registerCp_1(CoupleVO couple);
	
	@Update("update couple set cp_2 = #{cp_2} where cp_cookie = #{cp_cookie}")
	public void registerCp_2(CoupleVO couple);
}



	
	
	
	