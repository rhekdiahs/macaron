package kr.spring.gallery.service;

import java.util.List;

import kr.spring.gallery.vo.GalleryImgVO;
import kr.spring.gallery.vo.GalleryVO;

public interface GalleryService {

	public List<GalleryVO>getGalleryList(String g_cookie);
	
	public void insertGallery(Integer mem_num);
	
	public void insertGalleryContent(GalleryVO gallery);
	
	public void insertGalleryImg(GalleryImgVO galleryImg);
	
	public Integer getG_num();
	
	public GalleryVO getGalleryDetail(Integer g_num);
}
