<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/findIdPw.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/findIdPw.js"></script>

<!-- 중앙 컨텐츠 시작 -->
<div class="find_all">
<div class="find_left">
	<a href="${pageContext.request.contextPath}/main/main.do">
		<img width="150" src="${pageContext.request.contextPath}/image_bundle/three-macarons.png" class="find-logo" id="writelogo">
	</a>
</div>
	<form:form action="findId.do" class="find_form" modelAttribute="memberVO" method="post">
		<div class="find-content">
		<span class="find-text">아이디 찾기</span>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<br>
				<label for="mem_nick">이름</label><br><br>
				<form:input path="mem_nick"/>
				<div id="message_name"></div> <!-- js를 위한 태그 -->
				<form:errors path="mem_nick" cssClass="error-color"/>
			</li>
			<li>
				<label for="user_email">본인확인 이메일</label><br><br>
				<input type="text" id="user_email" required>
				<span id="middle">@</span>
				<input type="text" id="email_direct" name="email_direct" placeholder="이메일 입력"/>
				<select id="email_address" name="email_address" title="이메일 선택" class="email_address">
					<option value="naver.com">naver.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="daum.net">daum.net</option>
					<option value="hanmail.net">hanmail.net</option>
					<option value="nate.com">nate.com</option>
					<option value="direct">직접입력</option>
				</select>
				<input type="hidden" id="mem_email" name="mem_email" value="">
			</li>
		</ul>
		</div>
		<div>
			<form:button class="find-submit-btn">아이디 찾기</form:button>
		</div>
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->

