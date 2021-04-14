<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 메타데이터 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/booking.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/header.css">

<!-- CSS -->
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">


<!-- 자바스크립트 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

<link href='${pageContext.request.contextPath }/assets/fullcalendar/lib/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/assets/fullcalendar/lib/main.js'></script>

<!-- ////////////////////////////////////////// -->

</head>
<body>
	<div class="wrap">
		<div class="container">
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
			<!--//header//-->

		</div>
	</div>
	<!-- Header end -->

	<!-- wrap -->
	<div class="wrap">
		<div class="container">
			<div class="middle">
				<c:import url="/WEB-INF/views/include/sidemenu.jsp"></c:import>
				<!--//side_menu//-->

				<!--////////////////////////////////////////////-->

				<div class="content_seller">
					<div class="content_booking_header">
						<div>대관 등록</div>
					</div>
					<!--//content_delevery_product_header//-->
					<div class="content_product_line"></div>

					<!-- 대관 관리 버튼 -->
					<div class="regi_btn_site">
						<a href="${pageContext.request.contextPath}/mypage/book/bookinfo?no=${gymVo.sell_no }&gymno=${gymVo.gym_no}">
						<button type="button" class="regi_btn">대관 관리</button></a>
					</div>
					
					<!--체육관 불러오기-->
					<div class="booking_gym_info">
						<table class="booking_gym_table">
							<tr>
								<td rowspan="8"><img class="gym_info_img" name="스파링장"
									src="${pageContext.request.contextPath }/assets/image/스파링장.jpeg"></td>
								<td class="gym_info_title gap">체육관 이름</td>
							</tr>

							<tr>
								<td class="gap_info">${gymVo.gym_name}</td>
							</tr>

							<tr>
								<td class="gym_info_title gap">체육관 주소</td>
							</tr>

							<tr>
								<td class="gap_info">${gymVo.gym_address}</td>
							</tr>

							<tr>
								<td class="gym_info_title gap">체육관 운영시간</td>
							</tr>

							<tr>
								<td class="gap_info">${gymVo.gym_time}</td>
							</tr>

<!-- 							<tr>
								<td class="gym_info_title gap">체육관 편의시설</td>
							</tr>

							<tr>
								<td class="gap_info">(불러온 체육관 편의시설)</td>
							</tr> -->

						</table>
					</div>
					<!--//체육관 불러오기-->

					<!-- 달력 라이브러리 -->
					<div id='calendar'></div>


				</div>
			</div>
			<!--//middle-->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			<!--//footer//-->
		</div>
		<!--//container-->
	</div>
	<!--//wrap-->
	
	<!-- ///////////////////////////////////////////////// -->

			       
	<!-- 날짜 등록 모달창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">대관 등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath }/mypage/book/bookadd">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">날짜</label> 
							&nbsp
							<input id="addModalContent" type="text" name="booking_date" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">시간</label>
							&nbsp
							<!-- <input id="addModalContent" type="text" name="booking_start" value="" placeholder="시작시간 ex)11:00"> -->
							<select name="booking_start" id="booking_start" class="form-control bmg-hrs-mins-input"></select>
							&nbsp~&nbsp
							<!-- <input id="addModalContent" type="text" name="booking_end" value="" placeholder="종료시간 ex)13:00"> -->
							<select name="booking_end" id="booking_end" class="form-control bmg-hrs-mins-input"></select>
						</div>
						<div class="form-group">
							<label class="form-text">요금</label>
							&nbsp
							<input id="addModalContent" type="text" name="booking_price" value="" >
						</div>
					</div>
					<input type="hidden" name="gym_no" value="${gymVo.gym_no}">
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
</body>

<script type="text/javascript">

	//대관 시작 시간 bmg_monday_start_hour
	let $selectStar = jQuery("#booking_start");

	for (let hr = 0; hr < 24; hr++) {
	
	  let hrStr = hr.toString().padStart(2, "0") + ":";
	
	  let val = hrStr + "00";
	  $selectStar.append('<option val="' + val + '">' + val + '</option>');
	
	  val = hrStr + "30";
	  $selectStar.append('<option val="' + val + '">' + val + '</option>')

	}
	
	//대관 종료 시간
 	let $selectEnd = jQuery("#booking_end");

	for (let hr = 0; hr < 24; hr++) {
	
	  let hrStr = hr.toString().padStart(2, "0") + ":";
	
	  let val = hrStr + "00";
	  $selectEnd.append('<option val="' + val + '">' + val + '</option>');
	
	  val = hrStr + "30";
	  $selectEnd.append('<option val="' + val + '">' + val + '</option>')

	} 
	
	//등록 제한걸기
	$("#btnUpload").on("click", function(){
		var booking_price = $("[name='booking_price']").val();
		
		if(booking_price == null || booking_price == ""){
			alert("요금을 입력해 주세요");
			return false;
		}
	});

	////////////////대관 리스트 출력///////////////////////

	//전역 변수(json을 받기 위한 데이터)
	var events=[];
	var bookingVo;
	
	//체육관 번호
	var gymno = "${gymVo.gym_no}";
	console.log("체육관번호 "+gymno);
	
	console.log("함수밖 테스트"); //console.log로 배열 출력할 때 "문자열"+배열 이런 식으로 하면 [Object, Object]로 출력됨.
	console.log(bookListF(gymno));
	console.log(events);
	
	
	//대관 리스트
	function bookListF(gymno){
		
		//처음에 함수 안에 변수 선언했지만 안 씀.
		//var bookings = new Array();
		//var item; 
			
		$.ajax({
			url: "${pageContext.request.contextPath}/mypage/book/booklist",
			type: "post",
			//contentType : "application/json", 필요없음
			data : {gymno: gymno},
				
			dataType: "json",
			async: false, //함수 안팎에서 함수값을 출력했을 때 [{}] 이런 형태의 배열이 떠야 되는데 [] 이렇게 뜸. 
						  //--> ajax는 기본상태가 비동기라서 일처리가 순서대로 안 됨. 그래서 값은 받았는데 제대로 표시가 안 되는 상태가 되는 거.
			success: function(bookList) {
				console.log(bookList);
				
				for(var i=0; i<bookList.length; i++){ //시간(title), 날짜(start)
		       
		            //var title = bookList[i].booking_start+"~"+bookList[i].booking_end;
					//var start = bookList[i].booking_date;
		            //console.log(start);
		            //console.log(title);
		            //위처럼 굳이 변수 선언해서 넣을 필요없이 아래처럼 바로 키:값 넣을 수 있음
		            
		            bookingVo = {
		            	title : bookList[i].booking_start+"~"+bookList[i].booking_end,
		            	start : bookList[i].booking_date
		            };
		            
		            //배열 확인
		            console.log(bookingVo);
		            
		            //배열 묶음
		            events[i] = bookingVo;
		            console.log("이벤트");
		            console.log(events);
				}
				
			},
			error : function(XHR, status, error) { 
				console.error(status + " : " + error);
			}

		}); //ajax

		//함수 리턴값 있어야 달력에 이벤트 반영됨
		return events;
		
		//return JSON.stringify(booksList); 따로 문자열로 만들지 않고 그냥 배열 상태로 리턴하면 됨.
		//return bookings;
		
	} 
	
	//bookList
	//console.log("events밖");
	//console.log(events);
	//console.log(events[0]);

		
	////////////////////////////////////////////////

	
	//달력
	document.addEventListener('DOMContentLoaded', function() {
		var events=[];
		
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			headerToolbar : {
				left : 'prevYear,prev,next,nextYear today',
				center : 'title',
				right : 'dayGridMonth,dayGridWeek,dayGridDay'
			},
			//selectable
			initialView : 'dayGridMonth',
			selectable : true,
			//날짜 클릭시 알림창
			dateClick : function(info) {
				//alert('Date: ' + info.dateStr);
				//alert('Resource ID: ' + info.resource.id);
				var bdate = info.dateStr;
				console.log(bdate);
				
				//모달창을 띄우기 전에 데이터가 들어가 있어야 하니까 데이터 넣고 모달 띄워야 날짜가 안 밀림
				$("#addModal").on('show.bs.modal', function(){
					console.log(bdate);
					$("[name='booking_date']").val(bdate);
				});	
				
				$("#addModal").modal();
			},
			//initialDate: '', 시작날짜 미지정시 현재 날짜로
			navLinks : false, // can click day/week names to navigate views
			editable : true,
			dayMaxEvents : true, // allow "more" link when too many events
		
			events : bookListF(gymno),
			/* 이벤트는 아래와 같은 형태로 들어가야 됨.
			events : [ {
				title : 'title1',
				start : '2021-03-01'
			}]
			*/
			eventColor : "#e60012",
			eventClick: function() {
				//$("#delModal").modal();
				alert("테스트");
			}
				
		});//calender

		calendar.render();
		
	});//document

</script>

</html>