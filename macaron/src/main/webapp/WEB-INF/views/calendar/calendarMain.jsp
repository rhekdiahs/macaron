<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
캘린더 메인페이지

<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/index.global.min.js"></script>
 <script src='https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@6.1.8/index.global.min.js'></script>
 
	<div id="calendar"></div>
	    <script>
	
	     document.addEventListener('DOMContentLoaded', function() {
	       var calendarEl = document.getElementById('calendar');
	       
	       var calendar = new FullCalendar.Calendar(calendarEl, {
	       	
	         	
	       		initialView: 'dayGridMonth',
	       });
	       
	       	calendar.render();
	       
	      	console.log(calendar);
	     });
	     
	   </script>