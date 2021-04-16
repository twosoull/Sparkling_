<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/style_1.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/store.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/header.css">
<script src="https://kit.fontawesome.com/5a9452f10d.js"
	crossorigin="anonymous"></script>
</head>

<body>
	<!-- Header -->

	<div class="wrap">
		<div class="container">
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
			<!--//header//-->
			<c:if test="${authUser == null}">
				<div class="banner">
					<!-- 어딘가 있어서 자동적용되는 css..헤더아님 -->
					<div id="main_1">회원가입으로</div>
					<div id="main_2">스파클링을 시작해보세요.</div>
					<div id="main_3">
						<a id="custom-login-btn"
							href="https://kauth.kakao.com/oauth/authorize?client_id=e90198f391392e46dc3459bec3b08f4b&redirect_uri=http://localhost:8088/Sparkling_/user/join&response_type=code">
							<img
							src="${pageContext.request.contextPath }/assets/image/kakao_login_medium.png">
						</a> <a id="custom-login-btn"
							href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=9ElAXUf0q0NhBnY7bqKl&state=state_String&redirect_uri=http://localhost:8088/Sparkling_/user/joinnaver">
							<img
							src="${pageContext.request.contextPath }/assets/image/네이버 아이디로 로그인_축약형_Green.PNG"
							width="113">
						</a>
					</div>
				</div>
			</c:if>
				<c:if test="${authUser != null}">
				<div class="banner">
					<!-- 어딘가 있어서 자동적용되는 css..헤더아님 -->
					<div id="main_1">${authUser.nickname}님 안녕하세요!</div>
					<div id="main_2">스파클링에서 스파링을 시작해보세요.</div>
					<div id="main_2"><strong>내 매치</strong> 바로만들기</div>
				</div>
			</c:if>
			<div class="space"></div>
		</div>
	</div>
	<div class="wrap">
		<div class="container">
			<!-- Mach Card -->
			<section class="mach-card">
				<!-- card 1ex -->
				<c:forEach items="${map.bbMap.bBuyList}" var="vo">
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
				
				<!-- card end -->
			</section>
			<!-- Mach Card end -->
		</div>
		<!--container-->
	</div>
	<!--wrap-->
	<div class="space"></div>

	<div class="wrap">
		<div class="container">
			<div class="wrap2">
		<div class="container2">
	    <div id="goodsList">
	      <ul>
	        <!--첫번째 줄-->
	        <c:forEach items="${map.storeList}" var="stoVo">
		        <li>
		          <input type="hidden" name="prod_no" value="${stoVo.prod_no}">
		          <input type="hidden" name="sell_no" value="${stoVo.sell_no}">
		          <input type="hidden" name="prod_cate" value="${stoVo.prod_cate}">
		          <input type="hidden" name="event_cate" value="${stoVo.event_cate}">
		          <div class="item">
		              <a class="img" href="${pageContext.request.contextPath}/store/read?prodNo=${stoVo.prod_no}">
		                <img src="${pageContext.request.contextPath}/upload/${stoVo.prod_img_savename}"  width="308" height="396">
		              </a> 
		
		              <div>
		                <a class="info" href="${pageContext.request.contextPath}/store/read?${stoVo.prod_no}">
		                  <span class="name">[${stoVo.prod_brand}]${stoVo.prod_name}</span>
		                  <span class="price"><fmt:formatNumber value="${stoVo.prod_price}" pattern="#,###"/>원</span>
		                  <span class="desc"></span>
		                </a>
		             </div>
		          </div>      
		        </li> 
	        </c:forEach>
	        
	      </ul>
	    </div>
	  </div>
	  <!--container-->
	</div>
	<!--wrap-->
		</div>
		<!--container-->
	</div>
	<!--wrap-->
	<div class="wrap">
		<div class="container">
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			<!--//footer//-->

		</div>
		<!--container-->
	</div>
	<!--wrap-->

</body>
</html>