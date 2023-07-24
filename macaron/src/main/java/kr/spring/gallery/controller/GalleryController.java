package kr.spring.gallery.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
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
import kr.spring.gallery.vo.GalleryReplyVO;
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
		
		List<String> imgList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			int g_num = list.get(i).getG_num();
			GalleryImgVO galleryImgVo = galleryService.getThumbImg(g_num);
			String filename = galleryImgVo.getImg_filename();
			byte[] galImg = galleryImgVo.getImg_file();

			
			String ext = filename.substring(filename.lastIndexOf("."));
			if(ext.equalsIgnoreCase(".gif")) {
				ext = "image/gif";
			}else if(ext.equalsIgnoreCase(".png")) {
				ext = "image/png";
			}else {
				ext = "image/jpeg";
			}
			
			String galImg2Base64 = Base64.getEncoder().encodeToString(galImg);
			String fullBase64 = "data:" + ext + ";base64, " + galImg2Base64;
						
			imgList.add(i, fullBase64);
		}
		
		model.addAttribute("imgList", imgList);
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
	public String galleryDetail(@RequestParam int g_num, Model model, HttpSession session) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		GalleryVO gallery = galleryService.getGalleryDetail(g_num);
		
		List<GalleryImgVO> list = galleryService.getGalleryDetailImg(g_num);
		
		List<GalleryReplyVO> replyList = galleryService.getGalleryReplyList(g_num);
		
		List<String> imgList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			
			String filename = list.get(i).getImg_filename();
			byte[] galImg = list.get(i).getImg_file();

			
			String ext = filename.substring(filename.lastIndexOf("."));
			if(ext.equalsIgnoreCase(".gif")) {
				ext = "image/gif";
			}else if(ext.equalsIgnoreCase(".png")) {
				ext = "image/png";
			}else {
				ext = "image/jpeg";
			}
			
			String galImg2Base64 = Base64.getEncoder().encodeToString(galImg);
			String fullBase64 = "data:" + ext + ";base64, " + galImg2Base64;
						
			imgList.add(i, fullBase64);
		}
		
		String[] hashtag = gallery.getG_hash().split("#");
		
		
		gallery.setG_title(StringUtil.useNoHtml(gallery.getG_title()));
		if(gallery.getG_content() != null) {
			gallery.setG_content(StringUtil.useBrNoHtml(gallery.getG_content()));
		}
		
		model.addAttribute("imgList", imgList);
		model.addAttribute("replyList", replyList);
		model.addAttribute("gallery", gallery);
		model.addAttribute("hashtag", hashtag);
		model.addAttribute("user", user);
		
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
				
		try {
			galleryService.insertGalleryImg(galleryImg);
			
			mapAjax.put("result", "success");
		}catch(Exception e) {
			e.printStackTrace();
			mapAjax.put("result", "fail");			
		}
		return mapAjax;
	}
	
	@RequestMapping("/gallery/insertReply.do")
	@ResponseBody
	public Map<String, Object> insertReply(GalleryReplyVO reply, HttpSession session){
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			mapAjax.put("result","logout");
		}else {
			reply.setMem_num(user.getMem_num());
			
			galleryService.insertGalleryReply(reply);
			
			LocalDate now = LocalDate.now();
			
			Date date = Date.valueOf(now);
			
			reply.setRe_date(date);
			
			reply.setMem_nick(galleryService.getMem_nick(user.getMem_num()));
			
			mapAjax.put("reply", reply);
			mapAjax.put("result", "success");
		}
		return mapAjax;		
	}
}
