package kr.spring.calendar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.calendar.vo.CalendarVO;

@Mapper
public interface CalendarMapper {
	@Insert("insert into calendar (date_start,date_end,cal_category,cal_title,cal_memo,mem_cookie) values (#{date_start},#{date_end},#{cal_category},#{cal_title},#{cal_memo},#{mem_cookie})")
	public void insertCal(CalendarVO calendar);
	
	@Select("select * from calendar where mem_cookie=#{mem_cookie}")
	public List<CalendarVO> allDataList(String mem_cookie);
	
	@Select("select * from calendar where cal_num=#{cal_num}")
	public CalendarVO getOneData(int cal_num);
	
	@Delete("delete from calendar where cal_num=#{cal_num}")
	public void deleteCal(int cal_num);
	
	@Update("update calendar set date_start=#{date_start},date_end=#{date_end},cal_category=#{cal_category},cal_title=#{cal_title},cal_memo=#{cal_memo} where cal_num=#{cal_num}")
	public void updateCal(CalendarVO calendar);
}
