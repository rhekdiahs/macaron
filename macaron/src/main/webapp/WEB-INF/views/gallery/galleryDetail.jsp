<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>    
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/galleryDetail.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryDetailCarousel.js"></script>

<div id = "detail_title_wrap">
	<p>${gallery.g_title}</p>
	<a>
		<span>${gallery.g_place }</span>
	</a>
</div>
<div id="detail_img_slider">
	<div id = "slide_cont">
		<div class = "slide-cont-img" id = "slide_cont_img_1">
			<img src="../image_bundle/detail_sample.jpg">			
		</div>
		<div class = "slide-cont-img" id = "slide_cont_img_2">
			<img src="../image_bundle/three-macarons.png">
		</div>
		<div class = "slide-cont-img" id = "slide_cont_img_3">
			<img src="../image_bundle/gal_sample.jpg">
		</div>
		<div class = "slide-cont-img" id = "slide_cont_img_4">
			<img src="../image_bundle/three-macarons.png">
		</div>
	</div>
</div>
<div id = "detail_radio_wrap">
	<input type = "radio" name = "slide-radios" class = "slide-radio" onclick="return(false);" checked>
	<input type = "radio" name = "slide-radios" class = "slide-radio" onclick="return(false);">
	<input type = "radio" name = "slide-radios" class = "slide-radio" onclick="return(false);">
	<input type = "radio" name = "slide-radios" class = "slide-radio" onclick="return(false);">
	
<!-- 	<div class = "radio-label">
		<label for = "slide-radio-1">1</label>
		<label for = "slide-radio-2">2</label>
		<label for = "slide-radio-3">3</label>
		<label for = "slide-radio-4">4</label>
	</div> -->
</div>
<hr width = "90%">
<div id = "detail_content_wrap">
	<div id="detail_content">
		<p>
			${gallery.g_content}
		</p>
	</div>
	<div id = 'detail_hash'>
		<p>
			<span>
			<c:forEach var = "hash" items = "${hashtag}">
				<c:if test="${!empty hash}">
					<a>#${hash }</a>
				</c:if>
			</c:forEach>
			</span>

		</p>
	</div>
	<div id = "detail_reply_wrap">
		<p id = "reply_header">댓글</p>
		
		<ul id = "reply_list">
			<li>
				<img src = "../image_bundle/mypage_icon.png">
				<div class = "reply">
					<span><strong>앵밀</strong> | </span>
					<span>2023.07.17</span>
					<div>
						<p>좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.좋았슴니다.</p>
					</div>
				</div>
				<hr style = "border : 1px solid whitesmoke;">
			</li>
			<li>
				<img src = "../image_bundle/mypage_icon.png">
				<div class = "reply">
					<span><strong>앵밀</strong> | </span>
					<span>2023.07.17</span>
					<div>
						<p>좋았슴니다.</p>
					</div>
				</div>
				<hr style = "border : 1px solid whitesmoke;">
			</li>
		</ul>
</div>
</div>

<div style = "margin-bottom : 40px;">

</div>