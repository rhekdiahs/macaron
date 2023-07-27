package kr.spring.mypage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.couple.vo.CoupleVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface MypageMapper {
	
	@Select("SELECT * FROM member m JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num = #{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	@Update("UPDATE member_detail SET mem_photo=#{mem_photo} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	
	
	@Select("SELECT * FROM couple WHERE cp_cookie = #{cp_cookie}")
	public CoupleVO selectCouple(String cp_cookie);
	
	@Select("SELECT mem_num FROM member_detail WHERE mem_email = #{mem_email}")
	public Integer getPartner(String mem_email);
	
	@Update("UPDATE member_detail SET mem_nick = #{mem_nick}, mem_phone = #{mem_phone} WHERE mem_num = #{mem_num}")
	public void updateMember(MemberVO member);
}
