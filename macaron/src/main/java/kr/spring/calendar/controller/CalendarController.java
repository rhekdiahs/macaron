package kr.spring.calendar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarController {
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	@RequestMapping("/cal/main.do")
	public String main() {
		return "calMain";
	}
}
