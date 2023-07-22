package kr.spring.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.gallery.dao.GalleryMapper;
import kr.spring.gallery.vo.GalleryImgVO;
import kr.spring.gallery.vo.GalleryVO;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	private GalleryMapper galleryMapper;

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

}
