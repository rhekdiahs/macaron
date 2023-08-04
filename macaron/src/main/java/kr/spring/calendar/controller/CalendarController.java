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
	public String main(HttpSession session) {
		MemberVO member = (MemberVO)session.getAttribute("user");
		
		if(member == null) {
			return "redirect:/member/login.do";
		}
		
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
		
		/*
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			date = (Date) format.parse(calendarVO.getDate_end().replaceAll(" - ", "-"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		//cal.setTimeInMillis(cal.getTimeInMillis() + 1000*60*60*24);
		
		String date_end = format.format(cal.getTime());
		*/
		
		String date_end = formatDateEnd(calendarVO);
		
		calendarVO.setMem_cookie(mem_cookie);
		calendarVO.setDate_start(calendarVO.getDate_start().replaceAll(" - ", "-"));
		calendarVO.setDate_end(date_end);
		
		calendarService.insertCal(calendarVO);
		
		model.addAttribute("message", "일정이 등록되었습니다.");
        model.addAttribute("url", "/main/main.do");
        
		return "common/resultView";
	}
	
	
	@PostMapping("/calendar/detail.do")
	@ResponseBody
	public CalendarVO scheduleDetail(@RequestParam int cal_num) {
		logger.debug("CAL_NUM CHECK >>>" + cal_num);
		
		CalendarVO schedule = calendarService.getOneData(cal_num);
		
		return schedule;
	}
	
	@RequestMapping("/calendar/delete.do")
	public String delete(@RequestParam int cal_num, Model model){
		
		calendarService.deleteSchedule(cal_num);
		
		if(calendarService.getOneData(cal_num) == null) {
			model.addAttribute("message", "일정이 삭제되었습니다.");
		}else {
			model.addAttribute("message", "일정 삭제에 실패했습니다.");
		}
		
		model.addAttribute("url", "/main/main.do");
		
		return "common/resultView";
	}
	
	@GetMapping("/calendar/edit.do")
	public String editPage(@RequestParam int cal_num, Model model){
		
		CalendarVO calendar = calendarService.getOneData(cal_num);
		
		model.addAttribute("calendar", calendar);
		
		return "calEdit";
	}
	
	@PostMapping("/calendar/edit.do")
	public String edit(@ModelAttribute CalendarVO calendarVO, HttpSession session, Model model) {
		
		if(session.getAttribute("user") == null) {
			model.addAttribute("message", "로그인이 필요합니다.");
			model.addAttribute("url", "/member/login.do");
		}
		
		calendarVO.setDate_start(calendarVO.getDate_start().replaceAll(" - ", "-"));
		calendarVO.setDate_end(formatDateEnd(calendarVO));
		
		calendarService.editSchedule(calendarVO);
		
		model.addAttribute("message", "일정이 수정되었습니다.");
		model.addAttribute("url", "/main/main.do");
		
		return "common/resultView";
	}
	
	public String formatDateEnd(CalendarVO calendar) {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			date = (Date) format.parse(calendar.getDate_end().replaceAll(" - ", "-"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		//cal.setTimeInMillis(cal.getTimeInMillis() + 1000*60*60*24);
		
		String date_end = format.format(cal.getTime());
		
		return date_end;
	}
}
