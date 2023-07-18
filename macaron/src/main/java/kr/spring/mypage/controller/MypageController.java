package kr.spring.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	
	@RequestMapping("/mypage/main.do")
	public String main() {
		
		return "myPage";
	}
}
