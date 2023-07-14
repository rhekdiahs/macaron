package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	/*=================================
	  			   회원가입
	 ==================================*/
	@Select("select auto_increment from information_schema.tables where TABLE_SCHEMA = 'macaron' and TABLE_NAME ='member'")
	public int selectMem_num();
	
	@Insert("INSERT INTO member (mem_num, mem_id) VALUES (#{mem_num}, #{mem_id})")
	public void insertMember(MemberVO member);
	
	@Insert("INSERT INTO member_detail (mem_num, mem_nick, mem_pw, mem_phone, mem_email) VALUES (#{mem_num}, #{mem_nick}, #{mem_pw}, #{mem_phone}, #{mem_email})")
	public void insertMember_detail(MemberVO member);
	
	@Select("SELECT m.mem_num, m.mem_id, m.mem_auth, d.mem_pw, d.mem_email FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num = d.mem_num WHERE m.mem_id = #{mem_id}")
	public MemberVO selectCheckMember(String mem_id);

	/*=================================
	 		   아이디, 비밀번호 찾기
	==================================*/
	
	//아이디 찾기
	@Select("SELECT mem_id FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num	"
		  + "WHERE d.mem_nick=#{mem_nick} AND d.mem_email=#{mem_email}")
	public String[] find_id(@Param("mem_nick") String mem_nick, @Param("mem_email") String mem_email);
	
	//비밀번호 찾기
	@Select("SELECT mem_pw FROM member JOIN member_detail USING(mem_num) "
		  + "WHERE mem_id=#{mem_id} AND mem_email=#{mem_email}")
	public String find_pw(@Param("mem_id") String mem_id, @Param("mem_email") String mem_email);


	/*=================================
				 회원 관리
	==================================*/
	public List<MemberVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	@Update("UPDATE member SET mem_auth=#{mem_auth} WHERE mem_num=#{mem_num}")
	public void updateByAdmin(MemberVO memberVO);
	
	@Select("SELECT * FROM MEMBER m JOIN MEMBER_DETAIL d ON m.mem_num = d.mem_num WHERE m.mem_num=${mem_num}")
	public MemberVO getMemInfo(Integer mem_num);



}



	
	
	
	