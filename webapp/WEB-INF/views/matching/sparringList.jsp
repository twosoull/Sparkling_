<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/style_1.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script src="https://kit.fontawesome.com/5a9452f10d.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
</head>

<body>
	<!-- Header -->
	<div class="wrap">
		<div class="container">
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
			<!--//header//-->
		</div>
	</div>
	<!--//header-->


	<!-- Sparring -->
	<div class="wrap">
		<div class="container">
			<div class="sm2_1">
				<h1 class="sm_title2">스파링을 시작해보세요!</h1>
			</div>
			<!-- search -->
			<section class="sm_searching">
				<!-- item1 -->
				<div class="sm_searching_item1">

					<div class="sm_searching_item2_list2 ">
						<a class="item2_list_a1" href="${pageContext.request.contextPath }/sparring/match?userno=${authUser.user_no}">
							<div class="list1_btn2">
								<div class="item2_list_a1_div">
									<i class="fas fa-map-marker-alt"></i> 내게 맞는 매치 보기
								</div>
							</div>
						</a>
					</div>
					<!--검색-->
					<form class="sm_searching_item1_form" action="${pageContext.request.contextPath }/sparring/match">

						<a class="item2_list_a3" href="${pageContext.request.contextPath }/sparring/match">
							<div class="list1_btn1">
								<div>전체보기</div>
							</div>
						</a> 
						<input class="item1_address select1" name="date" type="text" placeholder="날짜선택">
						<select class="item1_address" name="time" id="">
							<option class="item1_address_option" value="">시간선택</option>
							<option class="item1_address_option" value="00:00~06:00">00:00~06:00</option>
							<option class="item1_address_option" value="06:00~09:00">06:00~09:00</option>
							<option class="item1_address_option" value="09:00~12:00">09:00~12:00</option>
							<option class="item1_address_option" value="12:00~15:00">12:00~15:00</option>
							<option class="item1_address_option" value="15:00~18:00">15:00~18:00</option>
							<option class="item1_address_option" value="18:00~21:00">18:00~21:00</option>
							<option class="item1_address_option" value="21:00~24:00">21:00~24:00</option>
						</select> 
						<input class="input-text" type="text"
							name="search" placeholder="시군구 또는 체육관 이름..">

						<button type="submit" class="address_btn">검색</button>
					</form>
				</div>
				<!-- 체육관 검색 -->


				<!-- item2 -->
				<div class="sm_searching_item2">
					<div class="sm_searching_item2_list list2">

						<div class="list2_btn2">
							<div>내 매치 만들기</div>
						</div>
						<!-- 매치버튼박스 -->
						<div style="display:block;" class="mach-box-jquery">
							<div class="mach_btn_box">
								<div class="mach_box1">
									<div class="mach_box_sub">직접 대관하시면 상대매칭이 수월합니다.</div>
									<a href="${pageContext.request.contextPath }/sparring/rent">
										<button class="mach_box_sub_btn">대관후 매치만들기</button>
									</a>
								</div>
								<div class="mach_box2">
									<div class="mach_box_sub">프로필을 공개하고 상대를 기다려보세요.</div>
									<a href="${pageContext.request.contextPath }/sparring/writeForm?user_no=${authUser.user_no}">
										<button class="mach_box_sub_btn">대관없이 매치만들기</button>
									</a>
								</div>
							</div>
						</div>
						<!-- 매치버튼박스 end -->
					</div>
				</div>

			</section>

			<!-- Mach Card -->
			<section class="mach-card">
				<!-- card 1ex -->
				<c:forEach items="${map.bBuyList}" var="vo">
				<a href="${pageContext.request.contextPath }/sparring/matchdetail?bbuyno=${vo.b_buy_no}&userno=${vo.user_no}&sessionuser=${authUser.user_no}&bookingno=${vo.booking_no}">
				<!-- 
				 <a href="${pageContext.request.contextPath }/sparring/matchdetail?bbuyno=${vo.b_buy_no}&userno=${vo.user_no}">
				 -->
				<div class="card_container">
					<div class="card">
					<c:choose>
						<c:when test="${vo.gym_img_savename == null}">
							<img
								class="card_img" src="${pageContext.request.contextPath}/upload/0.png" alt="">
						</c:when>
						<c:otherwise>
							<img
								class="card_img" src="${pageContext.request.contextPath}/upload/${vo.gym_img_savename}" alt="">
						</c:otherwise>
					</c:choose>
							<div class="card_profile">
								<div class="card_address">
									<i class="fas fa-map-marker-alt address_icon"></i>

									<div class="card_address_gimname">
									<c:choose>
						<c:when test="${vo.gym_name == null}">
										<p>${vo.b_buy_address} 장소미정</p>
										<div class="user_text">${vo.b_buy_day} | ${vo.b_buy_time}</div>
							
						</c:when>
						<c:otherwise>
							<p>${vo.addressHalf} ${vo.gym_name}</p>
							<div class="user_text">${vo.booking_date} | ${vo.booking_start }~${vo.booking_end }</div>
						</c:otherwise>
					</c:choose>

										
									</div>
									<div class="card_address_kind">
									<c:choose>
										<c:when test="${vo.gym_event==null}">
											<p>${vo.b_buy_event}</p>
										</c:when>
										<c:otherwise>
											<p>${vo.gym_event }</p>
										</c:otherwise>
									</c:choose>
									</div>
								</div>
								<div class="card_user">
									<div class="card_user_profil">
										<div class="user_name">
											<i class="fas fa-user"></i>${vo.nickname}
										</div>
										<div class="user_pro">
											<i class="fas fa-star"></i> <span>${vo.career}</span>
										</div>
										<div class="user_skill">
											주특기 | 
											<span>
											<c:forEach items="${vo.eventList }" var="eventvo">
												<c:choose>
													<c:when test="${eventvo.eventName == 1}">
														복싱
													</c:when>
													<c:when test="${eventvo.eventName == 2}">
														킥복싱
													</c:when>
													<c:when test="${eventvo.eventName == 3}">
														종합격투기
													</c:when>
													<c:when test="${eventvo.eventName == 4}">
														주짓수
													</c:when>
												</c:choose>
											</c:forEach>
											
											</span>
										</div>
									</div>
									<div class="card_user_img"></div>
								</div>
								<!--  
								<div class="card_reser">예약중</div>
								-->
								<div class="card_price">
									<c:if test="${vo.b_buy_price !=null}">
									<div class="card_price_1person">
										<fmt:formatNumber type="number"
												maxFractionDigits="3"
												value="${vo.b_buy_price}" />원</div>
									<div class="card_price_2person">
										<fmt:formatNumber type="number"
												maxFractionDigits="3"
												value="${vo.b_buy_price * 2}" />원/2인</div>
									</c:if>

								</div>
							</div>
						</a>

					</div>
				</div>
				</a>
				</c:forEach>
				<!-- card end -->
				




			</section>
			<!-- Mach Card end -->

			<!-- Paging-->
			<section id="paging">
				<ul>
							<c:if test="${map.pMap.prev == true}">
								<li><a href="${pageContext.request.contextPath}/sparring/match?crtPage=${map.pMap.startPageBtnNo-1}&date=${map.searchMatchVo.date}&time=&search=${map.searchMatchVo.search}">◀</a></li>
							</c:if>
							
							<c:forEach begin="${map.pMap.startPageBtnNo}" end="${map.pMap.endPageBtnNo}" 
							step="1" var="page">
								<li><a href="${pageContext.request.contextPath}/sparring/match?crtPage=${page}&date=${map.searchMatchVo.date}&time=&search=${map.searchMatchVo.search}">${page}</a></li>
							</c:forEach>
							
							<c:if test="${map.pMap.next == true }">
								<li><a href="${pageContext.request.contextPath}/sparring/match?crtPage=${map.pMap.endPageBtnNo+1}&date=${map.searchMatchVo.date}&time=&search=${map.searchMatchVo.search}">▶</a></li>
							</c:if>
						</ul>


				<div class="clear"></div>
			</section>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			<!--//footer//-->
		</div>
		<!-- container end -->
	</div>
	<!-- wrap end -->

</body>

<script type="text/javascript">
	$("document").ready(function(){
		$(".mach-box-jquery").hide();
		
	})
	$(".list2_btn2").on("click",function(){
		console.log("클릭");
		
		if($(".mach-box-jquery").css('display') == 'block' ){
			$(".mach-box-jquery").hide();
		}else{
			$(".mach-box-jquery").show();
		}
		
		
		
	});
	//faltpickr
	$(".select1").flatpickr({
		
		
		 enableTime: false,
		 minDate: "today",
		 maxDate: new Date().fp_incr(10),
		 dateFormat: "Y-m-d",
		 
	});
	
	$(".select1").change(function () {
		var date1 = $(".select1").val();
		
		console.log(date1);
	});
	
	
	
	function btnlist(){
	str="";
	str+=""
		
	}
</script>
</html>
