$(function(){
	$("input[name=re_content]").on('keyup', function(){
		if(this.value != ''){
			$('#replySubmitBtn').attr("disabled", false);
		}else{
			$('#replySubmitBtn').attr("disabled", true);
		}
	});
	
	//댓글 등록
	$('#gallery_replyForm').on('submit', function(e){
		e.preventDefault();
		
		let form_data = $(this).serialize();
		$.ajax({
			url : "/gallery/insertReply.do",
			type : "post",
			data : form_data,
			dataType : 'json',
			success : function(param){
				if(param.result == "logout"){
					alert('로그인 후 작성해주세요.');
					location.href = "../memeber/login.do";
				}else if(param.result == "success"){
					updateReplyView(param.reply);
				}
			},
			error : function(){
				console.log("댓글 등록 오류");
			}
		});
	});
});

function updateReplyView(param){
	const reply_list = document.getElementById('reply_list');
	
	let newReplyLi = document.createElement('li');
	
	let profileImg = document.createElement('img');
	profileImg.src = "../image_bundle/mypage_icon.png";
	
	let replyDiv = document.createElement('div');
	replyDiv.className = "reply";
	
	let userNameSpan = document.createElement('span');
	let nameStrong = document.createElement('strong');
	nameStrong.textContent = param.mem_nick;
	userNameSpan.appendChild(nameStrong);
	userNameSpan.appendChild(document.createTextNode(" | "));
	
	let dateSpan = document.createElement('span');
	dateSpan.textContent = param.re_date;
	
	let editAnchor = document.createElement('a');
	let editSpan = document.createElement('p');
	let editSmall = document.createElement('small');
	editSmall.textContent = "수정";
	editSpan.appendChild(editSmall);
	editAnchor.appendChild(editSpan);
	
	let contDiv = document.createElement('div');
	let replyContP = document.createElement('p');
	replyContP.textContent = param.re_content;
	contDiv.appendChild(replyContP);
	
	replyDiv.appendChild(userNameSpan);
	replyDiv.appendChild(dateSpan);
	replyDiv.appendChild(editAnchor);
	replyDiv.appendChild(contDiv);

	const hr = document.createElement("hr");
	hr.style.border = "1px solid whitesmoke";
	
	newReplyLi.appendChild(profileImg);
	newReplyLi.appendChild(replyDiv);
	newReplyLi.appendChild(hr);
	
	reply_list.insertBefore(newReplyLi, reply_list.firstChild);
}