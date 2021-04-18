<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<!-- 지우지말것 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<!-- 지우지말것 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/style_1.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<script src="https://kit.fontawesome.com/5a9452f10d.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
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
		<!-- container -->
		<div class="container">
			<!-- title -->
			<div class="sm_0">
				<div class="sm_title">
					<h1>스파링 신청서</h1>
					<div class="sm_title_back">
						<a href="">스파링매치</a>><a href="">스파링신청서</a>
					</div>
				</div>
			</div>
			<!-- title end -->
			<!-- rule -->
			<div class="rule">
				<h3 class="rule_info">Rule</h3>
				<!-- 룰 정보 여기넣기 -->

				<h1 class="">${empty map.gAVo ? map.bBuyVo.b_buy_event : map.gAVo.gym_event}</h1>
			</div>
			<!-- rule end -->
			<!-- post_host -->
			<section class="post_host">
				<div class="host_items1">

					<div>
						<div class="post_host_item">
							<!-- 글주인 이름 -->
							<h1 class="post_host_item1">
								<span> ${map.bBuyVo.nickname} </span>
							</h1>
							<!-- 글주인이름 end -->
							<div class="post_host_item2">
								<div class="post_host_item2_1">
									<div class="post_host_imgbox">
										<img src="${pageContext.request.contextPath }/upload/${map.bBuyVo.user_photo }" alt="">

									</div>
									<div class="post_host_spa-profile">
										<p class="post_host_spa-profile_coment">"${map.bBuyVo.word }"</p>
										<div class="post_host_spa-profile_spa">
											주특기는 <span> <c:forEach items="${map.bBuyVo.eventList}" var="eventvo">
													<c:choose>
														<c:when test="${eventvo.eventName == 1 }">
                                            				복싱
                                            		</c:when>
														<c:when test="${eventvo.eventName == 2 }">
                                            				킥복싱
                                            		</c:when>
														<c:when test="${eventvo.eventName == 3 }">
                                            				종합격투기
                                            		</c:when>
														<c:when test="${eventvo.eventName == 4 }">
                                            				주짓수
                                            		</c:when>
													</c:choose>
												</c:forEach>

											</span> 입니다 <br> <span>스파클링</span>에서 <span>${map.bBuyVo.scoreCount}번의 스파링</span>을 했고 <span>${map.bBuyVo.rate}% 승률</span>을 자랑합니다
										</div>
									</div>
								</div>
								<div class="post_host_item2_2">
									<div class="post_host_spac">
										<h3>프로필</h3>
										<div class="post_host_spac_profile">
											<p>${map.bBuyVo.career}</p>

											<p>${map.bBuyVo.weightC}</p>


											<p>${map.bBuyVo.height}cm</p>
											<p>${map.bBuyVo.weight}kg</p>
											<p>전공: ${map.bBuyVo.major}</p>
											<!-- 경력 for문 -->
											<c:forEach items="${map.bBuyVo.recordList }" var="recordvo" varStatus="status">

												<c:if test="${!empty recordvo.recordType &&!empty recordvo.recordEvent &&!empty recordvo.recordMatch}">
													<br>
													<p class="record_ti">${status.count}번째공식기록</p>
													<p>
														<c:choose>
															<c:when test="${recordvo.recordEvent == 1 }">
                                            				복싱
                                            			</c:when>
															<c:when test="${recordvo.recordEvent == 2 }">
                                            				 킥복싱
                                            			</c:when>
															<c:when test="${recordvo.recordEvent == 3 }">
                                            				 종합격투기
                                            			</c:when>
															<c:when test="${recordvo.recordEvent == 4 }">
                                            				 주짓수
                                            		</c:when>
														</c:choose>
													</p>
													<p>${recordvo.recordType}</p>
													<p>${recordvo.recordDate}${recordvo.recordName}</p>
													<p>${recordvo.recordMatch}</p>
												</c:if>

											</c:forEach>
											<!-- 대관 x -->
											<c:if test="${map.bBuyVo.booking_no == 0}">
												<p class="p_dae">원하는 장소 : ${map.bBuyVo.b_buy_address}</p>
											</c:if>
											<!-- 대관 x end -->
										</div>
										<c:if test="${map.booking_state eq '예약중' || map.booking_state eq '예약대기중' }">
											<c:if test="${authUser.user_no != null }">
												<c:if test="${map.booking_state eq '예약중' }">
													<p id="conment_10">상대가 대관했거나 수락된 글 입니다</p>
												</c:if>
												<c:if test="${authUser.user_no == map.bbuyVoUser.user_no }">
													<p id="conment_10">이미 신청한 글 입니다</p>
												</c:if>
											</c:if>
										</c:if>

										<c:choose>
											<c:when test="${map.booking_state eq '예약중' }">

											</c:when>
											<c:otherwise>
												<c:if test="${authUser.user_no != null }">
													<c:if test="${authUser.user_no != map.bBuyVo.user_no }">
														<c:if test="${authUser.user_no != map.bbuyVoUser.user_no }">
															<c:if test="${empty map.bBuyList[0]  || map.bBuyList[0].b_buy_player_state ne '선택자' }">
																<c:choose>
																	<c:when test="${map.bBuyVo.booking_no == 0 }">
																		<a href="${pageContext.request.contextPath }/sparring/rent?subnum=1&bbuyno=${map.bBuyVo.b_buy_no}"><button class="dae_button_item2">
																				<span data-no="" ${map.bBuyVo.booking_no} class="dea_btn2">대관후 대결신청</span>
																			</button></a>
																	</c:when>
																	<c:otherwise>
																		<a href="${pageContext.request.contextPath }/sparring/writeForm?user_no=${authUser.user_no}&booking_no=${map.bBuyVo.booking_no}&subnum=1&bbuyno=${map.bBuyVo.b_buy_no}&bbuyuser=${param.userno}"><button class="dae_button_item2">
																				<span data-no="" ${map.bBuyVo.booking_no} class="dea_btn2">대결신청</span>
																			</button></a>
																	</c:otherwise>
																</c:choose>
															</c:if>

														</c:if>
													</c:if>
												</c:if>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="host_middle">

					<div class="host_middle_vs">vs</div>

				</div>
				<div class="host_items3">

					<div>
						<!-- item -->
						<div class="post_host_item_victim">
							<!-- 상대방 이름 -->
							<h1 id="nick_items" class="post_host_item1">
								<c:choose>
									<c:when test="${map.bBuyList[0]!= null}">
										<span> <!-- 상대방이름 넣는곳 --> ${map.bBuyList[0].nickname}
										</span>
									</c:when>
									<c:otherwise>
										<span>신청자</span>
									</c:otherwise>
								</c:choose>
							</h1>

							<!-- 상대방 end -->
							<!-- 글주인이름 end -->
							<!---->

							<c:choose>
								<c:when test="${map.bBuyList[0]!= null}">
									<div class="post_host_item2">
										<div class="post_host_item2_1">
											<div id="img_post" class="post_host_imgbox">
												<img src="${pageContext.request.contextPath }/upload/${map.bBuyList[0].user_photo}" alt="">

											</div>
											<div id="spac_pro" class="post_host_spa-profile">
												<p class="post_host_spa-profile_coment">"${map.bBuyList[0].word}"</p>
												<div class="post_host_spa-profile_spa">
													주특기는 <span> <c:forEach items="${map.bBuyList[0].eventList}" var="eventvo">
															<c:choose>
																<c:when test="${eventvo.eventName == 1 }">
	                                            				복싱
	                                            		</c:when>
																<c:when test="${eventvo.eventName == 2 }">
	                                            				킥복싱
	                                            		</c:when>
																<c:when test="${eventvo.eventName == 3 }">
	                                            				종합격투기
	                                            		</c:when>
																<c:when test="${eventvo.eventName == 4 }">
	                                            				주짓수
	                                            		</c:when>
															</c:choose>
														</c:forEach>
													</span> 입니다 <br> <span>스파클링</span>에서 <span>${map.bBuyList[0].scoreCount }번의 스파링</span>을 했고 <span>${map.bBuyList[0].rate }% 승률</span>을 자랑합니다
												</div>
											</div>
										</div>
										<div class="post_host_item2_2">
											<div class="post_host_spac">
												<h3>프로필</h3>
												<div id="spac_profile2" class="post_host_spac_profile">
													<p>${map.bBuyList[0].career}</p>
													<p>${map.bBuyList[0].weightC}</p>

													<p>${map.bBuyList[0].height}cm</p>
													<p>${map.bBuyList[0].weight}kg</p>
													<p>전공: ${map.bBuyList[0].major}</p>
													<!-- 경력 for문 -->

													<c:forEach items="${map.bBuyList[0].recordList }" var="recordvo" varStatus="status">

														<c:if test="${!empty recordvo.recordType &&!empty recordvo.recordEvent &&!empty recordvo.recordMatch}">
															<br>
															<p class="record_ti">${status.count}번째공식기록</p>
															<p>
																<c:choose>
																	<c:when test="${recordvo.recordEvent == 1 }">
                                            				복싱
                                            			</c:when>
																	<c:when test="${recordvo.recordEvent == 2 }">
                                            				 킥복싱
                                            			</c:when>
																	<c:when test="${recordvo.recordEvent == 3 }">
                                            				 종합격투기
                                            			</c:when>
																	<c:when test="${recordvo.recordEvent == 4 }">
                                            				 주짓수
                                            		</c:when>
																</c:choose>
															</p>
															<p>${recordvo.recordType}</p>
															<p>${recordvo.recordDate}${recordvo.recordName}</p>
															<p>${recordvo.recordMatch}</p>
														</c:if>

													</c:forEach>
													<!-- 대관 x -->
													<p class="p_dae"></p>
													<!-- 대관 x end -->
												</div>

												<c:if test="${authUser.user_no == param.userno || !map.bBuyList[0].b_buy_player_state eq '선택자' }">
													<c:choose>
														<c:when test="${map.booking_state eq '결제완료'}">
															<p id="conment_10"></p>

														</c:when>
														<c:when test="${map.booking_state eq '예약대기중' && map.bBuyList[0].b_buy_player_state eq '선택자'}">
															<p id="conment_10">상대의 수락 및 결제를 기다리고 있습니다</p>

														</c:when>

														<c:otherwise>
															<c:if test="${authUser.user_no == param.userno }">

																<c:choose>
																	<c:when test="${map.bBuyVo.b_buy_state == null && map.bBuyVo.booking_no != 0}">
																		<div id="accept_pay_btn">
																			<a href="${pageContext.request.contextPath }/sparring/accept?partneruserno=${map.bBuyList[0].user_no}&bookingNo=${map.bBuyVo.booking_no}&userNo=${map.bBuyVo.user_no}&bbuyno=${map.bBuyVo.b_buy_no}&mainnum=1&profileno=${map.bBuyVo.profile_no}&mybbuyno=${map.bBuyList[0].b_buy_no}"><button class="dae_button_item2">
																					<span class="dea_btn2">수락하고 결제하기</span>
																				</button></a>
																		</div>

																	</c:when>
																	<c:otherwise>
																		<div id="accept_btn">
																			<a href="${pageContext.request.contextPath }/sparring/accept?partneruserno=${map.bBuyList[0].user_no}&bookingNo=${map.bBuyVo.booking_no}&userNo=${map.bBuyVo.user_no}&bbuyno=${map.bBuyVo.b_buy_no}&mybbuyno=${map.bBuyList[0].b_buy_no}"><button class="dae_button_item2">
																					<span class="dea_btn2">수락하기</span>
																				</button></a>
																		</div>

																	</c:otherwise>
																</c:choose>
																<div id="refuse_btn">
																	<a href="${pageContext.request.contextPath }/sparring/refuse?mybbuyno=${map.bBuyList[0].b_buy_no}&partneruserno=${map.bBuyList[0].user_no}&bookingNo=${map.bBuyVo.booking_no}&userNo=${map.bBuyVo.user_no}&bbuyno=${map.bBuyVo.b_buy_no}&sessionuserno=${authUser.user_no}"><button class="dae_button_item3">
																			<span class="dea_btn2">거절하기</span>
																		</button></a>
																</div>
															</c:if>
														</c:otherwise>
													</c:choose>
												</c:if>


											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="post_host_item2">
										<div class="psh">
										<div id="no_here_box">
											<div>
												<img id="no_select_here_img" alt="" src="${pageContext.request.contextPath }/upload/glove.jpg">
											</div>
											<div>
												<h1 id="no_select_here">신청자가 없습니다</h1>
											</div>
										</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>

							<c:if test="${map.bBuyList[0]!= null}">
								<c:if test="${empty map.bBuyList[0] || map.bBuyList[0].b_buy_player_state eq '신청자' }">
									<div class="chell">
										<div class="ss_box">
											<div class="swiper-button-prev ss_prev"></div>
											<div class="swiper-container ss_con">
												<div class="swiper-wrapper ss_wrapper">
													<c:forEach items="${map.bBuyList}" var="bBuyVo">
														<div class="swiper-slide ss_slide" data-userno="${bBuyVo.user_no }" data-no="${bBuyVo.b_buy_no}">
															<div>
																<i class="fas fa-user"></i>
															</div>
															<div>${bBuyVo.nickname}</div>
														</div>
													</c:forEach>

												</div>
											</div>

											<div class="swiper-button-next ss_next"></div>
										</div>
									</div>
								</c:if>
							</c:if>
						</div>

					</div>
					<!--  -->
				</div>
			</section>

			<!-- post_host end-->
			<!-- detail -->
			<c:if test="${!empty map.gAVo}">
				<section class="detail_info">
					<div class="detail_info_img">
						<div id="main_img_1" class="detail_info_first-img">
							<img class="detail_info_first-img_main" src="${pageContext.request.contextPath}/upload/${map.gAVo.gymimgList[0].gym_img_savename}" alt="">
						</div>
						<div class="detail_info_sub-imgs">
							<!-- **사진 총 6개만 받을 것 -->
							<!-- sub img 1pc -->
							<c:forEach items="${map.gAVo.gymimgList}" var="vo">
								<span style="cursor: pointer" class="detail_info_sub-a" data-no="${vo.gym_img_no}"><img class="detail_info_sub-img" src="${pageContext.request.contextPath}/upload/${vo.gym_img_savename}" alt=""></span>
							</c:forEach>
							<!-- sub img 1pc end-->


						</div>
					</div>
					<div class="detail_info_amenities">
						<div class="amenities">
							<div class="amenities_item1">
								<!-- 체육관 네임 -->

								<div class="amen_box">

									<p class="amenities_item1_title">${map.gAVo.gym_name}<span></span>
									</p>
									<div class="amenities_item1_skill">${map.gAVo.gym_event}</div>
								</div>

								<div class="amen_box2">
									<div>
										<div class="amenities_item1_sub5">${map.gAVo.gym_ph}</div>
										<!-- 주소 -->
										<div class="amenities_item1_sub">
											<span class="amenities_item1_sub2"> <i class="fas fa-map-marker-alt"></i>${map.gAVo.gym_address}</span>
										</div>
									</div>

								</div>

							</div>
							<div class="amenities_item2">
								<!-- 네글자만 쓰세요 -->
								<c:choose>
									<c:when test="${map.gAVo.conList[0].con_state eq '1'}">
										<div class="amenities_item2_sub">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload/paking.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>무료 주차</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="amenities_item2_sub" style="opacity: 0.2">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload/paking.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>무료 주차</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${map.gAVo.conList[1].con_state eq '1'}">
										<div class="amenities_item2_sub">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload//shower.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>샤워 가능</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="amenities_item2_sub" style="opacity: 0.2">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload//shower.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>샤워 가능</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${map.gAVo.conList[2].con_state eq '1'}">
										<div class="amenities_item2_sub">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload//glove.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>수건 대여</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="amenities_item2_sub" style="opacity: 0.2">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload//glove.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>수건 대여</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${map.gAVo.conList[3].con_state eq '1'}">
										<div class="amenities_item2_sub">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload/lacker.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>락커 대여</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="amenities_item2_sub" style="opacity: 0.2">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload/lacker.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>락커 대여</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${map.gAVo.conList[4].con_state eq '1'}">
										<div class="amenities_item2_sub">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload/glove.jpg" alt="">
											<div class="amenities_item2_sub_title">
												<div>글러브대여</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="amenities_item2_sub" style="opacity: 0.2">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload/glove.jpg" alt="">
											<div class="amenities_item2_sub_title">
												<div>글러브대여</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${map.gAVo.conList[5].con_state eq '1'}">
										<div class="amenities_item2_sub">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload//wear.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>운동복대여</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="amenities_item2_sub" style="opacity: 0.2">
											<img class="amenities_item2_sub_img" src="${pageContext.request.contextPath}/upload//wear.png" alt="">
											<div class="amenities_item2_sub_title">
												<div>운동복대여</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>



							</div>

						</div>
					</div>
										<input type="hidden" name="gym_name" value="${map.gAVo.gym_name}">
					<input type="hidden" name="gym_address" value="${map.gAVo.gym_address}">
				</section>
				<!-- detail end -->
				<!-- notice -->
				<section class="notice">
					<div class="notice_title">
						<h1>체육관 공지사항</h1>
					</div>
					<div class="notice_text">
						<p class="time_notice">◈ 체육관 운영시간은 ${map.gAVo.gym_time} 입니다</p>
						<p>◈모든 매치는 현재 매치 중, 이동 시 마스크 필수 착용입니다</p>
						<p>■${map.gAVo.gym_notice}</p>

					</div>
					<br>

				</section>
				<!-- notice end-->
				<!-- map -->
				<section class="dae_map">
									<div id="map" style="width: 500px; height: 400px;">
						
					<p style="margin-top:-12px">
					    <em class="link">
					        <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
					            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
					        </a>
					    </em>
					</p>
					<div id="map" style="width:100%;height:350px;"></div>
					
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=301083c755fd8c3be91c51b314f886f3&libraries=services"></script>
		<script>
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(37.485147843235154, 127.0162899980831), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch($("[name='gym_address']").val(), function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$("[name='gym_name']").val()+'</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		</script>
		
		
		<br><br><br><br><br><br>
				
				
				
				
				</section>
				<!-- map end -->
				<!-- 버튼 -->
			</c:if>
			<div id="dae_footer">
			<c:if test="${map.booking_state ne '결제완료' }">
				<c:if test="${map.bBuyList[0].b_buy_player_state eq '선택자' && map.bBuyList[0].user_no == authUser.user_no }">
					<div class="dae_foo">
						<div>
							<a href="${pageContext.request.contextPath }/sparring/accept?partneruserno=${map.bBuyVo.user_no}&bookingNo=${map.bBuyVo.booking_no}&userNo=${map.bBuyList[0].user_no}&bbuyno=${map.bBuyVo.b_buy_no}&mybbuyno=${map.bBuyList[0].b_buy_no}&mainnum=2&profileno=${map.bBuyList[0].profile_no}">
								<button class="dae_button_item_2">
									<span class="dea_btn_2">결제하기</span>
								</button>
							</a>
						</div>
						<div>
							<a href="${pageContext.request.contextPath }/sparring/cancel?bbuyno=${ map.bBuyList[0].b_buy_no}&bookingno=${map.bBuyVo.booking_no}&userno=${map.bBuyVo.user_no}">
								<button class="dae_button_item3">
									<span class="dea_btn_3">취소하기</span>
								</button>
							</a>
						</div>
				</c:if>
				</c:if>
				<div>
					<a href="${pageContext.request.contextPath }/">
						<button class="dae_button_item3">
							<span class="dea_btn">돌아가기</span>
						</button>
					</a>
				</div>
			</div>

		</div>
		<!-- 버튼 end -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!--//footer//-->
	</div>
	</div>
	<input id="bookingno" data-bookingno="${map.bBuyVo.booking_no }" type="hidden" name="" value="">
	<input id="userno" data-userno="${map.bBuyVo.user_no }" type="hidden" name="" value="">
	<input id="bbuyno" data-bbuyno="${map.bBuyVo.b_buy_no }" type="hidden" name="" value="">
	<input id="profileno" data-profileno="${map.bBuyVo.profile_no }" type="hidden" name="" value="">
	<input id="sessionuser" data-sessionuser="${authUser.user_no}" type="hidden" name="" value="">
	<script>
		var mySwiper = new Swiper('.swiper-container', {
			slidesPerView : 2,

			//loop: true,

			navigation : {
				nextEl : '.swiper-button-next',
				prevEl : '.swiper-button-prev',

			},
		});
	</script>
</body>
<script type="text/javascript">
	//메인 사진 변경

	
	
	$(".detail_info_sub-a").on("click", function() {
		console.log("클릭");
		var gymImgNo = $(this).data("no");
		
		
		
		

		$.ajax({

			url : "${pageContext.request.contextPath }/sparring/api/gymimgone", //컨트롤러의 url과 파라미터
			type : "post", // 겟 포스트
			//contentType : "application/json",
			data : {
				gymImgNo : gymImgNo

			},

			//dataType : "json",
			success : function(gIVo) { //성공시
				console.log(gIVo);

				mainImg(gIVo);
			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});

	});

	//메인이미지 변경
	function mainImg(gIVo) {
		str = "";
		str += "<img class='detail_info_first-img_main' src='${pageContext.request.contextPath}/upload/"+gIVo.gym_img_savename+"' alt=''>";

		$("#main_img_1").html(str);
	}

	//신청자 리스트 클릭시 신청자 프로필 보여주기
	$(".ss_wrapper").on("click", ".ss_slide", function() {

		var bbuyNo = $(this).data("no");
		console.log(bbuyNo);
		var userNo = $(this).data("userno");
		console.log(userNo);
		var bookingno = $("#bookingno").data("bookingno");
		console.log(bookingno);
		var userno = $("#userno").data("userno");
		console.log(userno);
		var bbuyno = $("#bbuyno").data("bbuyno");
		console.log(bbuyno);
		var profileno = $("#profileno").data("profileno");
		console.log(profileno);
		var sessionuserno = $("#sessionuser").data("sessionuser");
		console.log(sessionuserno);
		$.ajax({

			url : "${pageContext.request.contextPath }/sparring/api/bbuyone", //컨트롤러의 url과 파라미터
			type : "post", // 겟 포스트
			//contentType : "application/json",
			data : {
				bbuyNo : bbuyNo,
				userNo : userNo
			},

			//dataType : "json",
			success : function(bbuyVo) { //성공시
				console.log(bbuyVo);

				addprofile(bbuyVo);
				nick(bbuyVo)
				addprofile2(bbuyVo)
				
				accept(bbuyVo,bookingno,userno,bbuyno,profileno);
				refuse(bbuyVo,bookingno,userno,bbuyno,profileno,sessionuser);
			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});

	});
	function addprofile(bbuyVo) {
		
		
		
		var recordlength = $(bbuyVo.recordList).length;
		
		$("#img_post").html(" ");
		str2 = "";
		str2 = '<img src="${pageContext.request.contextPath }/upload/'+bbuyVo.user_photo+'" alt="">';
		$("#img_post").append(str2);	
		$("#spac_profile2").html(" ");

		console.log(recordlength);
		str = '';
		str += '<div class="post_host_spac_profile">';
		str += '	<p>' + bbuyVo.career + '</p>';
		str += '	<p>' + bbuyVo.weightC + '</p>';
		str += '	<p>' + bbuyVo.height + 'cm</p>';
		str += '	<p>' + bbuyVo.weight + 'kg</p>';
		str += '	<p>전공: ' + bbuyVo.major + '</p>';
		for (var i = 0; i < recordlength; i++) {
			str += ' <br>'
			str += ' 	<p class="record_ti">'+(i+1)+'번째공식기록</p>'
			
			str += '	<p>' + bbuyVo.recordList[i].recordType + '</p>';
			str += '	<p>' + bbuyVo.recordList[i].recordDate

			if (bbuyVo.recordList[i].recordEvent == 1) {
				str += '	복싱';

			} else if (bbuyVo.recordList[i].recordEvent == 2) {

				str += '	킥복싱';
			} else if (bbuyVo.recordList[i].recordEvent == 3) {
				str += '	종합격투기';

			} else if (bbuyVo.recordList[i].recordEvent == 4) {

				str += ' 주짓수';
			}
			str += bbuyVo.recordList[i].recordName;

			str += ' </p>';

		}
		str += ' <p class="p_dae"></p>';
		str += '</div>';

		$("#spac_profile2").append(str);
	}

	function nick(bbuyVo) {
		$("#nick_items").html(" ");

		str = "<span>" + bbuyVo.nickname + "</span>";

		$("#nick_items").append(str);
	}
	function addprofile2(bbuyVo) {
		$("#spac_pro").html(" ");
		var eventlength = $(bbuyVo.eventList).length;

		str = '';
		str += '<p class="post_host_spa-profile_coment">"' + bbuyVo.word
				+ '"</p>';
		str += '<div class="post_host_spa-profile_spa">';
		str += '주특기는 <span>';
		for (var i = 0; i < eventlength; i++) {
			if (bbuyVo.eventList[i].eventName == 1) {
				str += '복싱';

			} else if (bbuyVo.eventList[i].eventName == 2) {
				str += '킥복싱';

			} else if (bbuyVo.eventList[i].eventName == 3) {
				str += '종합격투기';

			} else if (bbuyVo.eventList[i].eventName == 4) {
				str += '주짓수';

			}
		}
		str += '</span> 입니다 <br> <span>스파클링</span>에서 <span>'
				+ bbuyVo.scoreCount + '번의 스파링</span>을 했고 <span>' + bbuyVo.rate
				+ '% 승률</span>을 자랑합니다';
		str += '</div>';
		$("#spac_pro").append(str);

	}
	
	function accept(bbuyVo,bookingno,userno,bbuyno,profileno){
		$("#accept_btn").html(" ");
		
		str="";
		str += '<a href="${pageContext.request.contextPath }/sparring/accept?partneruserno='+bbuyVo.user_no+'&bookingNo='+bookingno+'&userNo='+userno+'&bbuyno='+bbuyno+'">'
		str += '<button class="dae_button_item2">';
		str += '<span class="dea_btn2">수락하기</span>';
		str += '</button></a>';
		$("#accept_btn").append(str);
	}
	
	function refuse(bbuyVo,bookingno,userno,bbuyno,profileno){
		
		$("#refuse_btn").html(" ");
		str="";
		str += '<a href="${pageContext.request.contextPath }/sparring/refuse?bbuyno='+bbuyno+'&mybbuyno='+bbuyVo.b_buy_no+'&partneruserno='+bbuyVo.user_no+'&bookingNo='+bookingno+'&userNo='+userno+'">'
		str += '<button class="dae_button_item3">';
		str += '<span class="dea_btn2">거절하기</span>';
		str += '</button></a>';
		
		$("#refuse_btn").append(str);
	}
</script>


</html>