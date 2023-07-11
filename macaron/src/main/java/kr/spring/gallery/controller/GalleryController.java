package kr.spring.gallery.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.gallery.service.GalleryService;
import kr.spring.gallery.vo.GalleryVO;

@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/gallery/main.do")
	public String galleryList(HttpSession session, Model model) {
		
		
		return "galleryMain";
	}
	
	
	@GetMapping("/gallery/write.do")
	public String galleryWriteForm() {
		return "galleryWrite";
	}
	
	@PostMapping("/gallery/write.do")
	public String galleryWrite(GalleryVO galleryVO, Model model, HttpServletRequest request, HttpSession session) {
		
		
		return "main"; //나중에 resultView로 바꾸기
	}
}
