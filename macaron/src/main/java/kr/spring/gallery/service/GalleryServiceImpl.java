package kr.spring.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.gallery.dao.GalleryMapper;
import kr.spring.gallery.vo.GalleryImgVO;
import kr.spring.gallery.vo.GalleryReplyVO;
import kr.spring.gallery.vo.GalleryVO;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	private GalleryMapper galleryMapper;
	
	@Override
	public String getMem_nick(Integer mem_num) {
		return galleryMapper.getMem_nick(mem_num);
	}

	
	

	@Override
	public List<GalleryVO> getGalleryList(String g_cookie) {
		return galleryMapper.getGalleryList(g_cookie);
	}

	@Override
	public void insertGalleryContent(GalleryVO gallery) {
		galleryMapper.insertGalleryContent(gallery);
	}

	@Override
	public GalleryVO getGalleryDetail(Integer g_num) {
		return galleryMapper.getGalleryDetail(g_num);
	}

	@Override
	public void insertGallery(Integer mem_num) {
		galleryMapper.insertGallery(mem_num);
	}

	@Override
	public void insertGalleryImg(GalleryImgVO galleryImg) {
		galleryMapper.insertGalleryImg(galleryImg);
	}

	@Override
	public Integer getG_num() {
		return galleryMapper.getG_num();
	}

	@Override
	public GalleryImgVO getThumbImg(Integer g_num) {
		return galleryMapper.getThumbImg(g_num);
	}

	@Override
	public List<GalleryImgVO> getGalleryDetailImg(Integer g_num) {
		return galleryMapper.getGalleryDetailImg(g_num);
	}

	@Override
	public List<GalleryReplyVO> getGalleryReplyList(Integer g_num) {
		return galleryMapper.getGalleryReplyList(g_num);
	}

	@Override
	public void insertGalleryReply(GalleryReplyVO galleryReply) {
		galleryMapper.insertGalleryReply(galleryReply);
	}

	@Override
	public GalleryReplyVO selectGalleryReply(Integer re_num) {
		return galleryMapper.selectGalleryReply(re_num);
	}

	@Override
	public void updateGalleryReply(GalleryReplyVO galleryReply) {
		galleryMapper.updateGalleryReply(galleryReply);
	}

	@Override
	public void deleteGalleryReply(Integer re_num) {
		galleryMapper.deleteGalleryReply(re_num);
	}



	@Override
	public GalleryReplyVO selectRecentReply(GalleryReplyVO galleryReply) {
		return galleryMapper.selectRecentReply(galleryReply);
	}




	@Override
	public MemberVO getMember(Integer mem_num) {
		return galleryMapper.getMember(mem_num);
	}


	@Override
	public void deleteGallery(String g_cookie) {
		int[] g_numList = galleryMapper.selectGnumForDelete(g_cookie);
		galleryMapper.deleteGalleryImg(g_numList);
		galleryMapper.deleteAllGalleryReply(g_numList);
		galleryMapper.deleteGallery(g_cookie);
	}


}
