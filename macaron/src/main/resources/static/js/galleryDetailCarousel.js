$(function(){
	let index = 0;
	const slideCont = document.getElementById('slide_cont');
	const radioButton = document.getElementsByName('slide-radios');
	const slideCount = $('#slide_cont').children().length;
	let screenX = screen.width;
	let curTouch;
	let moveTouch;
	let distance;
	let moveX;
	let curLeft = 0;
	
	$('#detail_img_slider').on('touchstart', function(e){
		//첫 포인트 찍힌 x좌표
		curTouch = e.originalEvent.touches[0].pageX;
	});
	$('#detail_img_slider').on('touchmove', function(e){
		moveTouch = e.originalEvent.touches[0].pageX;
		//움직인 거리, 왼쪽으로 당기면 + / 오른쪽으로 당기면 - 라서 부호 바꿔줌
		distance = -(curTouch-moveTouch);
		moveX = curLeft + distance;
		slideCont.style.transform = "translateX("+(moveX)+"px)";
	});
	$('#detail_img_slider').on('touchend', function(e){
		slideCont.classList.add('slide-transition');

		if(distance < -screenX/6){
			if(index == slideCount - 1) {
				slideCont.style.transform = "translateX(" + (-screenX * index) + "px)";
				return;
			};
			index += 1;
			radioButton[index].checked = true;
		}else if(distance >= screenX/6 && index != 0){
			index -= 1;
			radioButton[index].checked = true;			
		}
		
		slideCont.style.transform = "translateX(" + (-screenX * index) + "px)";
		
		moveX = 0;
		distance = 0;
	});
	
	slideCont.addEventListener("transitionend", function(){
		curLeft = slideCont.getBoundingClientRect().left;
		slideCont.classList.remove('slide-transition');
	});
});
