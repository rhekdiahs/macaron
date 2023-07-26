	//input 공백 체크
	let title = true;			
	let date = false;
	
	$(":text").change(function(e){
		if($(this).val() == '' || $(this).val().trim() == ''){
			if(e.currentTarget == $('#cal_title')){
				title = true;
			}
		}else{
			if(e.currentTarget == $('#cal_title')){
				title = false;
			}
		}
		
		if(!title && !date){
			$('#calendar_submit').css('background-color', 'coral');
			$('#calendar_submit').attr("disabled", false);
		}else{
			$('#calendar_submit').css('background-color', '#ccc');
			$('#calendar_submit').attr("disabled", true);
		}
	});
	
	$("#cal_title").keyup(function(){
		if($(this).val() == '' || $(this).val().trim() == ''){
			title = true;
		}else{
			title = false;
		}
		
		if(!title && !date){
			$('#calendar_submit').css('background-color', 'coral');
			$('#calendar_submit').attr("disabled", false);
		}else{
			$('#calendar_submit').css('background-color', '#ccc');
			$('#calendar_submit').attr("disabled", true);
		}
	});
	
	
	//날짜 선택 (시작일 / 종료일 비교)
	$('.date_pick').change(function(e){
		let start = $('.start').val().replace(/ - /gi,'-');
		let end = $('.end').val().replace(/ - /gi,'-');
		
		if(new Date(end) - new Date(start) < 0){
			alert('기간 설정이 올바르지 않습니다.');
			$('.date_pick').datepicker( "setDate", new Date());
			date = false;
		}
		
		if(!title && !date){
			$('#calendar_submit').css('background-color', 'coral');
			$('#calendar_submit').attr("disabled", false);
		}else{
			$('#calendar_submit').css('background-color', '#ccc');
			$('#calendar_submit').attr("disabled", true);
		}
	});