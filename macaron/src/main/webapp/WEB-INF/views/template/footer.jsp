<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul id = "bottom_menu">
	<li>
		<a class= "menu-icon" href = "${pageContext.request.contextPath}/main/main.do">
			<img id = "menu_home" src = "../image_bundle/home-64.png">
		</a>
	</li>
	<li>
		<a class = "menu-icon" href="${pageContext.request.contextPath}/calendar/main.do">
			<img id = "menu_calendar" src = "../image_bundle/calendar.png">
		</a>
	</li>
	<li>
		<a class = "menu-icon" href = "${pageContext.request.contextPath}/gallery/main.do">
			<img id = "menu_gallery" src = "../image_bundle/gallery.png">
		</a>
	</li>
	<li>
		<a class = "menu-icon" href = "${pageContext.request.contextPath}/mypage/main.do">
			<img id = "menu_mypage" src = "../image_bundle/mypage_icon.png">
		</a>
	</li>
</ul>