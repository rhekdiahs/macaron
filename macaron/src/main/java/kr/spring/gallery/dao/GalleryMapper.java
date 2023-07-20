package kr.spring.gallery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.gallery.vo.GalleryVO;

@Mapper
public interface GalleryMapper {
	/*==================================
    			 	갤러리
	====================================*/
	@Select("SELECT g_num, g_title, g_place, g_cookie, g_date FROM gallery WHERE g_cookie = #{g_cookie} ORDER BY g_date DESC limit 0,3")
	public List<GalleryVO>getGalleryList(String g_cookie);
	
	@Insert("INSERT INTO gallery (g_title, g_content, g_place, g_cookie, g_hash, mem_num) VALUES (#{g_title}, #{g_content}, #{g_place}, #{g_cookie}, #{g_hash}, #{mem_num})")
	public void insertGalleryContent(GalleryVO gallery);
	
	@Select("SELECT * FROM gallery WHERE g_num = #{g_num}")
	public GalleryVO getGalleryDetail(Integer g_num);
}
