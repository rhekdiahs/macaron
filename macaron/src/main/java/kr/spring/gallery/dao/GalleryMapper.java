package kr.spring.gallery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.gallery.vo.GalleryImgVO;
import kr.spring.gallery.vo.GalleryVO;

@Mapper
public interface GalleryMapper {
	/*==================================
    			 	갤러리
	====================================*/
	@Select("SELECT g_num, g_title, g_place, g_cookie, g_date FROM gallery WHERE g_cookie = #{g_cookie} ORDER BY g_date DESC limit 0,4")
	public List<GalleryVO> getGalleryList(String g_cookie);

	@Select("SELECT * FROM img_gallery where g_num = #{g_num} ORDER BY img_num asc limit 1")
	public GalleryImgVO getThumbImg(Integer g_num);
	
	@Insert("INSERT INTO gallery (mem_num) VALUES (#{mem_num})")
	public void insertGallery(Integer mem_num);
	
	@Update("Update gallery SET g_title = #{g_title}, g_content = #{g_content}, g_place = #{g_place}, g_cookie = #{g_cookie}, g_hash = #{g_hash} WHERE g_num = #{g_num}")
	public void insertGalleryContent(GalleryVO gallery);

	@Insert("INSERT INTO img_gallery (img_filename, img_file, g_num) VALUES (#{img_filename}, #{img_file}, #{g_num})")
	public void insertGalleryImg(GalleryImgVO galleryImg);
	
	@Select("SELECT g_num FROM gallery WHERE g_title IS NULL")
	public Integer getG_num();
	
	@Select("SELECT * FROM gallery WHERE g_num = #{g_num}")
	public GalleryVO getGalleryDetail(Integer g_num);
	
	@Select("SELECT * FROM img_gallery WHERE g_num = #{g_num}")
	public List<GalleryImgVO> getGalleryDetailImg(Integer g_num);
}
