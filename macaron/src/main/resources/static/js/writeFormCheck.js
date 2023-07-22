let titleSubmitOn = false;
$(function(){
	$('input[name=g_title]').change(function(){
		if($(this).val() != ''){
			titleSubmitOn = true;
		}else{
			titleSubmitOn = false;
		}
		if(titleSubmitOn && imgSubmitOn){
			$('#gallery_submit').css('background-color', 'coral');
			$('#gallery_submit').attr("disabled", false);			
		}else{
			$('#gallery_submit').css('background-color', '#ccc');
			$('#gallery_submit').attr("disabled", true);			
		}
	})
	$('input[type="text"]').keydown(function(event) {
	    if (event.keyCode === 13) {
	        event.preventDefault();
			if($(this)[0] == $('input[type="text"]')[0]){
				$('input[type="text"]')[1].focus();
			}else if($(this)[0] == $('input[type="text"]')[1]){
				$('input[type="text"]')[2].focus();
			}else if($(this)[0] == $('input[type="text"]')[2]){
				searchPlaces();
			}
	    }
	});
	$('#gallery_submit').click(function(){
			$("input[name=upload]").attr("disabled", true);
			$.ajax({
				url : '../gallery/insertGallery.do',
				type : 'post',
				dataType : 'json',
				success : function(param){
					if(param.result == 'successRow'){
						var g_num = param.g_num;
						sendImgArray(fileArray, g_num);
					}else if(param.result == 'failRow'){
						console.log('행추가 안됨')
					}
				},
				
			});					
	});
});
function sendImgArray(fileArray, g_num){
	if (fileArray.length == 0) {
		$('#g_num').val(g_num);
		$('#gallery_register').submit();
		return;
	} 
	var image_form_data = new FormData();
	image_form_data.append('upload', fileArray[0]);
	image_form_data.append('g_num', g_num);
	$.ajax({
		url: '../gallery/insertGalleryImage.do',
		data: image_form_data,
		type: 'post',
		dataType: 'json',
		contentType: false,
		processData: false,
		async: false,
		success: function(param) {
			if (param.result == 'success') {
				fileArray.splice(0, 1);
				sendImgArray(fileArray, g_num);
			} else {
				console.log('이미지 안넣어짐')
			}
		}
	});		
}