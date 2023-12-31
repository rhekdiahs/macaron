package kr.spring.gallery.service;

import java.util.List;

import kr.spring.gallery.vo.GalleryImgVO;
import kr.spring.gallery.vo.GalleryReplyVO;
import kr.spring.gallery.vo.GalleryVO;
import kr.spring.member.vo.MemberVO;

public interface GalleryService {
	
	public String getMem_nick(Integer mem_num);
	
	public MemberVO getMember(Integer mem_num);

	public List<GalleryVO>getGalleryList(String g_cookie, int start, int end);
	
	public GalleryImgVO getThumbImg(Integer g_num);
	
	public void insertGallery(Integer mem_num);
	
	public void insertGalleryContent(GalleryVO gallery);
	
	public void insertGalleryImg(GalleryImgVO galleryImg);
	
	public Integer getG_num();
	
	public GalleryVO getGalleryDetail(Integer g_num);
	
	public List<GalleryImgVO> getGalleryDetailImg(Integer g_num);
	
	//갤러리 댓글 리스트 불러오기
	public List<GalleryReplyVO> getGalleryReplyList(Integer g_num);
	//갤러리 댓글 저장하기
	public void insertGalleryReply(GalleryReplyVO galleryReply);
	//최근
	public GalleryReplyVO selectRecentReply(GalleryReplyVO galleryReply);
	//갤러리 댓글 하나 가져오기
	public GalleryReplyVO selectGalleryReply(Integer re_num);
	//갤러리 댓글 수정
	public void updateGalleryReply(GalleryReplyVO galleryReply);
	//갤러리 댓글 하나 삭제
	public void deleteGalleryReply(Integer re_num);

	
	public void deleteGallery(String g_cookie);
}
