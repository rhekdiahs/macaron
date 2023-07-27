<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>    

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryDetailCarousel.js"></script> --%>

<div id ="mypage_mem_info">
	<ul id = "mypage_mem_info_list">
		<li>
			<img class = "profile_img" src = "../mypage/viewProfile.do?mem_num=${member.mem_num}">
		</li>
		<li>
			<span>${member.mem_nick }</span>
			<p><small>${member.mem_email }</small></p>
			<p><small>커플번호:${member.mem_cookie}</small></p>
		</li>
		<li>
			<a href = "${pageContext.request.contextPath}/mypage/personal.do">
				<img src = "../image_bundle/right-arrow.png" width = "35" height = "35" style = "opacity: 0.5;">
			</a>	
		</li>
	</ul>
</div>
<div id = "mypage_couple_info_wrap">
	<div id = "mypage_couple_info">
		<ul id = "mypage_couple_img">
			<li>
				<img class = "profile_img" src = "../mypage/viewProfile.do?mem_num=${member.mem_num}">
			</li>
			<li>
				<img src = "../image_bundle/heart.png" width = "35" height = "35" style = "margin : 0 10px 0 10px">
			</li>
			<li>
				<img class = "profile_img" src = "../mypage/viewProfile.do?mem_num=${partner}">
			</li>
		</ul>
		<div id = "couple_since">
			<p>오늘로</p>
			<p id = "couple_today"><strong>${since}일</strong></p>
			<span id= "couple_since"><small>since</small>&nbsp;<fmt:formatDate value="${couple.cp_date}" pattern = "yyyy.MM.dd"/></span>
		</div>
		<div id = couple_anniversary>
			<c:forEach var="list" items="${daylist}" varStatus="status">
				<ul class="couple_anniversary_list">
					<li class="couple_anni_date">
						<span>
							<c:if test="${list.day % 365 == 0}">
								<fmt:formatNumber value = "${list.day/365}"/>주년
							</c:if>
							<c:if test = "${list.day % 365 != 0}">
								${list.day}일
							</c:if>
						</span>
					</li>
					<li class="couple_anni_date_remain"><span>${list.remain}일 남음</span><br>
						<span>${list.date }</span></li>
				</ul>
			</c:forEach>

<!-- 			<ul class = "couple_anniversary_list">
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
			</ul> -->
		</div>
	</div>
</div>