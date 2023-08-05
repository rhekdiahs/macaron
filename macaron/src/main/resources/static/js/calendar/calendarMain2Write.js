document.addEventListener('DOMContentLoaded', function() {
	var main_header = document.getElementById("main_header");
	var main_footer = document.getElementById("main_footer");
	
	var calendar_wrap = document.getElementById("for_slider_div");
	var calendar_write_div = document.getElementById("calendar_write_div");
	var calendar_write_div_wrap = document.getElementById("calendar_write_div_wrap");
	
	var writeButton = document.getElementById('menu_icon');
	var backButton = document.getElementById('back_btn');
	var curURL = document.location.href.split('calendar')[0];
	
	let curTop;
	let urlCheck;
	
	writeButton.addEventListener('click', function() {
		$('#detail_container').addClass('detail-hide');
		
		curTop = window.pageYOffset;
		console.log(curTop);

		main2write(curTop);
	});
	backButton.addEventListener('click', function(){
		write2main(curTop);	
	})
	window.onpopstate = () => {
		write2main(curTop);
	};
	
	function main2write(curTop){
		$('html, body').addClass('hidden');

		$('#main_header').slideUp(200, "", function(){
			main_header.style.display = "none";
		});
		$('#main_footer').slideUp(200, "", function(){
			main_footer.style.display = "none";
		});
		
		/*if(curTop == '0'){
			gallery_write_div.style.top = curTop + 'px';
		}else{
			gallery_write_div.style.top = (curTop - header_height) + 'px';
		}*/
		calendar_write_div.style.top = '0px';
		calendar_write_div_wrap.style.display = "";
		calendar_write_div.style.transform = "translateX(-100%)";
		
		calendar_wrap.style.height = (screen.height) + 'px';
		calendar_write_div.style.height = (screen.height) + 'px';
		
		//placeList.style.height = (screen.height - placeList.offsetTop - main_footer.clientHeight) + "px";
		
		//curImageCount = 0;
		
		history.pushState(null, null, curURL + 'calendar/write.do');
		urlCheck = 'calendar/write.do';		
	}
	
	function write2main(curTop){
		if(urlCheck == 'calendar/write.do'){
			
			if (curTop == '0') {
				calendar_write_div_wrap.style.display = "none";
			}
			calendar_write_div.style.transform = "translateX(100%)";
			calendar_wrap.style.height = "";
			calendar_write_div.addEventListener("transitionend", function(){
				window.scrollTo({top:curTop, left:0, behavior:'auto'});
			});
			
			$('#main_header').slideDown(200);
			$('#main_footer').slideDown(200);
			
			history.pushState(null, null, curURL + 'calendar/main.do');		
			urlCheck = 'calendar/main.do';
			
			//닫았을 시 있던 값 초기화
			$(':checkbox').eq(1).prop('checked',false);
			$(':checkbox').eq(2).prop('checked',false);
			$(':checkbox').eq(0).prop('checked',true);
			
			document.getElementById('cal_title').value = "";
			document.getElementById('cal_memo').value = "";
			
			$('.start').datepicker('setDate', 'today');
	 	    $('.end').datepicker('setDate', 'today');
			
	 	    $('#calendar_submit').css('background-color', '#ccc');
			$('#calendar_submit').attr("disabled", true);
	 	    
			$('html, body').removeClass('hidden');
			
		}				
	}
});
