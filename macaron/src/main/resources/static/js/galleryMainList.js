$(function(){
	var list_ul = document.getElementById('gallery_list_ul');
	var page = 5;
	var start = 6;
	var end = 11;
	const observer = new IntersectionObserver(function(items){
		items.forEach(function(item){
			if(item.isIntersecting){
				$.ajax({
					url : "../gallery/moreList.do?start=" + start + "&end=" + end,
					type : "get",
					success : function(param){
						updateMainList(param.list, param.imgList);
						list_ul = document.getElementById('gallery_list_ul');
						observer.observe(list_ul.lastElementChild);
						start += page;
						end += page;
					}
				});
			}
		});
	});
	
	observer.observe(list_ul.lastElementChild);
});

function updateMainList(list, img){
	const list_ul = document.getElementById('gallery_list_ul');

	list.forEach(function(item, index){
		let newLi = document.createElement('li');
		
		let anchor = document.createElement('a');
    	anchor.href = "../gallery/detail.do?g_num="+item.g_num;
		newLi.appendChild(anchor);
		
		let galleryItemWrap = document.createElement('div');
		galleryItemWrap.className = "gallery-item-wrap";
		anchor.appendChild(galleryItemWrap);
		
		let galleryItemImage = document.createElement('div');
		galleryItemImage.className = "gallery-item-image";
		galleryItemWrap.appendChild(galleryItemImage);
		
		let thumb = document.createElement('img');
		thumb.src = img[index];
		galleryItemImage.appendChild(thumb);
		
		let galleryItemText = document.createElement('div');
		galleryItemText.className = "gallery-item-text";
		galleryItemWrap.appendChild(galleryItemText);
		
		let date = document.createElement('p');
		date.className = 'gallery-item-text-date font-White';
		date.textContent = formatDate(item.g_date);
		galleryItemText.appendChild(date);
		
		const title = document.createElement('p');
		title.className = 'gallery-item-text-title font-White';
		title.textContent = item.g_title;
		galleryItemText.appendChild(title);
		
		const location = document.createElement('p');
		location.className = 'gallery-item-text-loc font-White';
		location.textContent = item.g_place;
		galleryItemText.appendChild(location);
		
		list_ul.appendChild(newLi);
	});
}
function formatDate(dateString) {
	const date = new Date(dateString);
	return `${date.getFullYear()}.${date.getMonth() + 1}.${date.getDate()}`;
}