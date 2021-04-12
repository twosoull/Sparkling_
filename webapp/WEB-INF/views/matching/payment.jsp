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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style_1.css">
<script src="https://kit.fontawesome.com/5a9452f10d.js"
	crossorigin="anonymous"></script>
<title>Document</title>
</head>

<body>
	<!-- Header -->

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
				<form action="${pageContext.request.contextPath}/sparring/pay" method="get" class="sf_form form_20">
				<div class="sm">
					<h1 class="sm_title">주문서</h1>
				</div>
				<!-- 결제하기 -->
				<div class="paykind_title2">장바구니</div>
				<div class="payment_line2"></div>


				<section class="sf_section">
						<div id="cartItemList" class="only_pc">

							<div>
								<div class="cart_item_1">
									<div class="cart_select">
										<div class="inner_select">
											<label class="check"> <input type="checkbox"
												name="" checked> <span class="ico"></span>
												전체선택
											</label> <a href="" class="btn_delete"> 선택삭제 </a>
										</div>
									</div>
									<!-- 아이템 1개 -->
									<div class="box room">
										<ul class="list">
											<li>
												<div class="item_13">
													<label class="check" for=""> <input type="checkbox"
														id="" name="" data-item-id="" data-item-no=""
														data-item-parent-no="" checked> <span class="ico"></span>
													</label>

													<div class="name">
														<div class="inner_name">
															<a
																href="${pageContext.request.contextPath }/sparring/rentdetail?gymNo=${map.bookingVo.gym_no }"
																class="package"> ${map.bookingVo.gym_name } <!-- 대관시에만 사용 -->
																<p>${map.bookingVo.booking_date }
																	${map.bookingVo.booking_start }~${map.bookingVo.booking_end }</p>
																<!-- 대관시에만 사용 -->
															</a>
															<div class="info"></div>
														</div>
													</div>

													<div class="goods">
														<a
															href="${pageContext.request.contextPath }/sparring/rentdetail?gymNo=${map.bookingVo.gym_no }"
															class="thumb"
															style="background-image: url(${pageContext.request.contextPath }/upload/${map.bookingVo.gym_img_savename };">상품이미지

														</a>
														<div class="price">
															<div class="in_price">
																<!-- 대관시 1인 가격 -->
																<span class="selling">1인 <fmt:formatNumber
																		type="number" maxFractionDigits="3"
																		value="${map.bookingVo.booking_price/2}" /> <span
																	class="won">원</span>
																</span>

																<!-- 대관시 총 가격 -->
																<span class="cost"> 총 <fmt:formatNumber
																		type="number" maxFractionDigits="3"
																		value="${map.bookingVo.booking_price}" /> <span
																	class="won">원</span>
																</span>

																<p class="noti"></p>
															</div>
															<!--
															<div class="in_price">
																<span class="selling">25,000
																	<span class="won">원</span>
																</span>

																<span class="cost">
																	25,000
																	<span class="won">원</span>
																</span>

																<p class="noti"></p>
															</div>
														-->

														</div>
													</div>
													<c:if test="${map.bookingVo.booking_no  == null}">
														<button type="button" class="btn_delete" data-item-id=""
															data-item-no="" data-type="">상품삭제</button>
													</c:if>
												</div>
											</li>
										</ul>
									</div>
									<!-- 아이템 1개 END -->



								</div>
							</div>

						</div>


					
					<div class="sf_modal">
						<!-- 대관의 경우 가리기 -->
						<c:if test="${map.bookingVo.booking_no  == null}">
							<div class="sf_address">
								<h3 class="sf_address_item1">
									<i class="modal_icon fas fa-map-marker-alt"></i>배송지
								</h3>
								<div class="sf_address_item2">
									<p class="sf_address_item2_text">${map.userVo.address}</p>
									<a href="" class="sf_address_item2_fix">배송지 변경</a>
								</div>
							</div>
						</c:if>
						<!-- 대관의 경우 가리기 end -->
						<div>
							<div class="sf_box1">
								<div class="sf_price">
									<div class="sf_amount">
										<dt class="sf_tit">상품금액</dt>
										<dd class="price_sf">
											<span class="num"><fmt:formatNumber type="number"
													maxFractionDigits="3"
													value="${map.bookingVo.booking_price/2}" /></span> <span
												class="won">원</span>
										</dd>
									</div>
									<div class="sf_amount">
										<dt class="sf_tit">할인금액</dt>
										<dd class="price_sf">
											<span class="num minus">0</span> <span class="won">원</span>
										</dd>
									</div>

									<div class="sf_amount">
										<dt class="sf_tit">배송비</dt>
										<dd class="price_sf">
											<span class="num">0</span> <span class="won">원</span>
										</dd>
									</div>


								</div>

								<div class="sf_amount_lst">
									<dt class="sf_tit sf_tit_lst">결제금액</dt>
									<dd class="price_sf price_sf_lst">
										<span class="num"><fmt:formatNumber type="number"
												maxFractionDigits="3"
												value="${map.bookingVo.booking_price/2}" /></span> <span
											class="won">원</span>
									</dd>
								</div>
							</div>
						</div>
					</div>
				</section>
				<div class="paykind-section">
					<div class="paykind_title">결제방식</div>
				</div>
				
				<section class="paykind">
						<table class="paykind_table">
							<tr>
								<th>카드결제</th>
								<td class="table_card"><input id="" class="table_pay-radio0"
									type="radio" name="b_buy_paytype" value="card">
									<div class="table_pay-radio_select">
										<select name="" id="">
											<option >카드선택</option>
											<option value="shinhan">신한카드</option>
											<option value="woori">우리카드</option>
											<option value="Corporate">기업카드</option>
										</select>
									</div></td>
							</tr>
							<tr>
								<th>토스결제</th>
								<td><input class="table_pay-radio" type="radio" name="b_buy_paytype"
									value="toss"> <img
									src="${pageContext.request.contextPath }/upd/toss.png" alt="">
								</td>
							</tr>
							<tr>
								<th>카카오페이</th>
								<td><input class="table_pay-radio" type="radio" name="b_buy_paytype"
									value="kakao"> kakao</td>
							</tr>
							<tr>
								<th>휴대폰결제</th>
								<td><input class="table_pay-radio" type="radio" name="b_buy_paytype"
									value="phone"> 휴대폰</td>
	
							</tr>
							<tr></tr>
						</table>
						<div class="btn_payment">
							<button class="" type="submit">결제하기</button>
							<a href="/view/matching/스파링 매칭.html">
								<button class="btn_payment2 ">
									<span class="dea_btn">취소하기</span>
								</button>
							</a>
						</div>
					
						<input type="hidden" name="profile_no" value="${param.profileno}"> 
					
					<input type="hidden" name="user_no" value="${param.userno}"> 
					<input type="hidden" name="b_buy_price" value="${map.bookingVo.booking_price/2}">
					<input type="hidden" name="booking_no" value="${map.bookingVo.booking_no }">
					<input type="hidden" name="subnum" value="${param.subnum }">
					<input type="hidden" name="bbuyno" value="${param.bbuyno }">
					<input type="hidden" name="mainnum" value="${param.mainnum }">
					<input type="hidden" name="partneruserno" value="${param.partneruserno }">
					<input type="hidden" name="mybbuyno" value="${param.mybbuyno}">
				</section>
			</form>
			
		</div>

				
			

		
	</div>
	<!-- payment end -->
	<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	<!--//footer//-->
	</div>
	<!-- container end-->
	</div>
	<!-- wrap end -->
</body>
<script type="text/javascript">


</script>


</html>
