<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 중앙 컨텐츠 시작 -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/confirmId.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/makeEmail.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/makePhone.js"></script>
<script>
$(document).on("keyup", ".mem_phone", function(e) {
	$(this).val($(this).val().replace(/[^0-9]/g, "")); 
});
</script>
<div class="register-main">
	<form:form action="registerUser.do" id="register_form" modelAttribute="memberVO">
		
		<br>
		<div class="register-content">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<br>
				<form:input path="mem_id" autocomplete="off"/>
				<input type="button" id="confirmId" value="아이디 중복체크">
				<div id="message_id"></div> <!-- js를 위한 태그 -->
				<form:errors path="mem_id" cssClass="error-color"/> <!-- 에러문구 -->
			</li>
			<li>
				<label for="mem_pw">비밀번호</label>
				<br>
				<form:password path="mem_pw"/>
				<div id="message_pw">
				<form:errors path="mem_pw" cssClass="error-color"/></div>
			</li>
		
			<li>
				<label for="pw_confirm">비밀번호 확인</label>
				<br>
				<form:password path="pw_confirm"/>
				<span id="confirmMsg"></span>
			</li>
			</ul>
			</div>
			
			<div class="register-content">
			<ul>
			<li>
				<label for="mem_nick">이름</label>
				<br>
				<form:input path="mem_nick"/>
				<form:errors path="mem_nick" cssClass="error-color"/>
			</li>
			<li>
				<label for="user_email">본인확인 이메일</label>
				<br>
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
				<button type="button" class="btn btn-primary" id="mail-Send-Btn">인증번호 전송</button>
			</li>
			
			
			<%--=======================이메일 인증코드 구현 시작========================  --%>
			<li>
				<div class="mail-check-box">
					<input class="form-control mail-check-input" placeholder="인증번호 6자리 입력" disabled="disabled" maxlength="6">
				</div>
				<div id="mail-check-warn"></div>
			</li>
			 <%-- =======================이메일 인증코드 구현 끝========================  --%>
			
			
			
			<li>
				<label for="phone">전화번호(선택)</label>
				<br>
				<select id="phone_head">
					<option value="02">02</option>
					<option value="010">010</option>
				</select>
				<input type="text" class="mem_phone middle" maxlength="4" placeholder="중간번호 입력"/>
				<input class="mem_phone tail" maxlength="4" placeholder="마지막번호 입력"/>
				<input type="hidden" id="mem_phone" name="mem_phone" value="">
				<form:errors path="mem_phone" cssClass="error-color"/>
			</li>
		</ul>
		</div>
		<div class="align-center">
			<form:button id="register-submit-btn">가입하기</form:button>
		</div>
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->
