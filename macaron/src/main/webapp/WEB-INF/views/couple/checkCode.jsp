<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/makeEmail.js"></script> -->
커플코드인증 페이지
${member.mem_cookie}

<!-- 인증코드 입력 모달 -->
<div id="input_code_modal">
	인증코드 <input id="input_code" type="text" maxlength="11">
	<span id="check_code"></span>
</div>

		<button disabled='disabled' id="register_btn" 
				style="width:100%;" onclick="location.href='/couple/register.do';">커플 등록하기</button>

<script type="text/javascript">

$( document ).ready(function() {
	history.replaceState({}, null, location.pathname);
});

const urlParams = new URL(location.href).searchParams;
let code =  urlParams.get('code');

console.log(code);

$("#input_code").keyup(function(){
	if($(this).val() == code){
		alert('인증코드가 일치합니다.');
		document.getElementById('register_btn').disabled = false;
	}else{
		document.getElementById('register_btn').disabled = true;
	}
});
</script>