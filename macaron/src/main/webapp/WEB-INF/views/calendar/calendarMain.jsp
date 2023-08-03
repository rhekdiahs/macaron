<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendarMain.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendarWrite.css">
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
							<input type="checkbox" id="cal_category" name="cal_category" value="chartreuse" onclick="checkOne(this)"/> 기념일
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
					<div id="submit_btn">
						<form:button id="calendar_submit" disabled='disabled'>일정 추가</form:button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- 글쓰기 폼 끝 -->
</div>
	<div id="detail_container" class="detail-hide">
		<div id="button_box">
			<a id="detail_del" >
				<img src = "../image_bundle/trash-bin.png">
			</a>
			<a id="detail_setting">
				<img src = "../image_bundle/gear.png">
			</a>
			<a id="detail_close" >
				<img src = "../image_bundle/down.png">
			</a>
		</div>
		<ul id="detail_list">
			<li><strong id="detail_title"></strong></li>
			<li><span id="detail_date" style="color:#939393;"></span></li>
			<li id="detail_memo" style="color:#939393;"><span id="detail_memo"></span></li>
		</ul>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar/writeFormCheck.js"></script>
	<script>
		document.addEventListener('DOMContentLoaded',function() {
			//console.log(document.body.offsetHeight);
			//console.log(document.body.scrollHeight);
			//console.log(document.body.clientHeight);
			//screen.availHeight = 현재 스크린 높이
			let footerHeight = document.getElementById('bottom_menu').offsetHeight;
			let headerHeight = document.getElementById('top_menu').offsetHeight;
			let calHeight = document.getElementById('calendar').offsetHeight;
			let emptyHeight = screen.availHeight - headerHeight - footerHeight - calHeight;
			let detailHeight = document.getElementById('detail_container').offsetHeight;
			
			console.log('공백의 높이 = ' + emptyHeight);
			console.log('footer 바로 위 = ' + (screen.availHeight - footerHeight));
			console.log('상세정보 = ' + detailHeight);
						
			$(function() {
				var request = $.ajax({
					url : '/calendar/cal.do',
					dataType : 'json',
					type : 'get'
				});

			request.done(function(data) {

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

						titleFormat : function(date) {
							// YYYY년 MM월
							return date['end'].year + '년 '+ (date['date'].month + 1)+ '월';
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

						/* dateClick : function(info) {
							alert('Clicked on: ' + info.dateStr);
							console.log(info.date);
						}, */
						
						eventClick : function(info){
							let cal_num = info.event.extendedProps.cal_num;
							
							//일정 누르면 상세 뜸
							$.ajax({
								url:'/calendar/detail.do',
								dataType:'json',
								type:'post',
								data:{cal_num:cal_num},
								success:function(param){
									let date_end = new Date(param.date_end).getTime() - 1000*60*60*24;
									let date_start = param.date_start.replace(/-/gi,'.');
									//let date_end = param.date_end.replace(/-/gi,'.');

									let diffMs = Math.abs(new Date(date_end) - new Date(param.date_start));
									let diffDay = Math.ceil(diffMs/ (1000 * 60 * 60 *24));
									
									$('#detail_title').html(param.cal_title);
									$('#detail_memo').html(param.cal_memo);
									if(diffDay < 1){
										console.log('같음');
										$('#detail_date').html(date_start);
									}else{
										date_end = new Date(date_end).toISOString().split("T")[0].replace(/-/gi,'.');
										$('#detail_date').html(date_start + ' ~ ' + date_end);
									}
									
									$('#detail_container').removeClass('detail-hide');
									
									$('#detail_del').click(function(){
										var del_confirm = confirm('일정을 삭제할까요?');
										
 										if(del_confirm){
 											location.href = '/calendar/delete.do?cal_num='+cal_num;
											/* $.ajax({
												url:'/calendar/delete.do',
												type:'get',
												data:{cal_num:cal_num},
												dataType:'json',
												success:function(param){
													if(param.message == 'delete success'){
														location.href = '/calendar/delete.do';
													}else{
														alert('DELETE ERROR');
													}
												}
											}); */
										} 
										
									});
									
									$('#detail_setting').click(function(){
										var edit_confirm = confirm('일정을 수정할까요?');
										
										if(edit_confirm){
											location.href = '/calendar/edit.do?cal_num='+cal_num;
										}
									});
									
								},
								error:function(param){
									console.log('에러' + param);
								}
							});//eventClick ajax
							
							//location.href = 'detail.do?cal_num='+cal_num;
						}

				});

					calendar.render();
					
			});//done

			request.fail(function() {
				alert('FAI:L');
			}); //fail

		});//ajax function

	});//ready function
	
	//datepicker JS
 	$(function(){
 		$('.date_pick').datepicker({
 			dateFormat: 'yy - mm - dd', //달력 날짜 형태
 	           showOtherMonths: true, //빈 공간에 현재월의 앞뒤월의 날짜를 표시
 	           showMonthAfterYear:true, // 월- 년 순서가아닌 년도 - 월 순서
 	           changeYear: true, //option값 년 선택 가능
 	           changeMonth: true, //option값  월 선택 가능                
 	           showOn: "both", //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
 	           buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif", //버튼 이미지 경로
 	           buttonImageOnly: true, //버튼 이미지만 깔끔하게 보이게함
 	           buttonText: "선택", //버튼 호버 텍스트              
 	           yearSuffix: "년", //달력의 년도 부분 뒤 텍스트
 	           monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 텍스트
 	           monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], //달력의 월 부분 Tooltip
 	           dayNamesMin: ['일','월','화','수','목','금','토'], //달력의 요일 텍스트
 	           dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'], //달력의 요일 Tooltip
 	           minDate: "-5Y", 	//최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
 	           maxDate: "+5y"	//최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
 	       });                    
 	       
 	       //초기값을 오늘 날짜로 설정
 	       $('.start').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
 	       $('.end').datepicker('setDate', 'today'); 
 		});
 	
 	//checkbox 중복 선택 방지
 	function checkOne(input){
 		let check = document.getElementsByName("cal_category");
	 	
 	 	check.forEach(function(chk){
 			chk.checked = false;
 		}); 
 		
 		input.checked = true;
 	}
	
	</script>