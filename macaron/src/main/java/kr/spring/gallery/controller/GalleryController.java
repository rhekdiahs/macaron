package kr.spring.gallery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.gallery.service.GalleryService;
import kr.spring.gallery.vo.GalleryVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.StringUtil;

@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/gallery/main.do")
	public String galleryList(HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		List<GalleryVO> list = galleryService.getGalleryList(user.getMem_cookie());
		
		model.addAttribute("list", list);
		
		return "galleryMain";
	}
	
	
	@GetMapping("/gallery/write.do")
	public String galleryWriteForm() {
		return "galleryWrite";
	}
	
	@PostMapping("/gallery/write.do")
	public String galleryWrite(GalleryVO galleryVO, Model model, HttpSession session) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		galleryVO.setG_cookie(user.getMem_cookie());
		galleryVO.setMem_num(user.getMem_num());
		
		galleryService.insertGalleryContent(galleryVO);
		
		model.addAttribute("message", "글 등록이 완료되었습니다.");
		model.addAttribute("url", "/gallery/main.do");
		
		return "common/resultView";
	}
	
	@RequestMapping("/gallery/detail.do")
	public String galleryDetail(@RequestParam int g_num, Model model) {
		
		GalleryVO gallery = galleryService.getGalleryDetail(g_num);
		
		String[] hashtag = gallery.getG_hash().split("#");
		
		
		gallery.setG_title(StringUtil.useNoHtml(gallery.getG_title()));
		if(gallery.getG_content() != null) {
			gallery.setG_content(StringUtil.useBrNoHtml(gallery.getG_content()));
		}
		
		model.addAttribute("gallery", gallery);
		model.addAttribute("hashtag", hashtag);
		
		return "galleryDetail";
	}
}
