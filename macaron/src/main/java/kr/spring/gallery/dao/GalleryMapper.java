package kr.spring.gallery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.gallery.vo.GalleryImgVO;
import kr.spring.gallery.vo.GalleryReplyVO;
import kr.spring.gallery.vo.GalleryVO;

@Mapper
public interface GalleryMapper {
	
	@Select("SELECT mem_nick FROM member_detail WHERE mem_num = #{mem_num}")
	public String getMem_nick(Integer mem_num);
	
	
	/*==================================
    			 	갤러리
	====================================*/
	
	//갤러리 리스트 불러오기
	@Select("SELECT g_num, g_title, g_place, g_cookie, g_date FROM gallery WHERE g_cookie = #{g_cookie} ORDER BY g_date DESC limit 0,4")
	public List<GalleryVO> getGalleryList(String g_cookie);
	//갤러리 메인 썸네일 가져오기
	@Select("SELECT * FROM img_gallery where g_num = #{g_num} ORDER BY img_num asc limit 1")
	public GalleryImgVO getThumbImg(Integer g_num);
	//Ajax로 먼저 갤러리 테이블 생성하기
	@Insert("INSERT INTO gallery (mem_num) VALUES (#{mem_num})")
	public void insertGallery(Integer mem_num);
	//갤러리 텍스트 콘텐츠 저장하기
	@Update("Update gallery SET g_title = #{g_title}, g_content = #{g_content}, g_place = #{g_place}, g_cookie = #{g_cookie}, g_hash = #{g_hash} WHERE g_num = #{g_num}")
	public void insertGalleryContent(GalleryVO gallery);
	//Ajax로 갤러리 이미지 저장하기
	@Insert("INSERT INTO img_gallery (img_filename, img_file, g_num) VALUES (#{img_filename}, #{img_file}, #{g_num})")
	public void insertGalleryImg(GalleryImgVO galleryImg);
	//Ajax로 생성된 갤러리 행 가져오기
	@Select("SELECT g_num FROM gallery WHERE g_title IS NULL")
	public Integer getG_num();
	//갤러리 상세 불러오기
	@Select("SELECT * FROM gallery WHERE g_num = #{g_num}")
	public GalleryVO getGalleryDetail(Integer g_num);
	//갤러리 상세 이미지 불러오기
	@Select("SELECT * FROM img_gallery WHERE g_num = #{g_num}")
	public List<GalleryImgVO> getGalleryDetailImg(Integer g_num);
	
	//갤러리 댓글 리스트 불러오기
	@Select("SELECT * FROM reply_gallery r JOIN member_detail d ON r.mem_num = d.mem_num WHERE g_num = #{g_num} ORDER BY r.re_date DESC")
	public List<GalleryReplyVO> getGalleryReplyList(Integer g_num);
	//갤러리 댓글 저장하기
	@Insert("INSERT INTO reply_gallery (re_content, g_num, mem_num) VALUES (#{re_content}, #{g_num}, #{mem_num})")
	public void insertGalleryReply(GalleryReplyVO galleryReply);
	//갤러리 댓글 하나 가져오기
	@Select("SELECT * FROM reply_gallery WHERE re_num = #{re_num}")
	public GalleryReplyVO selectGalleryReply(Integer re_num);
	//갤러리 댓글 수정
	@Update("UPDATE reply_gallery SET re_content = #{re_content}, re_date = curdate()")
	public void updateGalleryReply(GalleryReplyVO galleryReply);
	//갤러리 댓글 하나 삭제
	@Delete("DELETE FROM reply_gallery WHERE re_num = #{re_num}")
	public void deleteGalleryReply(Integer re_num);
	//갤러리 글 삭제시 댓글 모두 삭제
	@Delete("DELETE FROM reply_gallery WHERE g_num = #{g_num}")
	public void deleteAllGalleryReply(Integer g_num);
	
	
	
}
