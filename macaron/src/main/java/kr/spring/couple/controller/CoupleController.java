package kr.spring.couple.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.couple.service.CoupleService;
import kr.spring.couple.vo.CoupleVO;
import kr.spring.mail.controller.MailService;
import kr.spring.mail.controller.MailVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
public class CoupleController {
	private static final Logger logger= LoggerFactory.getLogger(CoupleController.class);
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private CoupleService coupleService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MailVO mailVO;

	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	@ModelAttribute
	public CoupleVO initCommand2() {
		return new CoupleVO();
	}
	
	/*-------------------------------
	 			커플 등록하기
	 --------------------------------*/
	
	//등록 폼 호출
	@RequestMapping("/couple/send.do")
	public String form(HttpSession session, Model model) {
		MemberVO member = (MemberVO)session.getAttribute("user");
		
		logger.debug("EMAIL >>>>>>>>>>>>>" + member.getMem_email());
		logger.debug("COOKIE >>>>>>>>>>>>>" + member.getMem_cookie());
		
		if(memberService.checkCookie(member.getMem_email()) == null) {

			model.addAttribute("member", member);
			
			return "sendCode";
		}else {
			
			model.addAttribute("member", member);
			//model.addAttribute("code", member.getMem_cookie());
			
			return "checkCode";
		}
	}
	
	//커플코드 전송
	@RequestMapping("/couple/checkEmail.do")
	@ResponseBody
	public Map<String, Object> check(@RequestParam String mem_email, @RequestParam String partner_email,HttpSession session) throws Exception{
		Map<String, Object> ajax = new HashMap<String, Object>();
			logger.debug("내 이메일 = " + mem_email + "/// 상대방 이메일 = " + partner_email);
			
			//해당 이메일로 계정을 조회 
			//계정 없으면 message : not exist
			//자기 이메일 입력한 경우 message : same user
			//계정이 있고 인증코드 없음 -> message : not confirmed
			//이미 인증코드가 있음 -> message : confirmed
			
			if(memberService.checkEmailCount(partner_email) == 0){
				ajax.put("message", "not exist");
			}else {
				if(mem_email.equals(partner_email)) {
					ajax.put("message", "same user");
				}else {
					if(memberService.checkCookie(partner_email) != null){
						ajax.put("message", "confirmed");
					}else {
						String code;
						
						do{
							code = code();
						}while(!checkCode(code));
						
						logger.debug("생성된 랜덤 커플 코드 >>>>>> " + code);
						
						memberService.setCookie(mem_email,partner_email,code);
						
						mailVO.setAddress(partner_email);
						
						mailVO.setTitle("Macaron 커플인증코드 안내");
						mailVO.setMessage("커플인증코드 11자리는 " + code + " 입니다.");
						
						mailService.mailSend(mailVO);
						
						ajax.put("message", "not confirmed");
						ajax.put("code", code);
					}
				}
			}
		return ajax;
	}
	
	//커플코드 인증
	@RequestMapping("/couple/check.do")
	public String check(HttpSession session, @RequestParam(value="code", required=false) String code) {

		logger.debug("COOKIEEEEEEE ====== " + code);
		
		return "checkCode";
	}
	
	//커플 등록
	@RequestMapping("/couple/register.do")
	public String register(HttpSession session, Model model) {
		String mem_email = (String)session.getAttribute("user_email");
		String cp_cookie = memberService.checkCookie(mem_email);
		
		logger.debug(">>>>>>>>>>>>>>등록시 EMAIL >>>" + mem_email);
		logger.debug(">>>>>>>>>>>>>>등록시 COOKIE>>>" + cp_cookie);
		
		CoupleVO couple = initCommand2();
		
		couple.setCp_cookie(cp_cookie);

		if(coupleService.checkCookie(cp_cookie) == 0) {
			couple.setCp_1(mem_email);
			coupleService.registerCp_1(couple);
			
			model.addAttribute("message", "커플 신청 완료");
		}else {
			couple.setCp_2(mem_email);
			coupleService.registerCp_2(couple);
			
			model.addAttribute("message", "커플 등록 완료");
		}
		
		model.addAttribute("url", "/main/main.do");
		
		return "common/resultView";
	}
	
	@RequestMapping("/couple/wait.do")
	public String waiting() {
		return "waitingView";
	}
	
	public String code() {
		Random random = new Random();
		
		StringBuffer code = new StringBuffer();
		
		for(int i=0;i<11;i++) {
			int rannum = random.nextInt(30);
			
			//0-9 : 임의의 자리 숫자를 발생
			//10-19 : 임의의 소문자 알파벳 발생
			//20-25 : 임의의 대문자 알파벳 발생
			
			if(rannum / 10 == 0) {
				code.append(random.nextInt(10));
			}else if(rannum / 10 == 1) {
				code.append(RandomStringUtils.randomAlphabetic(1).toLowerCase());
			}else {
				code.append(RandomStringUtils.randomAlphabetic(1));
			}
		}
		
		return code.toString();	
	}
	
	public boolean checkCode(String code) {
		boolean result = false;
		
		//해당 인증코드가 cookie컬럼에 저장된 계정이 있는지 확인
		if(coupleService.checkCookie(code) == 0) {
			result = true;
		}
		
		return result;
	}
}
