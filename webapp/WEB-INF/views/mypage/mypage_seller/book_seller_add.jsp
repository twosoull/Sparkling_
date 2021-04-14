<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/mypage_buy.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/booking.css">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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
					<div class="content_booking_header_fx">
						<div>예약판매자 등록</div>

					</div>
					<!--//content_delevery_product_header//-->
					<div class="content_product_line"></div>

					<div class="content_seller_regi">
						<div class="seller_regi_title">사업자 정보</div>
						<br>

						<!--입력폼 (추후 form 추가)-->
						<c:choose>
							<c:when test="${authUser.prod_type == 0}">
								<form id="addForm"
									action="${pageContext.request.contextPath}/mypage/book/bookselleradd"
									method="post">
							</c:when>
							<c:otherwise>
								<form id="addForm"
									action="${pageContext.request.contextPath}/mypage/book/booksellermodify"
									method="post">
									<input type="hidden" name="sell_no" value="${authUser.sell_no}">
							</c:otherwise>
						</c:choose>



						<table class="seller_regi_form">
							<tbody>

								<tr>
									<td class="basic">사업자 이름</td>
									<td><input class="input_con" type="text"
										name="book_sell_name" placeholder="이름을 입력해주세요"></td>
								</tr>
								<tr>
									<td class="basic">사업체 이름</td>
									<td><input class="input_con" type="text"
										name="book_com_name" placeholder="회사명을 입력해주세요"></td>
								</tr>
								<tr>
									<td class="basic">사업자 번호</td>
									<td><input class="input_con" type="text"
										name="book_business_no" placeholder="번호를 입력해주세요">
										<div class="seller_alert">'-'와 공백을 제외하고 숫자만 입력해주세요. (예:
											1231212345)</div></td>
								</tr>

								<tr id="address">
									<td class="basic">사업자 주소</td>
									<td id="address_content">
										<div id="add_search">
											<div id="address_content1">
												<input type="hidden" id="confmKey" name="confmKey" value="">
												<div class="da">
													<input type="text" id=sample4_postcode name="post"
														placeholder="우편번호" readonly>
												</div>
												<div class="da">
													<input type="text" id="sample4_roadAddress"
														name="roadaddress" placeholder="도로명주소" value="" readonly>
												</div>
											</div>
											<button type="button" id="search_btn">검색하기</button>
										</div>
										<div id="address_content2">
											<input type="text" id="addressdetail" name="addressdetail"
												placeholder="나머지주소를 입력해주세요." value=""> <input
												type="hidden" id="sample4_jibunAddress" placeholder="지번주소">
											<input type="hidden" id="sample4_extraAddress"
												placeholder="참고항목"> <span id="guide"
												style="color: #999; display: none"></span>
										</div>

									</td>
								</tr>
								<tr>
									<td class="basic">사업자 계좌번호</td>
									<td><select name="book_sell_bank">
											<option disabled>은행 선택</option>
											<option>국민은행</option>
											<option>신한은행</option>
											<option>우리은행</option>
											<option>하나은행</option>
											<option>기업은행</option>
											<option>농협</option>
											<option>카카오뱅크</option>
									</select>
										<div class="account_input">
											<input class="input_con" type="text" name="book_sell_account"
												placeholder="계좌번호를 입력해주세요">
										</div>
										<div class="seller_alert">'-'와 공백을 제외하고 숫자만 입력해주세요. (예:
											1234567890123)</div></td>
								</tr>
								<input type="hidden" name="book_type" value="1">
								<input type="hidden" name="user_no" value="${authUser.user_no}">
							</tbody>
						</table>

						<div class="seller_regi_btn_site">
							<button class="seller_regi_btn" type="submit">판매자 등록</button>
						</div>
						</form>
						<!--입력폼-->

						<div id="space"></div>
					</div>
					<!--//content_seller_regi-->
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
	$(".da").on("click", function() {
		sample4_execDaumPostcode();

	});

	$("#search_btn").on("click", function() {
		sample4_execDaumPostcode();
	});

	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_roadAddress").value = roadAddr;
						document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

						// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
						if (roadAddr !== '') {
							document.getElementById("sample4_extraAddress").value = extraRoadAddr;
						} else {
							document.getElementById("sample4_extraAddress").value = '';
						}

						var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
	}

	//주소 api

	//입력 제한요건 (if문 아니고 배열에 넣어서 체크함)
	$(".seller_regi_btn").on(
			"click",
			function() {

				var name = $("[name='book_sell_name']").val();

				var comName = $("[name='book_com_name']").val();

				var no = $("[name='book_business_no']").val();

				var roadAddress = $("[name='roadaddress']").val();

				var addressdetail = $("[name='addressdetail']").val();

				var bank = $("[name='book_sell_bank']").val();

				var acc = $("[name='book_sell_account']").val();

				var formArr = new Array(name, comName, no, roadAddress,
						addressdetail, bank, acc);

				for (var i = 0; i < formArr.length; i++) {
					if (!formArr[i]) {
						alert("모든 정보를 입력해 주세요");
						return false;
					}
					;
				}

				return true;

			});
</script>
</html>