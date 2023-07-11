package kr.spring.gallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.gallery.dao.GalleryMapper;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	private GalleryMapper galleryMapper;
}
