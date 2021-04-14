<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/booking.css">

<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/flatpickr/flatpickr.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/flatpickr/flatpickr.js"></script>

</head>
<body>
	<div class="wrap">
		<div class="container">
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
			<!--//header//-->
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
						<div>예약 현황</div>
					</div>
					<!--//content_delevery_product_header//-->
					<div class="content_product_line"></div>

					<!-- 탭부분(다수 체육관 소유 시) pill형식으로 표시 -->
					<div class="own_gym_tab">
						<ul>
							<c:forEach var="vo" items="${bookMap.gymList }">
								<a href="${pageContext.request.contextPath}/mypage/book/bookmanage?no=${vo.sell_no}&gymno=${vo.gym_no}">
								<input type="hidden" name="gym_no" value="${vo.gym_no}">
								<li id="tab${vo.gym_no}">${vo.gym_name }</li></a>
							</c:forEach>
						</ul>										
					</div>
					
					<!--체육관 정보(불러옴)-->
					<div class="booking_list_elnt">
						<div class="gym_info">
							<div class="gym_image">
								<img name="스파링장" src="${pageContext.request.contextPath }/assets/image/스파링장.jpeg">
							</div>

							<div class="gym_info_title">
								<div class="gym_info_title_no">체육관 이름</div>
								<br>
								<div class="gym_info_title_no">체육관 주소</div>
							</div>

							<div class="gym_info_detail">
								<div>${bookMap.gymVo.gym_name }</div>
								<br>
								<div>${bookMap.gymVo.gym_address }</div>
							</div>

							<div class="gym_info_btn_site">
								<a href="${pageContext.request.contextPath }/mypage/book/gym?no=${bookMap.gymVo.sell_no }&gymno=${bookMap.gymVo.gym_no}">
								<button class="gym_info_btn" type="button">체육관 정보</button>
								</a>
								<a href="${pageContext.request.contextPath }/mypage/book/bookaddform?gymno=${bookMap.gymVo.gym_no }">
									<button class="gym_info_btn" type="button">대관 등록</button>
								</a>
							</div>
						</div>
						<!--//gym_info-->
					</div>
					<!--//booking_list_elnt (반복영역)-->

					<!--기간 필터-->
					<div class="time_filter input-group date" data-provide="datepicker">
						<!-- <input type="text" placeholder="기간필터"> -->
						<!-- 시작시 기본 날짜 설정은 value를 이용 -->
						<input type="text" name="peri_start" class="selector flatpickr flatpickr-input" placeholder="기간설정">
						&nbsp~&nbsp
						<!-- <input type="text" id="datePicker" class="form-control" value="2021-04-01"> -->
						<input type="text" name="peri_end" class="selector flatpickr flatpickr-input" placeholder="기간설정">
						<button id="test" name="" type="button">조회하기</button>
					</div>


					<!--예약현황 리스트-->
					<div class="booking_manage">
						<table class="booking_manage_list">
							<thead>
								<tr>
									<th>날짜</th>
									<th>시간</th>
									<th>요금</th>
									<th>상태</th>
									<th>예약자명</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<!--반복영역-->
								<c:forEach var="vo" items="${bookMap.gymVo.bookingList }">
									<tr>
										<td>${vo.booking_date }</td>
										<td>${vo.booking_start }~${vo.booking_end }</td>
										<td>${vo.booking_price }</td>
										<td>${vo.booking_state }</td>
										<td>${vo.user_name1}
										<c:if test="${vo.user_name2 ne null }">
										/ ${vo.user_name2}
										</c:if>
											
										</td>
										
										<td>
											<c:if test="${vo.booking_state eq '대기'}">
												<a href="${pageContext.request.contextPath }/mypage/book/bookremove?bookno=${vo.booking_no }&gymno=${bookMap.gymVo.gym_no}">
													<button class="booking_manage_btn" type="button" id="delbtn">삭제</button>
												</a>
											</c:if>
										</td>
									</tr>
								</c:forEach> <!-- //반복 영역 -->

							</tbody>
						</table>
					</div> <!--//booking_manage-->
					
				</div>
				</div>
				<!--//middle-->
				<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
				<!--//footer//-->
			</div>
			<!--//container-->
		</div>
		<!--//wrap-->
</body>

<script type="text/javascript">

	//url에서 파라미터값 가져오는 함수 --> getParameterByName('함수명');
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	//체육관 버튼 색변경 이벤트 유지
	window.onload=function(){
		
		var paramNo = getParameterByName('gymno');
		var gymNo = $("[name='gym_no']").val();
		var gymArr = [];
		
		//각 체육관에서 체육관번호 뽑아서 배열에 넣기 (jstl과 js 혼용) 
		<c:forEach var="vo" items="${bookMap.gymList}">
			gymArr.push("${vo.gym_no}");
		</c:forEach>
		
		console.log("체육관번호 배열 "+gymArr);
		
		//색 유지
 		for(var i=0; i<gymArr.length; i++){
		
			gymNo = gymArr[i];
			console.log("체육관번호 "+gymNo);
			
			if(gymNo == paramNo){
				console.log("색변경");
				$("#tab"+gymNo).css("color", "#ffffff");
				$("#tab"+gymNo).css("background", "#e60012");
			}
		} 
	}
	
	
	////////////////////////////////////////////////////
	
	//기간필터 날짜 선택(플랫피커)
	$(".selector").flatpickr({
		dateFormat: "Y-m-d",
		minDate:"today"
	});
	
 	$("#test").on("click", function(){
		var st = $("[name='peri_start']").val();
		var en = $("[name='peri_end']").val();
		
		console.log(st);
		console.log(en);
		
		alert(st+"~"+en);
 	});
 	
 	//삭제 전 경고
 	$("#delbtn").on("click", function(){
 		alert("삭제하시겠습니까?");
 		return true;
 	});

		 

</script>

</html>