package kr.spring.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.gallery.dao.GalleryMapper;
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

}
