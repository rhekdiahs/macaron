package kr.spring.calendar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.calendar.vo.CalendarVO;

@Mapper
public interface CalendarMapper {
	@Insert("insert into calendar (date_start,date_end,cal_category,cal_title,cal_memo,mem_cookie) values (#{date_start},#{date_end},#{cal_category},#{cal_title},#{cal_memo},#{mem_cookie})")
	public void insertCal(CalendarVO calendar);
	
	@Select("select * from calendar where mem_cookie=#{mem_cookie}")
	public List<CalendarVO> allDataList(String mem_cookie);
	
	@Select("select * from calendar where cal_num=#{cal_num}")
	public CalendarVO getOneData(int cal_num);
}
