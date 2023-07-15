document.addEventListener('DOMContentLoaded', function() {
	var writeButton = document.getElementById('menu_icon');
	var backButton = document.getElementById('back_btn');
	var curURL = document.location.href.split('gallery')[0];
	var main_header = document.getElementById("main_header");
	var main_footer = document.getElementById("main_footer");
	var gallery_write_div = document.getElementById("gallery_write_div");
	var gallery_write_div_wrap = document.getElementById("gallery_write_div_wrap");
	
	let curTop;
	let urlCheck;
	let header_height = main_header.getBoundingClientRect().height;
	
	writeButton.addEventListener('click', function() {
		curTop = parseInt(window.pageYOffset + main_header.getBoundingClientRect().bottom);
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
		
		main_header.style.display = "none";
		main_footer.style.display = "none";
		gallery_write_div_wrap.style.display = "";
		
		if(curTop == '40'){
			gallery_write_div.style.top = (curTop - header_height) + 'px';
		}else{
			gallery_write_div.style.top = (curTop - header_height*2) + 'px';
		}
		
		gallery_write_div.style.transform = "translateX(-100%)";
		gallery_write_div.style.height = (screen.height) + 'px';
		
		history.pushState(null, null, curURL + 'gallery/write.do');
		urlCheck = 'gallery/write.do';		
	}
	
	function write2main(){
		if(urlCheck == 'gallery/write.do'){
			
			history.go(1);
			
			if (curTop == '40') {
				gallery_write_div_wrap.style.display = "none";
			}
			
			gallery_write_div.style.transform = "translateX(100%)";
			
			main_footer.style.display = ""
			main_header.style.display = "";
			
			history.pushState(null, null, curURL + 'gallery/main.do');		
			urlCheck = 'gallery/main.do';
			
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
