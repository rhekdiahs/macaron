<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypagePersonal.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypagePersonal.js"></script>

<div id = "personal_profile">
	<img id = "personal_profile_img" class = "profile-photo" src = "../mypage/viewProfile.do?mem_num=${member.mem_num}">
	<img id = "personal_profile_setting" src = "../image_bundle/settings.png">
</div>
<div class = "background"></div>
<div id = "profile_modal">
	<p>프로필 사진 변경</p>
	<img class = "profile-photo" src = "../mypage/viewProfile.do?mem_num=${member.mem_num}">
	<br>
	<label for = "upload">이미지 선택</label>
	<input type = "file" id = "upload" accept = "image/gif, image/png, image/jpeg" style = "display : none;">
	<button id = "profile_change_Btn" class = "modal-btn">변경</button>
	<button id = "close_Btn" class = "modal-btn">닫기</button>
</div>
<div id = "personal_info">
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>이름</span>
		</li>
		<li>
			<input type = "text" id = "input_mem_nick" class = "inputStyle" value = "${member.mem_nick}">
		</li>
	</ul>
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>커플번호</span>
		</li>
		<li>
			<span>${member.mem_cookie}</span>
		</li>
	</ul>
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>이메일</span>
		</li>
		<li>
			<span>${member.mem_email}</span>
		</li>
	</ul>
	<!-- <ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>생일</span>
		</li>
		<li>
			<input type = "text" class = "inputStyle" value = "0000.00.00">
		</li>
	</ul> -->
	<ul class = "personal_info_list">
		<li class = "personal_info_title">
			<span>전화번호</span>
		</li>
		<li>
			<input type = "text" id = "input_mem_phone" class = "inputStyle" value = "${member.mem_phone}">
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