<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypagePersonal.css">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryDetailCarousel.js"></script> --%>

<div id = "personal_profile">
	<img id = "personal_profile_img" src = "../image_bundle/mypage_icon.png">
	<img id = "personal_profile_setting" src = "../image_bundle/settings.png">
</div>
<div id = "personal_info">
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>이름</span>
		</li>
		<li>
			<input type = "text" class = "inputStyle" value = "앵밀">
		</li>
	</ul>
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>커플번호</span>
		</li>
		<li>
			<span>123124124</span>
		</li>
	</ul>
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>이메일</span>
		</li>
		<li>
			<span>test@test.com</span>
		</li>
	</ul>
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>생일</span>
		</li>
		<li>
			<input type = "text" class = "inputStyle" value = "0000.00.00">
		</li>
	</ul>
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>전화번호</span>
		</li>
		<li>
			<input type = "text" class = "inputStyle" value = "010-0000-0000">
		</li>
	</ul>
	<ul class = "personal_info_list pageOut">
		<li class = "personal_info_title">
			<span>로그아웃</span>
		</li>
		<li>
			<a onclick = "checkLogout()">
				<img src = "../image_bundle/right-arrow.png" class = "nextBtn">
			</a> 
		</li>
	</ul>
	<ul class = "personal_info_list pageOut">
		<li class = "personal_info_title">
			<span>회원탈퇴</span>
		</li>
		<li>
			<a>
				<img src = "../image_bundle/right-arrow.png" class = "nextBtn">
			</a>
		</li>
	</ul>					
</div>
<div style = "margin-bottom : 40px;">

</div>
<script>
function checkLogout(){
	var check = confirm("로그아웃 할까요?");
	console.log(check);
	if(check){
		location.href = "${pageContext.request.contextPath}/member/logout.do";
	}else{
		return;
	}
}
</script>