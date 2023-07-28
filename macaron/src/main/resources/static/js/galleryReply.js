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
					$('input[name=re_content]').val('');
					$('#replySubmitBtn').attr("disabled", true);
				}
			},
			error : function(){
				console.log("댓글 등록 오류");
			}
		});
	});
	var selectOne;
		
	$(document).on('touchstart','.reply', function(event){
		
		var curReply = event.target.closest(".reply");

		if(curReply == null) return;
		
		longTouch(curReply, 500, function(){
			if(selectOne == true) return;
			selectOne = true;
			var main_header = document.getElementById("main_header");
			var top_menu = document.getElementById("top_menu");
			var menu_alarmBtn = document.getElementById("menu_alarmBtn");
			var originBtn = menu_alarmBtn.src;
			
			main_header.style.background = "deepskyblue";
			menu_alarmBtn.src = "../image_bundle/back.png";
			
			var newLi = document.createElement("li");
			top_menu.appendChild(newLi);
			var menu_delBtn = document.querySelector("#top_menu li:last-child")
			var menu_selectText = document.querySelector("#top_menu li:nth-child(2)");
			
			var delAnchor = document.createElement("a");
			delAnchor.className = "menu-icon";
			
			var delImg = document.createElement("img");
			delImg.src = "../image_bundle/trash.png";
			delImg.id = "menu_insertBtn";
			delAnchor.appendChild(delImg);
			
			menu_delBtn.appendChild(delAnchor);
			
			menu_alarmBtn.addEventListener('click', function(){
				if(menu_alarmBtn.src === originBtn) return;
				main_header.style.background = "skyblue";
				
				menu_alarmBtn.src = originBtn;
				
				var menu_delBtn = document.querySelector("#top_menu li:last-child");
				
				menu_delBtn.remove();
								
				selectOne = false;
			});
			menu_delBtn.addEventListener("click", function(){
				$.ajax({
					url : "../gallery/deleteReply.do",
					data : {re_num : curReply.dataset.rnum},
					type : "post",
					dataType : "json",
					success : function(param){
						if(param.result == "logout"){
							alert('로그인 후 이용가능합니다.');
							location.href = "../member/login.do";
						}else if(param.result == "success"){
							curReply.parentNode.remove();
							menu_alarmBtn.click();
						}else if(param.result == "wrongAccess"){
							alert("자신이 작성한 댓글이 아닙니다.");
						}
					}
				});
			});
		});
	});
	window.oncontextmenu = function(event) {
		event.preventDefault();
		event.stopPropagation();
		return false;
	};
});

function updateReplyView(param){
	const reply_list = document.getElementById('reply_list');
	
	let newReplyLi = document.createElement('li');
	
	let profileImg = document.createElement('img');
	profileImg.src = "../mypage/viewProfile.do?mem_num="+param.mem_num;
	
	let replyDiv = document.createElement('div');
	replyDiv.className = "reply";
	replyDiv.dataset.rnum = param.re_num;
	
	let userNameSpan = document.createElement('span');
	let nameStrong = document.createElement('strong');
	nameStrong.textContent = param.mem_nick;
	userNameSpan.appendChild(nameStrong);
	userNameSpan.appendChild(document.createTextNode(" | "));
	
	let dateSpan = document.createElement('span');
	dateSpan.textContent = param.re_date;
	
	let editAnchor = document.createElement('a');
	let editSpan = document.createElement('span');
	let editSmall = document.createElement('small');
	editAnchor.onclick = function(){modifyReply(this);};
	editAnchor.className = "modify-anchor";
	editSmall.textContent = " 수정";
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
	
	//$('.reply').load('../gallery/detail.do?g_num='+param.g_num+'.reply');
}

function longTouch($target, duration, callback){
	$target.ontouchstart = function(){
		var timer = setTimeout(callback, duration);
		
		$target.ontouchend = function(){
			clearTimeout(timer);
		}
	}
}
var replyDiv;
function modifyReply(e){
	
	if(replyDiv != undefined) return;
	replyDiv = e.parentNode;
	var re_num = replyDiv.dataset.rnum;
	if(e.nextSibling.nextSibling != null){
		var curReplyContentP = e.nextSibling.nextSibling.childNodes[1];
		var curReplyContentPtext = curReplyContentP.innerText;
	}else{
		var curReplyContentP = e.nextSibling.childNodes[0];
		var curReplyContentPtext = curReplyContentP.innerText;
	}
	
	var checkMember = false;
	
	$.ajax({
		url: "../gallery/checkModify.do",
		data: {re_num : re_num},
		type : "post",
		dataType : "json",
		async : false,
		success : function(param){
			if(param.result == "check"){
				checkMember = true;
			}else if(param.result == "logout"){
				alert("로그인 후 이용가능합니다.");
				location.href = "../member/login.do"	
			}else if(param.result == "wrongAccess"){
				alert('자신이 작성한 댓글이 아닙니다.');
				replyDiv = undefined;
			}
		}
	});
	
	if(!checkMember) return;
	
	var newInput = document.createElement('input');
	newInput.type = "text";
	newInput.name = "re_content";
	newInput.id = "modify_reply_input";
	newInput.maxLength = 300;
	newInput.value = curReplyContentPtext;
	curReplyContentP.innerText = "";
	
	var newSubmit = document.createElement('input');
	newSubmit.type = "submit";
	newSubmit.value = "게시";
	newSubmit.onclick = function(){
		$.ajax({
			url : "../gallery/modifyReply.do",
			data : {re_num : re_num, re_content : $('#modify_reply_input').val()},
			type : "post",
			dataType : "json",
			success : function(param){
				if(param.result == "logout"){
					alert("로그인 후 이용가능합니다.");
					location.href = "../member/login.do";
				}else if(param.result == "success"){
					newInput.remove();
					newSubmit.remove();
					curReplyContentP.innerText = param.content;
					newCancel.remove();
					replyDiv = undefined;
				}else if(param.result == "wrongAccess"){
					alert("자신이 작성한 댓글이 아닙니다.");
					replyDiv = undefined;
				}
			}
		});
	};
	newSubmit.className = "modify_reply_button";
	
	var newCancel = document.createElement('button');
	newCancel.innerText = "취소";
	newCancel.onclick = function(){
		newInput.remove();
		newSubmit.remove();
		curReplyContentP.innerText = curReplyContentPtext;
		newCancel.remove();
		replyDiv = undefined;
	};
	newCancel.className = "modify_reply_button";
	
	curReplyContentP.appendChild(newInput);
	curReplyContentP.appendChild(newSubmit);
	curReplyContentP.appendChild(newCancel);
	newInput.focus();
}