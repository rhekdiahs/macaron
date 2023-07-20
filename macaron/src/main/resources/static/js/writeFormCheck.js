$(function(){
	
	$('input[name=g_title]').change(function(){
		if($(this).val() != ''){
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
	$('#galleryRegister').submit(function(){
		$("input[name=upload]").attr("disabled", true);
	});
});