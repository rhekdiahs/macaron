<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendarMain.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/galleryWrite.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.print.min.css" rel="stylesheet" media="print">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src='https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@6.1.8/index.global.min.js'></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/calendarMain2Write.js"></script>

<div id = "for_slider_div">
	<div id="calendar"></div>
	<!-- 글쓰기 폼 -->
	<div id = "calendar_write_div">
		<div id = "calendar_write_div_wrap">
			<ul id = "calendar_write_header">
				<li>
					<a id = "cancel_write"><!-- 나중에 뒤로가기로 -->
						<img id = "back_btn" src = "../image_bundle/back.png">
					</a>
				</li>
				<li style = "margin: 0 auto;">
					<span>일정 등록하기</span>
				</li>
			</ul>
		</div>
		<div id = "cont-wrap">
			<div>
				<form:form action = "write.do" method = "post" modelAttribute = "calendarVO" id = "calendar_register">
					<ul id = "select-section">
						<li class="hide">
							<input type="checkbox" id="cal_category" name="cal_category" value="pink" onclick="checkOne(this)" checked /> 데이트
							<input type="checkbox" id="cal_category" name="cal_category" value="lavendar" onclick="checkOne(this)"/> 기념일
							<input type="checkbox" id="cal_category" name="cal_category" value="gray" onclick="checkOne(this)"/> 기타
						</li>	
						<li>
							<img class = "option-icon" src = "../image_bundle/notebook.png">
							<span class = "option-title">일정</span>
						</li>
						<li class = "hide">
							<form:input type = "text" class = "input-textbox title" path = "cal_title" placeholder = "일정을 입력해주세요"/>
						</li>
						<hr>
						<li>
							<img class = "option-icon" src = "../image_bundle/schedule.png">
							<span class = "option-title">날짜 선택</span>
						</li>
						<li class = "date_picker">
							<form:input type = "text" class="date_pick start" path="date_start" />
						</li>
						<li class="slash" style="padding:0px;"> ~ </li>
						<li class="date_picker">
							<form:input type = "text" class="date_pick end" path="date_end" />
						</li>
						<hr>
						<li>
							<img class = "option-icon" src = "../image_bundle/notes.png">
							<span class = "option-title">메모</span>
						</li>
						<li class = "hide">
							<form:textarea class = "input-textbox memo" path = "cal_memo" placeholder = "메모할 내용을 입력해주세요" cols="40" rows="6"/>
						</li>
					</ul>
					<ul id = "placesList"></ul>
					<div id="submit_btn">
						<form:button id="calendar_submit" disabled='disabled'>일정 추가</form:button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- 글쓰기 폼 끝 -->
</div>
	<script>
		document.addEventListener('DOMContentLoaded',function() {
			$(function() {
				var request = $.ajax({
					url : '/cal/cal.do',
					dataType : 'json',
					type : 'get'
				});

			request.done(function(data) {

				console.log(data);

				var calendarEl = document.getElementById('calendar');

				var calendar = new FullCalendar.Calendar(calendarEl, {

						googleCalendarApiKey : 'AIzaSyBqhv473kTLFqUdxpUh9QJ7v50zK3dVD_k',

						//	plugins: [ googleCalendarPlugin ],
						/*   
						  	plugins: [
							    interactionPlugin,
							    dayGridPlugin
							], 
						 */
						initialView : 'dayGridMonth',

						editable : true,
						dragScroll : true,

						headerToolbar : {
							right : 'next',
							center : 'title', //today
							left : 'prev'
						},

						titleFormat : function(
								date) {
							// YYYY년 MM월
							return date['end'].year
									+ '년 '
									+ (date['date'].month + 1)
									+ '월';
						},

						//일정이 추가될 때마다 추가될 데이터
						events : data,

						eventSources : [
								{
									googleCalendarId : 'wky981216@gmail.com'
								},
								{
									googleCalendarId : 'ko.south_korea.official#holiday@group.v.calendar.google.com',
									color : 'white',
									textColor : 'red'
								} ],

						dateClick : function(
								info) {
							alert('Clicked on: '
									+ info.dateStr);
							console
									.log(info.date);
						}

				});

					calendar.render();

					console.log(calendar);

			});//done

			request.fail(function() {
				alert('FAI:L');
			}); //fail

		});//ajax function

	});//ready function

		/*
		calendar.on('dateClick', function(info) {
			alert('Clicked on: ' + info.dateStr);
			console.log(info.date);
		}); 
		 */
	</script>