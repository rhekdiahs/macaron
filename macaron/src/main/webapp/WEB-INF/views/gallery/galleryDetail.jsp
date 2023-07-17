<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab928e5929563772b2932e6182f6b7d9&libraries=services"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/galleryDetail.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryDetailCarousel.js"></script>

<div id = "detail_title_wrap">
	<p>야경 본 날</p>
	<a><span>반포 한강공원</span></a>
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
<div id = "detail_radio_wrap">
	<input type = "radio" name = "slide-radios" class = "slide-radio" id = "slide-radio-1" onclick="return(false);" checked>
	<input type = "radio" name = "slide-radios" class = "slide-radio" id = "slide-radio-2" onclick="return(false);">
	<input type = "radio" name = "slide-radios" class = "slide-radio" id = "slide-radio-3" onclick="return(false);">
	<input type = "radio" name = "slide-radios" class = "slide-radio" id = "slide-radio-4" onclick="return(false);">
	
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
			시들어 가는 노년에게서 구하지 못할 바이며 오직 우리 청춘에 서만 구할 수 있는 것이다 청춘은 인생의 황금시대다
			우리는 이 황금시대의 가치를 충분히 발휘하기 위하여 이 황금시대를 영원히 붙잡아 두기 위하여 힘차게 노래하며 힘차게
		</p>
	</div>
	<div id = 'detail_hash'>
		<p>
			<span>
				<a>#해시태그</a>
				<a>#해시태그</a>
				<a>#해시태그</a>
				<a>#해시태그</a>
				<a>#해시태그</a>
				<a>#해시태그</a>
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