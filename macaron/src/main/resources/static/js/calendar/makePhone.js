$(function(){
	let phone_head = $('#phone_head').val();
	let phone_middle = $('.mem_phone')[0].value;
	let phone_tail = $('.mem_phone')[1].value;
	
	$(".mem_phone").keyup(function(e){
		if(e.currentTarget.placeholder == '중간번호 입력'){
			phone_middle = $('.mem_phone')[0].value;
			console.log('middle');
		}else if(e.currentTarget.placeholder == '마지막번호 입력'){
			phone_tail = $('.mem_phone')[1].value;
			console.log('tail');			
		}
	});
	
	$('.mem_phone.middle').change(function(){
		if(phone_middle.length < 3 && phone_middle.length > 0){
			alert('앞자리를 3자리 이상 입력해주세요');
			phone_middle = '';
			$('.mem_phone')[0].value='';
		}
	});
	
	$('.mem_phone.tail').change(function(){
		if(phone_tail.length < 4 && phone_tail.length > 0){
			alert('뒷자리는 4자리를 입력해주세요');
			phone_tail = '';
			$('.mem_phone')[1].value='';
		}
	})
	
	$('#phone_head').change(function(e){
		phone_head = $('#phone_head').val();
	});
	
	$('#push_btn').click(function(){
		if(phone_middle == '' || phone_tail == ''){
			alert('전화번호를 올바르게 입력해주세요');
			return false;
		}else{
			$('#mem_phone').val(phone_head + '-' + phone_middle + '-' + phone_tail);
		}
	});
});