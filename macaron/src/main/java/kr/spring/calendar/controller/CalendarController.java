package kr.spring.calendar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.calendar.service.CalendarService;
import kr.spring.calendar.vo.CalendarVO;

@Controller
public class CalendarController {
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	@Autowired
	private CalendarService calendarService;
	
	@ModelAttribute
	public CalendarVO initCommand() {
		return new CalendarVO();
	}
	
	@RequestMapping("/cal/main.do")
	public String main() {
		return "calMain";
	}
	

	@GetMapping("/cal/write.do")
	public String galleryWriteForm() {
		return "calWrite";
	}
}
