$(function(){
	
	//=================이메일 합치기==================
	$("#email_direct").hide();
	
	//이메일 앞부분
	$("#user_email").change(function(){
		email();
	});

	$("#email_address").change(function() {
		if($("#email_address").val() == "direct"){
			$("#user_email").val('');
			$("#user_email").hide();
			$("#middle").hide();
			$("#email_direct").show();
			$(document).on('keyup','#email_direct',function(){
				email();
			});
		}else{
			$("#email_direct").hide();
			$("#user_email").show();
			$("#middle").show();
			email();
		}
	});
	
	
	//합치기
	function email(){
		let email = $("#user_email").val();
		let middle = $("#middle").text();
		var address;
		if($("#email_address").val()=="direct"){
			address = $("#email_direct").val();
		}else{
			address = $("#email_address").val();
		}
		
		if(email!='' && address!=''){
			$("#mem_email").val(email+middle+address);
			console.log('email = ' + email+middle+address);
		}else if(email==''&& address!='') {
			$("mem_email").val(address);
			console.log('adrress= ' + address);
		}
	};
	
	
	//============이메일 인증코드==============
	$('#mail-Send-Btn').click(function() {
		if($('#user_email').val()=='' && $("#email_address").val()!="direct"){
			$('#user_email').val('').focus();
			$('#mail-check-warn').html('이메일을 입력해주세요');
			$('#mail-check-warn').css('color','#E65962');
			return;
		}else{
			$('#mail-check-warn').html('');
			
		if($("#email_address").val()=="direct"){
			if($('#email_direct').val()==''){
				$("#email_direct").show();
				$('#email_direct').val('').focus();
				$('#mail-check-warn').html('이메일을 입력해주세요');
				$('#mail-check-warn').css('color','#E65962');
				return;
			}
		}
			
			const email = $('#mem_email').val();
			console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
			const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
			
			$.ajax({
				type : 'get',
				url : '../mailCheck?email='+email, // GET방식이라 Url 뒤에 email 붙임
				success : function (data) {
					checkInput.attr('disabled',false);
					code =data;
					alert('인증번호가 전송되었습니다.');
					console.log(code);
				}			
			});
		}
				
	}); // end send eamil

	// 인증번호 비교 
	$('.mail-check-input').keyup(function () {
		const inputCode = $(this).val();
		const $resultMsg = $('#mail-check-warn');
		
		if(inputCode === code){
			$resultMsg.html('인증번호가 일치합니다');
			$resultMsg.css('color','green');
		}else{
			$resultMsg.html('인증번호가 불일치 합니다');
			$resultMsg.css('color','#E65962');
			
		}
	});


	$('#mem_pw').keyup(function() {
		let right_pw = /^[A-Za-z0-9]{8,16}$/;
		if(!right_pw.test(mem_pw.value)){
     	 	$('#message_pw').text("8~16자의 영문 대소문자, 숫자로 입력하세요.");
			$('#message_pw').css('color','#E65962');
			return;
		}else{
			$('#message_pw').text('');
		}
	
	});

	//유효성 체크
	$('#register_form').submit(function(){
		
		if($("#email_address").val()=="direct"){
			if($('#email_direct').val()==''){
				$("#email_direct").show();
				$('#email_direct').val('').focus();
				return false;
			}
		}
		
		if($('.mail-check-input').val()==''){
			$('.mail-check-input').val('');
			$('#mail-check-warn').html('인증번호를 입력해주세요');
			$('#mail-check-warn').css('color','#E65962');
			return false;
		}
		
		if($('.mail-check-input').val()!==code){
			const $resultMsg = $('#mail-check-warn');
			$resultMsg.html('인증번호가 불일치 합니다');
			$resultMsg.css('color','#E65962');
			return false;
		}
		
	});
	
		
});

	

