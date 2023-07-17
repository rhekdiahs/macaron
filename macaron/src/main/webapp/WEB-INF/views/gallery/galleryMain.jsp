<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab928e5929563772b2932e6182f6b7d9&libraries=services"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/galleryWrite.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/galleryMain.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/galleryMain2Write.js"></script>

<div id = "for_slider_div">
	<div id = "gallery_main_div">
		<ul id = "gallery_list_ul">
			<li>
				<a href = "${pageContext.request.contextPath}/gallery/detail.do">
				<div class = "gallery-item-wrap">
					<div class = "gallery-item-image">
						<img src = "../image_bundle/gal_sample.jpg">
					</div>
					<div class = "gallery-item-text">
						<p class = "gallery-item-text-date font-White">2023.07.10</p>
						<p class = "gallery-item-text-title font-White">야경 본 날 야경 본 날야경 본 날</p>
						<p class = "gallery-item-text-loc font-White">반포 한강공원</p>
					</div>
				</div>
				</a>
			</li>
			<li>
				<div class = "gallery-item-wrap">
					<div class = "gallery-item-image">
						<img src = "../image_bundle/gal_sample.jpg">
					</div>
					<div class = "gallery-item-text">
						<p class = "gallery-item-text-date font-White">2023.07.10</p>
						<p class = "gallery-item-text-title font-White">야경 본 날</p>
						<p class = "gallery-item-text-loc font-White">반포 한강공원</p>
					</div>
				</div>
			</li>
			<li>
				<div class = "gallery-item-wrap">
					<div class = "gallery-item-image">
						<img src = "../image_bundle/gal_sample.jpg">
					</div>
					<div class = "gallery-item-text">
						<p class = "gallery-item-text-date font-White">2023.07.10</p>
						<p class = "gallery-item-text-title font-White">야경 본 날</p>
						<p class = "gallery-item-text-loc font-White">반포 한강공원</p>
					</div>
				</div>
			</li>
			<li>
				<div class = "gallery-item-wrap">
					<div class = "gallery-item-image">
						<img src = "../image_bundle/gal_sample.jpg">
					</div>
					<div class = "gallery-item-text">
						<p class = "gallery-item-text-date font-White">2023.07.10</p>
						<p class = "gallery-item-text-title font-White">야경 본 날</p>
						<p class = "gallery-item-text-loc font-White">반포 한강공원</p>
					</div>
				</div>
			</li>
			<li>
				<div class = "gallery-item-wrap">
					<div class = "gallery-item-image">
						<img src = "../image_bundle/gal_sample.jpg">
					</div>
					<div class = "gallery-item-text">
						<p class = "gallery-item-text-date font-White">2023.07.10</p>
						<p class = "gallery-item-text-title font-White">야경 본 날</p>
						<p class = "gallery-item-text-loc font-White">반포 한강공원</p>
					</div>
				</div>
			</li>
			<li>
				<div class = "gallery-item-wrap">
					<div class = "gallery-item-image">
						<img src = "../image_bundle/gal_sample.jpg">
					</div>
					<div class = "gallery-item-text">
						<p class = "gallery-item-text-date font-White">2023.07.10</p>
						<p class = "gallery-item-text-title font-White">야경 본 날</p>
						<p class = "gallery-item-text-loc font-White">반포 한강공원</p>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<!-- 글쓰기 폼 시작 -->
	<div id = "gallery_write_div">
		<div id = "gallery_write_div_wrap">
			<ul id = "gallery_write_header">
				<li>
					<a id = "cancel_write"><!-- 나중에 뒤로가기로 -->
						<img id = "back_btn" src = "../image_bundle/back.png">
					</a>
				</li>
				<li style = "margin-left: 80px;">
					<span>게시물 등록하기</span>
				</li>
				<li>
					
				</li>
			</ul>
		</div>
		<div id = "cont-wrap">
			<div id = "writeInfo">
				<img class = "profile-img" src = "../image_bundle/mypage_icon.png">
				<select id = "publish">
					<option value = "public">전체공개</option>
					<option value = "couple">커플보기</option>
				</select>
			</div>
			<div>
				<form>
					<textarea id = "gallery_content" placeholder = "오늘의 추억을 기록해보세요"></textarea>
					<hr>
					<ul id = "select-section">
						<li>
							<img class = "option-icon" src = "../image_bundle/price.png">
							<span class = "option-title">제목</span>
						</li>
						<li class = "hide">
							<input type = "text" class = "input-textbox" placeholder = "제목을 입력해주세요.">
						</li>
						<hr>
						<li>
							<img class = "option-icon" src = "../image_bundle/picture.png">
							<span class = "option-title">사진</span>
						</li>
						<li class = "hide">
							<label for ="upload">파일선택</label>
							<input type = "file" name = "upload" id = "upload" style = "display:none;">
						</li>
						<hr>
						<li>
							<img class = "option-icon" src = "../image_bundle/hash-key.png">
							<span class = "option-title">해시태그</span>
						</li>
						<li class = "hide">
							<input type = "text" class = "input-textbox" placeholder = "#해시태그 형태로 입력해주세요.">
						</li>
						<hr>
						<li>
							<img class = "option-icon" src = "../image_bundle/placeholder.png">
							<span class = "option-title">장소</span>
						</li>
						<li class = "hide">
							<input type = "text" id ="keyword" class = "input-textbox" placeholder = "장소를 검색해주세요.">
						</li>
					</ul>
					<ul id = "placesList"></ul>
					<div id = "submit_btn">
						<button id = "gallery_submit" disabled='disabled'>게시</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 글쓰기 폼 끝 -->
</div>
<script>

$('#keyword').change(function(){
	searchPlaces();
});

var ps = new kakao.maps.services.Places(); 

var listEl = document.getElementById('placesList');

function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        removeAllChildNods(listEl);
        return false;
    }

    ps.keywordSearch( keyword, placesSearchCB); 
}

function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        displayPlaces(data);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    	removeAllChildNods(listEl);
        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {
		
        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

function displayPlaces(places) {

    var listEl = document.getElementById('placesList'); 
    var fragment = document.createDocumentFragment(); 
    
    removeAllChildNods(listEl);
    
    for ( var i=0; i<places.length; i++ ) {

        var itemEl = getListItem(i, places[i]);
		
        
        (function(name){
        	itemEl.onclick = function(){
            	var key = document.getElementById('keyword');
            	
            	key.value = name;
            	
            	removeAllChildNods(listEl);       		
        	}
        })(places[i].place_name);
        
       	fragment.appendChild(itemEl);
    }

    listEl.appendChild(fragment);
}

function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <span class = "info-title"><b>' + places.place_name + '</b></span>';

    if (places.road_address_name) {
        itemStr += '    <br><span class = "info-address"><small>' + places.road_address_name + '</small></span>'
    } else {
        itemStr += '    <br><span class = "info-address"><small>' +  places.address_name  + '</small></span>'; 
    }        

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}
</script>
