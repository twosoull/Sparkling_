<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/style_1.css">
<script src="https://kit.fontawesome.com/5a9452f10d.js" crossorigin="anonymous"></script>
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

	<!-- rental search-->

	<!-- Sparring -->
	<div class="wrap">
		<div class="container">
			<div class="sm2_10">
				<h1 class="sm_title2">근처 체육관을 보러가세요!</h1>
			</div>
			<!-- search -->
			<section class="sm_searching">
				<!-- item1 
                        -->
				<div class="sm_searching_item1">

					<div class="sm_searching_item2_list2">
						<!-- 
                                <a class="item2_list_a1" href="${pageContext.request.contextPath }/sparring/rent?userno=${authUser.user_no}">
                                    <div class="list1_btn2">
                                        <div class="item2_list_a1_div">
                                            <i class="fas fa-map-marker-alt"></i>
                                            가까운 체육관 검색
                                        </div>
                                    </div>
                                </a>
                             -->
					</div>

					<!-- 시군구 검색 -->

					<form class="sm_searching_item1_form" action="${pageContext.request.contextPath}/sparring/rent">

						<a class="item2_list_a3" href="${pageContext.request.contextPath}/sparring/rent">
							<div class="list1_btn1">
								<div>전체보기</div>
							</div>
						</a>
						<!-- 
                                <select class="item1_address" name="address2" id="">
                                  <option class="item1_address_option" value="21-03-05">날짜선택</option>
                                </select>
                                <select class="item1_address" name="address2" id="">
                                  <option class="item1_address_option" value="21-03-05">시간선택</option>
                                </select>
                                 -->
						<div class="input-text_box">
							<input class="input-text" type="text" name="search" placeholder="시군구 또는 체육관 이름..">
						</div>

						<button class="address_btn">검색</button>
					</form>

				</div>
				<!-- item2 -->


			</section>

			<!-- rental -->
			<section class="rental">
				<!-- rental 1pc -->
				<c:forEach items="${map.gymList}" var="vo">
					<a href="${pageContext.request.contextPath }/sparring/rentdetail?gymNo=${vo.gym_no}&subnum=${param.subnum}&bbuyno=${param.bbuyno}" class="rental_container">
						<div class="rental_img">
							<img class="rental_img" src="${pageContext.request.contextPath }/upload/${vo.gymImgSavename }" alt="">
						</div>
						<div class="rental_profile">
							<div class="rental_profile1 rental_profile_gim">${vo.addressHalf}| ${vo.gym_name}</div>
							<div class="rental_profile1 rental_profile_address">
								<i class="fas fa-map-marker-alt"></i>${vo.gym_address}</div>
							<div class="rental_profile1 rental_profile_skills">${vo.gym_event }</div>
							<div class="rental_profile1 rental_profile_price1">${vo.money}원/ 2인</div>
							<div class="rental_profile1 rental_profile_price2">
								<span class="person">1인</span> <span class="pay">${vo.moneyHalf}원</span>

							</div>


						</div>

					</a>
				</c:forEach>
				<!-- rental 1px end -->







			</section>
		</div>
		<!-- Paging-->
		<section id="paging">
			<ul>
				<c:if test="${map.pMap.prev == true}">
					<li><a href="${pageContext.request.contextPath}/sparring/rent?crtPage=${map.pMap.startPageBtnNo-1}&search=${param.searchMatchVo.search}">◀</a></li>
				</c:if>

				<c:forEach begin="${map.pMap.startPageBtnNo}" end="${map.pMap.endPageBtnNo}" step="1" var="page">
					<li><a href="${pageContext.request.contextPath}/sparring/rent?crtPage=${page}&search=${param.searchMatchVo.search}">${page}</a></li>
				</c:forEach>

				<c:if test="${map.pMap.next == true }">
					<li><a href="${pageContext.request.contextPath}/sparring/rent?crtPage=${map.pMap.endPageBtnNo+1}&search=${param.searchMatchVo.search}">▶</a></li>
				</c:if>
			</ul>


			<div class="clear"></div>
		</section>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!--//footer//-->
		<!-- container end-->
	</div>
	<!-- wrap end -->
</body>

</html>
