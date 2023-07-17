<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab928e5929563772b2932e6182f6b7d9&libraries=services"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/galleryDetail.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryDetailCarousel.js"></script>

<div>
	<p>야경 본 날</p>
	<a><p>반포 한강공원</p></a>
</div>
<div id="detail_img_slider">
	<div id = "slide-cont" class= "nothing">
		<div class = "slide-cont-img" id = "slide-cont-img-1">
			<img src="../image_bundle/detail_sample.jpg">			
		</div>
		<div class = "slide-cont-img" id = "slide-cont-img-2">
			<img src="../image_bundle/three-macarons.png">
		</div>
		<div class = "slide-cont-img" id = "slide-cont-img-3">
			<img src="../image_bundle/gal_sample.jpg">
		</div>
		<div class = "slide-cont-img" id = "slide-cont-img-4">
			<img src="../image_bundle/three-macarons.png">
		</div>
	</div>
</div>
<div>
	<input type = "radio" name = "slide-radios" class = "slide-radio" id = "slide-radio-1" checked>
	<input type = "radio" name = "slide-radios" class = "slide-radio" id = "slide-radio-2">
	<input type = "radio" name = "slide-radios" class = "slide-radio" id = "slide-radio-3">
	<input type = "radio" name = "slide-radios" class = "slide-radio" id = "slide-radio-4">
	
	<div class = "radio-label">
		<label for = "slide-radio-1">1</label>
		<label for = "slide-radio-2">2</label>
		<label for = "slide-radio-3">3</label>
		<label for = "slide-radio-4">4</label>
	</div>
</div>
    