<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/galleryDetail.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryDetailCarousel.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryReply.js"></script>

<div id = "detail_title_wrap">
	<p>${gallery.g_title}</p>
	<a>
		<span>${gallery.g_place }</span>
	</a>
</div>
<div id="detail_img_slider">
	<div id="slide_cont">
	<c:forEach var = "list" items = "${imgList}" varStatus = "status">
		<div class="slide-cont-img" id="slide_cont_img_${status.count}">
			<img src="${list}">
		</div>
	</c:forEach>
	</div>
</div>
<div id = "detail_radio_wrap">
	<c:forEach var = "list" items = "${imgList}" varStatus = "status">
		<c:if test = "${status.index == 0}">
			<input type = "radio" name = "slide-radios" class = "slide-radio" onclick="return(false);" checked>
		</c:if>
		<c:if test = "${status.index != 0}">
			<input type = "radio" name = "slide-radios" class = "slide-radio" onclick="return(false);">
		</c:if>
	</c:forEach>
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
	<c:if test = "${empty replyList }">
	<ul id = "reply_list"></ul>
	</c:if>
	<c:if test = "${!empty replyList}">
		<p id="reply_header">댓글</p>
		<ul id="reply_list">
			<c:forEach var="list" items="${replyList}" varStatus="status">
				<li>
					<img src="../image_bundle/mypage_icon.png">
					<div class="reply" data-rnum = "${list.re_num}">
						<span><strong>${list.mem_nick}</strong> | </span> <span>${list.re_date}</span>
						<a onclick = "modifyReply(this);" class = "modify-anchor"><span><small>수정</small></span></a>
						<div>
							<p>${list.re_content}</p>
						</div>
					</div>
					<hr style="border: 1px solid whitesmoke;"></li>
			</c:forEach>
			<!-- 			<li>
			<img src = "../image_bundle/mypage_icon.png">
			<div class = "reply">
				<span><strong>앵밀</strong> | </span>
				<span>2023.07.17</span>
				<a><span><small>수정</small></span></a>
				<div>
					<p>좋았슴니다.</p>
				</div>
			</div>
			<hr style = "border : 1px solid whitesmoke;">
		</li> -->
		</ul>
	</c:if>
		<div id ="reply_input_wrap">
			<form:form action = "write_reply.do" method = "post" modelAttribute = "galleryReplyVO" id = "gallery_replyForm">
				<input type = "hidden" name = "g_num" value = "${gallery.g_num }">
				<input type = "hidden" name = "mem_num" value = "${user.mem_num}">
				<ul id = "reply_input_ul">
					<li>
						<img src = "../image_bundle/mypage_icon.png">
					</li>
					<li>
						<input type = "text" name = "re_content" placeholder = "댓글 달기..." maxlength = "300">
					</li>
					<li>
						<input type = "submit" id = "replySubmitBtn" value = "게시" disabled ="disabled">
					</li>
				</ul>
			</form:form>
		</div>
	</div>
</div>

<div style = "margin-bottom : 40px;">

</div>