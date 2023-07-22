package kr.spring.gallery.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.gallery.service.GalleryService;
import kr.spring.gallery.vo.GalleryImgVO;
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
	
	@RequestMapping("/gallery/insertGallery.do")
	@ResponseBody
	public Map<String, Object> insertGalleryTable(HttpSession session){
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		try {
			galleryService.insertGallery(user.getMem_num());			
			
			mapAjax.put("g_num", galleryService.getG_num());
			mapAjax.put("result", "successRow");
		}catch(Exception e) {
			mapAjax.put("result", "failRow");			
		}
		
		return mapAjax;		
	}
	
	@RequestMapping("/gallery/insertGalleryImage.do")
	@ResponseBody
	public Map<String, String> insertImage(GalleryImgVO galleryImg, HttpSession session){
				
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		//위 ajax에서 리턴 받은 g_num ajax 송신할 때 세팅해주고 넘겨받자
		
		try {
			galleryService.insertGalleryImg(galleryImg);
			
			mapAjax.put("result", "success");
		}catch(Exception e) {
			e.printStackTrace();
			mapAjax.put("result", "fail");			
		}
		return mapAjax;
	}
}
