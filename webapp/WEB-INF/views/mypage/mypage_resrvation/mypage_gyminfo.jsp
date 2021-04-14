<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/booking.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div class="wrap">
		<div class="container">
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
			<!--//header//-->

			<div class="middle">
				<c:import url="/WEB-INF/views/include/sidemenu.jsp"></c:import>
				<!--//side_menu//-->

				<!--////////////////////////////////////////////-->

				<div class="content_seller">
					<div class="content_booking_header">
						<div>체육관 관리</div>
					</div>
					<!--//content_delevery_product_header//-->
					<div class="content_product_line"></div>

					<!--탭의 위쪽에 위치-->
					<div class="regi_btn_site">
						<a href="${pageContext.request.contextPath}/mypage/book/gymaddform">
							<button type="button" class="regi_btn">체육관 등록</button>
						</a>
					</div>

					<!-- 탭부분(다수 체육관 소유 시) pill형식으로 표시 -->
					<div class="own_gym_tab">
						<ul>
						
						<c:forEach var="vo" items="${gymMap.gymList }">
							<a href="${pageContext.request.contextPath}/mypage/book/gym?no=${vo.sell_no}&gymno=${vo.gym_no}">
							
							<%-- 
							<c:if test="${param.gymno eq vo.gym_no }">
								<li class="tab_selected">${vo.gym_name }</li>
							</c:if>
							--%>
							
							<li class="tab">${vo.gym_name }</li>
							</a>
							
						</c:forEach>
						</ul>										
					</div>


					<!-- 대관 등록 버튼 -->
					<div class="regi_btn_site">
						<a href="${pageContext.request.contextPath}/mypage/book/bookaddform?gymno=${gymMap.gymVo.gym_no}">
						<button type="button" class="regi_btn">대관 등록</button></a>
					</div>

					<!--체육관 정보-->
					<div class="gym_regi_form">
						<form action="${pageContext.request.contextPath}/mypage/book/gymmodify" method="post"
							enctype="multipart/form-data">
							<table>
								<tr>
									<td class="basic">체육관 이름</td>
									<td colspan="4"><input class="input_text" type="text" name="gym_name"
										value="${gymMap.gymVo.gym_name }"></td>
								</tr>

								<tr>
									<td class="basic">체육관 주소</td>
									<td colspan="4"><input class="input_text" type="text" name="gym_address"
										value="${gymMap.gymVo.gym_address }"></td>
								</tr>

								<tr>
									<td class="basic">체육관 전화번호</td>
									<td colspan="4"><input class="input_text" type="text" name="gym_ph"
										value="${gymMap.gymVo.gym_ph }"></td>
								</tr>

								<tr class="input_plus">
									<td>체육관 운영시간</td>
									<td colspan="4"><textarea name="gym_time">${gymMap.gymVo.gym_time }</textarea></td>
								</tr>

								<tr class="basic_chk">
									<td>체육관 주 종목</td>
									
									<td class="basic_chk_padding"><c:choose>
											<c:when test="${gymMap.gymVo.gym_event eq '복싱' }">
												<input id="box" type="radio" name="gym_event" value="복싱" checked="checked">
												<label for="box">복싱</label>
											</c:when>
											<c:otherwise>
												<input id="box" type="radio" name="gym_event" value="복싱">
												<label for="box">복싱</label>
											</c:otherwise>
										</c:choose>
									</td>
									
									<td class="basic_chk_padding"><c:choose>
											<c:when test="${gymMap.gymVo.gym_event eq '킥복싱' }">
												<input id="kbox" type="radio" name="gym_event" value="킥복싱" checked="checked">
												<label for="kbox">킥복싱</label>
											</c:when>
											<c:otherwise>
												<input id="kbox" type="radio" name="gym_event" value="킥복싱">
												<label for="kbox">킥복싱</label>
											</c:otherwise>
										</c:choose>
									</td>

									<td class="basic_chk_padding"><c:choose>
											<c:when test="${gymMap.gymVo.gym_event eq '주짓수' }">
												<input id="jiu" type="radio" name="gym_event" value="주짓수" checked="checked">
												<label for="jiu">주짓수</label>
											</c:when>
											<c:otherwise>
												<input id="jiu" type="radio" name="gym_event" value="주짓수">
												<label for="jiu">주짓수</label>
											</c:otherwise>
										</c:choose>
									</td>

									<td class="basic_chk_padding"><c:choose>
											<c:when test="${gymMap.gymVo.gym_event eq '종합격투기' }">
												<input id="mma" type="radio" name="gym_event" value="종합격투기" checked="checked">
												<label for="mma">종합격투기</label>
											</c:when>
											<c:otherwise>
												<input id="mma" type="radio" name="gym_event" value="종합격투기">
												<label for="mma">종합격투기</label>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>

								<tr class="basic_chk">
									<td>체육관 편의시설</td>
									
									<td>
										<c:forEach var="vo" items="${gymMap.conList}">
											<c:choose>
										
												<c:when test="${vo.con_name eq '주차장' && vo.con_state eq 1 }">
													<input id="park" type="checkbox" name="conve" value="park" checked="checked">
													<label for="park">주차장</label>
												</c:when>
										
												<c:when test="${vo.con_name eq '주차장'}">
													<input id="park" type="checkbox" name="conve" value="park">
													<label for="park">주차장</label>
												</c:when>
											</c:choose>
										</c:forEach>
									</td>
									<td>
										<c:forEach var="vo" items="${gymMap.conList}">
											<c:choose>
											
												<c:when test="${vo.con_name eq '샤워실' && vo.con_state eq 1 }">
													<input id="shower" type="checkbox" name="conve" value="shower" checked="checked">
													<label for="shower">샤워실</label>
												</c:when>
											
												<c:when test="${vo.con_name eq '샤워실' }">
													<input id="shower" type="checkbox" name="conve" value="shower">
													<label for="shower">샤워실</label>
												</c:when>
											</c:choose>
										</c:forEach>
									</td>
									<td colspan="2">
										<c:forEach var="vo" items="${gymMap.conList}">
											<c:choose>
											
												<c:when test="${vo.con_name eq '수건' && vo.con_state eq 1 }">
													<input id="towel" type="checkbox" name="conve" value="towel" checked="checked">
													<label for="towel">수건</label>
												</c:when>
											
												<c:when test="${vo.con_name eq '수건' }">
													<input id="towel" type="checkbox" name="conve" value="towel">
													<label for="towel">수건</label>
												</c:when>
											</c:choose>
										</c:forEach>
									</td>
								</tr>

								<tr>
									<td></td>
									<td class="basic_chk_padding">
										<c:forEach var="vo" items="${gymMap.conList}">
											<c:choose>
											
												<c:when test="${vo.con_name eq '락커' && vo.con_state eq 1 }">
													<input id="locker" type="checkbox" name="conve" value="locker" checked="checked">
													<label for="locker">락커</label>
												</c:when>
											
												<c:when test="${vo.con_name eq '락커' }">
													<input id="locker" type="checkbox" name="conve" value="locker">
													<label for="locker">락커</label>
												</c:when>
											</c:choose>
										</c:forEach>
									</td>
									<td class="basic_chk_padding">
										<c:forEach var="vo" items="${gymMap.conList}">
											<c:choose>
											
												<c:when test="${vo.con_name eq '글러브' && vo.con_state eq 1 }">
													<input id="glove" type="checkbox" name="conve" value="glove" checked="checked"> 
													<label for="glove">글러브</label>
												</c:when>
											
												<c:when test="${vo.con_name eq '글러브' }">
													<input id="glove" type="checkbox" name="conve" value="glove"> 
													<label for="glove">글러브</label>
												</c:when>
											</c:choose>
										</c:forEach>
									</td>
									<td class="basic_chk_padding" colspan="2">
										<c:forEach var="vo" items="${gymMap.conList}">
											<c:choose>
											
												<c:when test="${vo.con_name eq '글러브' && vo.con_state eq 1 }">
													<input id="wear" type="checkbox" name="conve" value="wear" checked="checked"> 
													<label for="wear">운동복</label>
												</c:when>
											
												<c:when test="${vo.con_name eq '글러브' }">
													<input id="wear" type="checkbox" name="conve" value="wear"> 
													<label for="wear">운동복</label>
												</c:when>
											</c:choose>
										</c:forEach>
									</td>
								</tr>

								<tr class="input_plus">
									<td>공지사항</td>
									<td colspan="4"><textarea name="gym_notice">${gymMap.gymVo.gym_notice }</textarea></td>
								</tr>


								<tr>
									<td class="basic">체육관 사진</td>
									<td colspan="3"><input type="file"></td>
									<!-- img태그 넣기 -->
								</tr>

							</table>

							<div class="gym_regi_btn_site">
								<button class="gym_regi_btn" type="submit">정보 수정</button>
								<a href="${pageContext.request.contextPath}/mypage/book/gymremove?no=${vo.sell_no}&gymno=${vo.gym_no}">
									<button class="gym_regi_btn" type="button">체육관 삭제</button>
								</a>
							</div>

						</form>
					</div>
					<!--//gym_regi_form-->
				</div>
				<!-- //content_seller -->
				
			</div>
			<!--//middle-->

		
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			<!--//footer//-->
		</div>
		<!--//container-->
	</div>
	<!--//wrap-->
	</div>
</body>

<script type="text/javascript">

	//url에서 파라미터값 가져오는 함수 --> getParameterByName('함수명');
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	/*
	//체육관 버튼 색변경 이벤트 유지
	window.onload=function(){
		
		var paramNo = getParameterByName('gymno');
		var gymNo = $("[name='gym_no']").val();
		var gymArr = [];
		
		//각 체육관에서 체육관번호 뽑아서 배열에 넣기 (jstl과 js 혼용) 
		<c:forEach var="vo" items="${gymMap.gymList}">
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
	*/

</script>
</html>