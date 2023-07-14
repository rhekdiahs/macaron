<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<div class="modal" id="loginModal">
			<div class="modal-head">
					<div class="col-7">
							<img src="${pageContext.request.contextPath}/image_bundle/three-macarons.png" height="100" width="100">
					</div>
			</div>
			<div class="modal-body">	
				<c:if test="${empty user}">
				<form action="${pageContext.request.contextPath}/member/login.do" method="post" id="login_form">
						<ul>
							<li><label for="mem_id"></label>
								<input type="text" name="mem_id" id="mem_id" placeholder=" ID"/></li>
								<li><span id="login_Id"></span></li>
							<li><label for="mem_pw"></label>
								<input type="password" name="mem_pw" id="mem_pw" placeholder=" Password"/></li>
								<li><span id="login_Pw"></span></li>
							<li>&nbsp;<input type="checkbox" name="auto" id="auto">&nbsp;로그인 상태 유지
							</li>
						</ul>
					
						<button type="submit" class="login-submit submit" style="background-color:#e65962;">Login
						</button>
				</form>
				</c:if>
				<c:if test="${!empty user}">
						<button class="btn text-white rounded logout-btn" style="background-color:#E65962;"
						onclick="location.href='${pageContext.request.contextPath}/member/logout.do'">
						로그아웃</button>
				</c:if>
				<ul class="find-id">
				<c:if test="${empty user}">
					<li><a href="${pageContext.request.contextPath}/member/findId.do">아이디 찾기</a></li>
					<li><a href="${pageContext.request.contextPath}/member/findPw.do">비밀번호 찾기</a></li>
					<li><a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></li>
				</c:if>
				</ul>
			</div>
</div>