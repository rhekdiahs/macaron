document.addEventListener('DOMContentLoaded', function() {
	var main_header = document.getElementById("main_header");
	var main_footer = document.getElementById("main_footer");
	
	var gallery_wrap = document.getElementById("for_slider_div");
	var gallery_write_div = document.getElementById("gallery_write_div");
	var gallery_write_div_wrap = document.getElementById("gallery_write_div_wrap");
	
	var writeButton = document.getElementById('menu_icon');
	var backButton = document.getElementById('back_btn');
	var curURL = document.location.href.split('gallery')[0];
	var placeList = document.getElementById("placesList");
	
	let curTop;
	let urlCheck;
	let header_height = main_header.getBoundingClientRect().height;
	
	writeButton.addEventListener('click', function() {
		curTop = window.pageYOffset;

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
		
		if(curTop == '0'){
			gallery_write_div.style.top = curTop + 'px';
		}else{
			gallery_write_div.style.top = (curTop - header_height) + 'px';
		}
		
		gallery_write_div_wrap.style.display = "";
		gallery_write_div.style.transform = "translateX(-100%)";
		
		gallery_wrap.style.height = (screen.height) + 'px';
		gallery_write_div.style.height = (screen.height) + 'px';
		
		placeList.style.height = (screen.height - placeList.offsetTop - main_footer.clientHeight) + "px";
		
		curImageCount = 0;
		
		history.pushState(null, null, curURL + 'gallery/write.do');
		urlCheck = 'gallery/write.do';		
	}
	
	function write2main(curTop){
		if(urlCheck == 'gallery/write.do'){
			
			if (curTop == '0') {
				gallery_write_div_wrap.style.display = "none";
			}
			gallery_write_div.style.transform = "translateX(100%)";
			gallery_wrap.style.height = "";
			
			$('#main_header').slideDown(200);
			$('#main_footer').slideDown(200);
			
			history.pushState(null, null, curURL + 'gallery/main.do');		
			urlCheck = 'gallery/main.do';
			
			//닫았을 시 있던 값 초기화
			var setNullInput = document.querySelectorAll('input');
			document.getElementById('gallery_content').value = "";
			document.getElementById("forUpload-ul").innerHTML = "";
			curImageCount = 0;
			removeAllChildNods(listEl);
			for(var i = 0; i < setNullInput.length; i++){
				setNullInput[i].value = "";
			}
			
			$('html, body').removeClass('hidden');
		}				
	}
});
