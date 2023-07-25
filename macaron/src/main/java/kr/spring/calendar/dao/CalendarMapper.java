package kr.spring.calendar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.calendar.vo.CalendarVO;

@Mapper
public interface CalendarMapper {
	@Insert("insert into calendar (cal_date,cal_category,cal_title,cal_memo,mem_num) values (#{cal_date},#{cal_category},#{cal_title},#{cal_memo},#{mem_num})")
	public void insertCal(CalendarVO cal);
	
	@Select("select * from calendar where mem_cookie=#{mem_cookie}")
	public List<CalendarVO> allData(String mem_cookie);
}
