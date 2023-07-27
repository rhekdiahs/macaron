$(function(){
	const nick_name = $('#input_mem_nick').val();
	const phone = $('#input_mem_phone').val();
	
	$('#personal_profile_img, #personal_profile_setting').on('click', function(){
		$('#profile_modal').addClass('open');
	});
	$('#close_Btn').on('click',function(){
		$('#profile_modal').removeClass('open');
	});
	
	let photo_path = $('.profile-photo').attr('src');
	let uploadPhoto;
	
	$('#upload').on('change', function(){
		uploadPhoto = this.files[0];
		
		if (!uploadPhoto) {
			$('.profile-photo').attr('src', photo_path);
			return
		}
		if (uploadPhoto.size > 1024 * 1024) { //1024byte => 1KB 1024*1024 => 1MB
			alert(Math.round(uploadPhoto.size / 1024 / 1024)
				+ 'MB(1MB까지만 업로드 가능)');
			$('.profile-photo').attr('src', photo_path);
			$(this).val('');
			return;
		}
		let reader = new FileReader();
		reader.readAsDataURL(uploadPhoto);

		reader.onload = function() {
			$('.profile-photo').attr('src', reader.result);
		};
	});
	
	$('#profile_change_Btn').on('click', function(){
		if ($('#upload').val() == '') {
			alert('프로필 이미지를 선택해주세요.');
			$('.profile-photo').attr('src', photo_path);
			return;
		}
		let form_data = new FormData();
		form_data.append('upload', uploadPhoto);
		$.ajax({
			url: '../mypage/updateProfileImg.do',
			data: form_data,
			type: 'post',
			dataType: 'json',
			contentType: false, 
			processData: false, 
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인 후 이용가능합니다.');
				} else if (param.result == 'success') {
					alert('프로필 이미지 변경 완료');
					$('#upload').val('');
					$('#profile_modal').removeClass('open');
				}
			}
		});
	});
	
	
	$('#menu_insertBtn').on('click', function(){
		let new_nick_name = $('#input_mem_nick').val();
		let new_phone = $('#input_mem_phone').val();
		
		if(nick_name == new_nick_name && phone == new_phone) return;
		
		$.ajax({
			url : '../mypage/updateMember.do',
			data : {mem_nick : new_nick_name, mem_phone : new_phone},
			type : 'post',
			dataType : 'json',
			success : function(param){
				if(param.result == "success"){
					alert('회원정보가 수정되었습니다.');
					$('#input_mem_nick').val(new_nick_name);
					$('#input_mem_phone').val(new_phone);
				}else{
					alert('정보 수정 오류');
				}
			}
		});
	});
	
	$('#input_mem_phone').on('keyup', function(){
		console.log($(this).val())
		$(this).val($(this).val()
			.replace(/[^0-9]/g, "")
			.replace(/(^01[0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/, "$1-$2-$3"));
	})
});