<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab928e5929563772b2932e6182f6b7d9&libraries=services"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/galleryWrite.css">

<div style = "height : 40px;">
	<ul id = "gallery_write_header">
		<li>
			<a id = "cancel_write" href = "${pageContext.request.contextPath}/gallery/main.do"><!-- 나중에 뒤로가기로 -->
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