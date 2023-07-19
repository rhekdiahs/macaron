<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/makeEmail.js"></script> -->
커플코드 전송페이지

<br>
내 이메일 : <input id="user_email" disabled type="text" value="${member.mem_email}">
상대방 이메일 : <input id="partner_email" type="text">
<button id="code_send_btn">인증코드 전송</button>

<script type="text/javascript">
	
	const regex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	let code;
	
	$("#code_send_btn").click(function(){
		let partner_email = $("#partner_email").val();
		let user_email = $("#user_email").val();
		
		if(partner_email !='' && regex.test(partner_email)){
	 		$.ajax({
				url:'checkEmail.do',
				type:'post',
				data:{mem_email:user_email, partner_email:partner_email},
				dataType:'json',
				async: false,
				success:function(param){
					if(param.message == 'not exist'){
						alert('가입되지 않은 유저');
					}else if(param.message == 'same user'){
						alert('본인의 이메일은 안됨');
					}else if(param.message == 'confirmed'){
						alert('이미 커플 인증을 완료한 계정');
					}else if(param.message == 'not confirmed'){
						alert('인증코드 전송 완료');
						code = param.code;
						location.href='/couple/check.do?code='+code;
					}
				},
				error:function(){
					alert('네트워크 오류');
				}
			});	//end of ajax 	
		}
		else{
			alert('입력하지 않았거나 올바른 형식이 아닙니다.');
		}
	});
	
</script>
