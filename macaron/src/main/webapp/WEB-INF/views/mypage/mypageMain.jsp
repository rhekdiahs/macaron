<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryDetailCarousel.js"></script> --%>

<div id ="mypage_mem_info">
	<ul id = "mypage_mem_info_list">
		<li>
			<img class = "profile_img" src = "../image_bundle/mypage_icon.png">
		</li>
		<li>
			<span>앵밀</span>
			<p><small>test@test.com</small></p>
			<p><small>커플번호:123124124124</small></p>
		</li>
		<li>
			<a>
				<img src = "../image_bundle/right-arrow.png" width = "35" height = "35" style = "opacity: 0.5;">
			</a>	
		</li>
	</ul>
</div>
<div id = "mypage_couple_info_wrap">
	<div id = "mypage_couple_info">
		<ul id = "mypage_couple_img">
			<li>
				<img class = "profile_img" src = "../image_bundle/mypage_icon.png">
			</li>
			<li>
				<img src = "../image_bundle/heart.png" width = "35" height = "35" style = "margin : 0 10px 0 10px">
			</li>
			<li>
				<img class = "profile_img" src = "../image_bundle/mypage_icon.png">
			</li>
		</ul>
		<div id = "couple_since">
			<p>오늘로</p>
			<p id = "couple_today"><strong>000일</strong></p>
			<span id= "couple_since"><small>since</small> 0000.00.00</span>
		</div>
		<div id = couple_anniversary>
			<ul class = "couple_anniversary_list">
				<li class = "couple_anni_date">
					<span >200일</span>
				</li>
				<li class = "couple_anni_date_remain">
					<span>00일 남음</span><br>
					<span>0000.00.00</span>
				</li>
			</ul>
			<ul class = "couple_anniversary_list">
				<li class = "couple_anni_date">
					<span >365일</span>
				</li>
				<li class = "couple_anni_date_remain">
					<span>00일 남음</span><br>
					<span>0000.00.00</span>
				</li>
			</ul>
			<ul class = "couple_anniversary_list">
				<li class = "couple_anni_date">
					<span >400일</span>
				</li>
				<li class = "couple_anni_date_remain">
					<span>00일 남음</span><br>
					<span>0000.00.00</span>
				</li>
			</ul>
		</div>
	</div>
</div>