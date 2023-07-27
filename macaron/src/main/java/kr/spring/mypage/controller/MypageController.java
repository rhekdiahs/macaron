package kr.spring.mypage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.couple.vo.CoupleVO;
import kr.spring.gallery.dao.GalleryMapper;
import kr.spring.gallery.service.GalleryService;
import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.service.MypageService;
import kr.spring.util.FileUtil;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/mypage/main.do")
	public String main(HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
	
		if(user == null) return "redirect:/member/login.do";
		
		MemberVO member = mypageService.selectMember(user.getMem_num());
		
		CoupleVO couple = mypageService.selectCouple(member.getMem_cookie());
		
		String partnerEmail = null;
		
		if(member.getMem_email().equals(couple.getCp_1())) {
			partnerEmail = couple.getCp_2();
		}else {
			partnerEmail = couple.getCp_1();
		}
		int partner = mypageService.getPartner(partnerEmail);
		
		Long since = 0L;
		if(couple.getCp_date() == null) {
			
			String firstDate = "20230514";
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			
			try {
				//-----------시작일 없어서 임시---------//
				Date date = format.parse(firstDate);
				
				long dateConv = date.getTime();
								
				java.sql.Date sqlDate = new java.sql.Date(dateConv);
				
				couple.setCp_date(sqlDate);
				
				//-------------------------------------//
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		Calendar firstDateC = new GregorianCalendar();
		firstDateC.setTimeInMillis(couple.getCp_date().getTime());
		Date firstDateD = firstDateC.getTime();
		
		Calendar nowDateC = Calendar.getInstance();
		Date nowDateD = nowDateC.getTime();
		
		
		long diff = nowDateD.getTime() - firstDateD.getTime();
		
		since = TimeUnit.MILLISECONDS.toDays(diff) + 1;
		
		Map<String, Object> map1 = calcDate(((since.intValue()-1)/100 + 1) * 100, couple.getCp_date().toLocalDate());
		Map<String, Object> map2 = calcDate((((since.intValue()-1)/100 + 1) * 100) + 100, couple.getCp_date().toLocalDate());
		Map<String, Object> map3 = calcDate((((since.intValue()-1)/100 + 1) * 100) + 200, couple.getCp_date().toLocalDate());
		Map<String, Object> map4 = calcDate(((since.intValue()-1)/365 + 1) * 365, couple.getCp_date().toLocalDate());
		
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		Map<String, Object> tmp;
		for(int i = 0; i < 3; i++) {
			for(int j = i+1; j < 4; j++) {
				if((int)list.get(j).get("day") < (int)list.get(i).get("day")) {
					tmp = list.get(j);
					list.remove(j);
					list.add(j, list.get(i));
					list.remove(i);
					list.add(i,tmp);
				}
			}
		}
		
		model.addAttribute("daylist", list);
		model.addAttribute("member", member);
		model.addAttribute("couple", couple);
		model.addAttribute("partner", partner);
		model.addAttribute("since", since);
		
		
		return "myPage";
	}

	@RequestMapping("/mypage/personal.do")
	public String personal(HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		MemberVO member = galleryService.getMember(user.getMem_num());
		
		model.addAttribute("member", member);
		
		return "myPagePersonal";
	}
	
	@RequestMapping("/mypage/updateMember.do")
	@ResponseBody
	public Map<String, String> update(MemberVO member, HttpSession session) {
		
		Map<String, String> mapAjax = new HashMap<>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		member.setMem_num(user.getMem_num());
		try {
			mypageService.updateMember(member);
			mapAjax.put("result", "success");
		}catch(Exception e) {
			e.printStackTrace();
			mapAjax.put("result", "fail");
		}		
		return mapAjax;
	}
	
	@RequestMapping("/mypage/deleteMember.do")
	public String delete(int mem_num, HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO member = galleryService.getMember(user.getMem_num());
		
		if(user.getMem_num() == member.getMem_num()) {
			galleryService.deleteGallery(member.getMem_cookie());
			
			session.invalidate();
			
			model.addAttribute("message", "회원탈퇴 완료");
	        model.addAttribute("url", "/member/login.do");
		}
		
		return "common/resultView";
	}
	
	
	public void viewProfile(MemberVO member, HttpServletRequest request, Model model) {
		if(member.getMem_photo() == null) {
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/mypage_icon.png"));
			model.addAttribute("imageFile", readbyte);
		}else {
			model.addAttribute("imageFile", member.getMem_photo());
		}
	}
	
	@RequestMapping("/mypage/viewProfile.do")
	public String getProfileByMem_num(@RequestParam int mem_num, HttpSession session, HttpServletRequest request, Model model) {
		
		MemberVO member = mypageService.selectMember(mem_num);
		
		viewProfile(member, request, model);
		
		return "imageView";
	}
	
	@RequestMapping("/mypage/updateProfileImg.do")
	@ResponseBody
	public Map<String, String> updateProfile(MemberVO member, HttpSession session){
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
				
		if(user == null) {
			mapAjax.put("result", "logout");
		}else {
			member.setMem_num(user.getMem_num());
			mypageService.updateProfile(member);
			mapAjax.put("result", "success");
		}
		
		return mapAjax;
	}
	
	private Map<String, Object> calcDate(int since, LocalDate first) {
		Map<String, Object> map = new HashMap<>();

		LocalDate AnniversaryDate = first.plusDays(since);
		
		ZoneId zoneId = ZoneId.systemDefault();
		
		Date AnniDateConvert = Date.from(AnniversaryDate.atStartOfDay(zoneId).toInstant());
		
		Date today = new Date();
		
		long diff = AnniDateConvert.getTime() - today.getTime();
		
		Long remain = ((diff / (24 * 60 * 60 * 1000L)) % 365);
		
		map.put("day", since);
		map.put("date", AnniversaryDate);
		map.put("remain", remain);
		
		return map;
	}
}
