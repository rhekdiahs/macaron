$(function(){
	
	//=================이메일 합치기==================
	$("#email_direct").hide();
	
	//이메일 앞부분
	$("#user_email").change(function(){
		email();
	});

	$("#email_address").change(function() {
	if($("#email_address").val() == "direct"){
		$("#email_direct").show();
		$(document).on('keyup','#email_direct',function(){
			email();
		});
	}else{
		$("#email_direct").hide();
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
		}
	};
	//유효성 체크
	$('.find_form').submit(function(){
		
		if($('#mem_name').val()==''){
			$('#mem_name').val('').focus();
			$("#message_name").text("이름을 입력해주세요").css('color','#E65962');
			return false;
		}
		
		if($('#mem_id').val()==''){
			$('#mem_id').val('').focus();
			$("#message_name").text("아이디를 입력해주세요").css('color','#E65962');
			return false;
		}
		
		if($("#email_address").val()=="direct"){
			if($('#email_direct').val()==''){
				$("#email_direct").show();
				$('#email_direct').val('').focus();
				return false;
			}
		}
	});
	
	
});

	

