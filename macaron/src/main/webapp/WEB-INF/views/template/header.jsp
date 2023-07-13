<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<c:set var = "curAddress" value = "${requestScope['javax.servlet.forward.request_uri']}"/>

<ul id = "top_menu">
	<li>
		<a class= "menu-icon">
			<img id = "menu_alarmBtn" src = "../image_bundle/macarons.png">
		</a>
	</li>
	<li>
	<!-- 나중에 캘린더 or 갤러리에서 글쓰기 버튼으로 만들 것(현재 페이지 주소에 따라) -->
	<c:if test = "${fn:contains(curAddress, 'gallery')}">
		<a class = "menu-icon" href = "${pageContext.request.contextPath}/gallery/write.do" data-transition="slide">
			<img id = "menu_insertBtn" src = "../image_bundle/free-icon-plus.png">
		</a>
	</c:if>		
	</li>
</ul>