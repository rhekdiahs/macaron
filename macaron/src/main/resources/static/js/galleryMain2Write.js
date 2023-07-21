document.addEventListener('DOMContentLoaded', function() {
		
	var main_header = document.getElementById("main_header");
	var main_footer = document.getElementById("main_footer");
	
	var gallery_write_div = document.getElementById("gallery_write_div");
	var gallery_write_div_wrap = document.getElementById("gallery_write_div_wrap");
	
	var writeButton = document.getElementById('menu_icon');
	var backButton = document.getElementById('back_btn');
	var curURL = document.location.href.split('gallery')[0];
	
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
		disableScroll();

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
		gallery_write_div.style.height = (screen.height) + 'px';
		
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
			
			$('#main_header').slideDown(200);
			$('#main_footer').slideDown(200);
			
			history.pushState(null, null, curURL + 'gallery/main.do');		
			urlCheck = 'gallery/main.do';
			
			var setNullInput = document.querySelectorAll('input');
			document.getElementById('gallery_content').value = "";
			document.getElementById("forUpload-ul").innerHTML = "";
			curImageCount = 0;
			for(var i = 0; i < setNullInput.length; i++){
				setNullInput[i].value = "";
			}
			
			enableScroll();
		}				
	}
	
	function preventDefault(e) {
		e.preventDefault();
	}
	
	var supportsPassive = false;
	
	try {
		window.addEventListener("test", null, Object.defineProperty({}, 'passive', {
			get: function() { supportsPassive = true; }
		}));
	} catch (e) { }
	
	var wheelOpt = supportsPassive ? { passive: false } : false;
	var wheelEvent = 'onwheel' in document.createElement('div') ? 'wheel' : 'mousewheel';

	// call this to Disable
	function disableScroll() {
		window.addEventListener('DOMMouseScroll', preventDefault, false); // older FF
		window.addEventListener(wheelEvent, preventDefault, wheelOpt); // modern desktop
		window.addEventListener('touchmove', preventDefault, wheelOpt); // mobile
	}
	function enableScroll() {
		window.removeEventListener('DOMMouseScroll', preventDefault, false);
		window.removeEventListener(wheelEvent, preventDefault, wheelOpt);
		window.removeEventListener('touchmove', preventDefault, wheelOpt);
	}
});
