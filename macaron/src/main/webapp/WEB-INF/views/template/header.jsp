<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul id = "top_menu">
	<li>
		<a class= "menu-icon" href = "${pageContext.request.contextPath}/main/main.do">
			<img id = "menu_alarmBtn" src = "../image_bundle/macarons.png">
		</a>
	</li>
	<li>
		<a class = "menu-icon"><!-- 나중에 캘린더 or 갤러리에서 글쓰기 버튼으로 만들 것(현재 페이지 주소에 따라) -->
			<img id = "menu_insertBtn" src = "../image_bundle/free-icon-plus.png">
		</a>
	</li>
</ul>