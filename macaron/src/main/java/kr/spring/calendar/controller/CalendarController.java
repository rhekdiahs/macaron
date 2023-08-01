package kr.spring.calendar.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.calendar.service.CalendarService;
import kr.spring.calendar.vo.CalendarVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class CalendarController {
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	@Autowired
	private CalendarService calendarService;
	
	@ModelAttribute
	public CalendarVO initCommand() {
		return new CalendarVO();
	}
	
	@RequestMapping("/calendar/main.do")
	public String main() {
		return "calMain";
	}
	
	@GetMapping("/calendar/cal.do")
	@ResponseBody
	public List<Object> ajax(HttpSession session){
		MemberVO member = (MemberVO)session.getAttribute("user");
		
		List<CalendarVO> ajax = calendarService.getAllData(member.getMem_cookie());
		Map<String, Object> map = new HashMap<String, Object>();
		
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		
		int ajax_len = ajax.size();
		
		for(int i=0;i<ajax_len;i++) {
			map.put("cal_num", ajax.get(i).getCal_num());
			map.put("title", ajax.get(i).getCal_title());
			map.put("start", ajax.get(i).getDate_start());
			map.put("end", ajax.get(i).getDate_end());
			map.put("color", ajax.get(i).getCal_category());  
			if(ajax.get(i).getCal_category().equals("chartreuse")) {
				map.put("textColor", "#000");
			}
			
			jsonObj = new JSONObject(map);
			jsonArr.put(jsonObj);
		}
		
		return jsonArr.toList(); 
	}
	
	@GetMapping("/calendar/write.do")
	public String calendarWriteForm() {
		return "calWrite";
	}
	
	@PostMapping("/calendar/write.do")
	public String calendarWriteDone(HttpSession session, @ModelAttribute CalendarVO calendarVO, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("user");
		
		String mem_cookie = member.getMem_cookie();
		
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		
		try {
			date = (Date) format.parse(calendarVO.getDate_end().replaceAll(" - ", "-"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		
		String date_end = format.format(cal.getTime());
		
		calendarVO.setMem_cookie(mem_cookie);
		calendarVO.setDate_start(calendarVO.getDate_start().replaceAll(" - ", "-"));
		calendarVO.setDate_end(date_end);
		
		calendarService.insertCal(calendarVO);
		
		return "redirect:/calendar/main.do";
	}
	
	/*
	@RequestMapping("/cal/detail.do")
	public String scheduleDetail(@RequestParam int cal_num) {
		logger.debug("CAL_NUM CHECK >>>" + cal_num);
		
		return "redirect:/cal/main.do";
	}
	*/
}
