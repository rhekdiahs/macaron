package kr.spring.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.calendar.dao.CalendarMapper;
import kr.spring.calendar.vo.CalendarVO;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private CalendarMapper calendarMapper;
	
	@Override
	public void insertCal(CalendarVO cal) {
		calendarMapper.insertCal(cal);
	}

	@Override
	public List<CalendarVO> getAllData(String mem_cookie) {
		return calendarMapper.allDataList(mem_cookie);
	}

	@Override
	public CalendarVO getOneData(int cal_num) {
		return calendarMapper.getOneData(cal_num);
	}

	
}
