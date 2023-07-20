package kr.spring.gallery.service;

import java.util.List;

import kr.spring.gallery.vo.GalleryVO;

public interface GalleryService {

	public List<GalleryVO>getGalleryList(String g_cookie);
	
	public void insertGalleryContent(GalleryVO gallery);
	
	public GalleryVO getGalleryDetail(Integer g_num);
}
