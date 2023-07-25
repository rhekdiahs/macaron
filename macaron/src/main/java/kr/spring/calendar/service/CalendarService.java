package kr.spring.calendar.service;

import java.util.List;

import kr.spring.calendar.vo.CalendarVO;

public interface CalendarService {
	public void insertCal(CalendarVO cal);
	
	public List<CalendarVO> getAllData(String mem_cookie);
}
