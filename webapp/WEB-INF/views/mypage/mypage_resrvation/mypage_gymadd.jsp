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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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
						<div>체육관 등록</div>
					</div>
					<!--//content_delevery_product_header//-->
					<div class="content_product_line"></div>

					<!--등록폼-->
					<div class="gym_regi_form">
						<form action="${pageContext.request.contextPath }/mypage/book/gymadd" method="post"
							enctype="multipart/form-data">
							<table id="space2">
								<tr>
									<td class="basic">체육관 이름</td>
									<td colspan="4"><input class="input_text" type="text" name="gym_name"> <!-- 사업자번호 숨김 -->
										<input type="hidden" name="sell_no" value="${authUser.sell_no }"></td>
								</tr>

								<!-- 
								<tr>
									<td class="basic">체육관 주소</td>
									<td colspan="4"><input class="input_text" type="text"
										name="gym_address"></td>
								</tr>
								 -->

								<tr id="address">
									<td id="address_title" class="basic">주소</td>
									<td colspan="4" id="address_content">
										<div id="add_search">
											<div id="address_content1">
												<input type="hidden" id="confmKey" name="confmKey" value=""> 
												<div class="da">
													<input type="text" id=sample4_postcode name="post" placeholder="우편번호" readonly> 
												</div>	
												<div class="da">
													<input type="text" id="sample4_roadAddress" name="roadaddress" value="" placeholder="도로명주소" readonly>
												</div>
											</div>
											<button type="button" id="search_btn">검색하기</button>
										</div>
										<div id="address_content2">
											<input type="text" id="addressdetail" name="addressdetail" placeholder="나머지주소를 입력해주세요.">
											<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소"> 
											<input type="hidden" id="sample4_extraAddress" placeholder="참고항목"> 
											<span id="guide" style="color: #999; display: none"></span>
										</div>
									</td>
								</tr>

								<tr>
									<td class="basic">체육관 전화번호</td>
									<td colspan="4"><input class="input_text" type="text" name="gym_ph"></td>
								</tr>

								<tr class="input_plus">
									<td>체육관 운영시간</td>
									<td colspan="4"><textarea name="gym_time"></textarea></td>
								</tr>

								<tr class="basic_chk">
									<td>체육관 주 종목</td>


									<td class="basic_chk_padding"><input id="box" type="radio" name="gym_event" value="복싱">
										<label for="box">복싱</label></td>
									<td class="basic_chk_padding"><input id="kbox" type="radio" name="gym_event"
										value="킥복싱"> <label for="kbox">킥복싱</label></td>
									<td class="basic_chk_padding"><input id="jiu" type="radio" name="gym_event"
										value="주짓수"> <label for="jiu">주짓수</label></td>
									<td class="basic_chk_padding"><input id="mma" type="radio" name="gym_event"
										value="종합격투기"> <label for="mma">종합격투기</label></td>
								</tr>

								<tr class="basic_chk">
									<td>체육관 편의시설</td>

									<td><input id="park" type="checkbox" name="conve" value="park"> <label
										for="park">주차장</label></td>
									<td><input id="shower" type="checkbox" name="conve" value="shower"> <label
										for="shower">샤워실</label></td>
									<td colspan="2"><input id="towel" type="checkbox" name="conve" value="towel">
										<label for="towel">수건</label></td>
								</tr>

								<tr>
									<td></td>
									<td class="basic_chk_padding"><input id="locker" type="checkbox" name="conve"
										value="locker"> <label for="locker">락커</label></td>
									<td class="basic_chk_padding"><input id="glove" type="checkbox" name="conve"
										value="glove"> <label for="glove">글러브</label></td>
									<td class="basic_chk_padding" colspan="2"><input id="wear" type="checkbox"
										name="conve" value="wear"> <label for="wear">운동복</label></td>
								</tr>

								<tr class="input_plus">
									<td>공지사항</td>
									<td colspan="4"><textarea name="gym_notice"></textarea></td>
								</tr>

								<tr>
									<td class="basic">체육관 대표사진</td>
									<td colspan="4"><input type="file" name="file1"></td>
								</tr>

								<tr>
									<td class="basic">체육관 추가사진</td>
									<td colspan="4"><input type="file" name="file2"></td>
								</tr>

								<!-- 
								<tr class="gym_insert_div_img">
									<td>대표이미지</td>
									<td class="img_td" colspan="3">
										<div>
											<div class="dropZone1">
												<p>이미지를 끌어오세요</p>
											</div>
											<div id="img_add_box">
												<div id="imgadd1"></div>
											</div>
										</div>
									</td>
								</tr>
								<tr class="gym_insert_div_img">
									<td>추가이미지</td>
									<td class="img_td" colspan="3">
										<div>
											<div class="dropZone2">
												<p>이미지를 끌어오세요</p>
											</div>
											<div id="img_add_box">
												<div id="imgadd2"></div>
											</div>
										</div>
									</td>
								</tr>
								 -->
							</table>


							<div class="gym_regi_btn_site">
								<button class="gym_regi_btn" type="submit">체육관 등록</button>
							</div>

						</form>
						<!-- //form -->
					</div>
					<!--//gym_regi_form-->
					<div id="space"></div>
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

	
	//등록 제한요건 걸기
	$(".gym_regi_btn").on("click", function() {

		//주종목 체크여부 (편의시설은 없어도 돼서 제외)
		var gym_event = $("input:radio[name='gym_event']").is(":checked");
		if (gym_event == false) {
			alert("주종목을 선택해주세요.");
			return false;
		}

		//체육관이름
		var gym_name = $("[name='gym_name']").val();
		if (gym_name == null || gym_name == "") {
			alert("체육관이름을 입력해주세요.");
			return false;
		}

		//체육관주소1
		var gym_address = $("[name='roadaddress']").val();
		if (gym_address == null || gym_address == "") {
			alert("체육관주소를 입력해주세요.");
			return false;
		}
		
		//체육관주소2
		var gym_address = $("[name='addressdetail']").val();
		if (gym_address == null || gym_address == "") {
			alert("체육관주소를 입력해주세요.");
			return false;
		}

		//체육관 전화번호
		var gym_ph = $("[name='gym_ph']").val();
		if (gym_ph == null || gym_ph == "") {
			alert("체육관 전화번호를 입력해주세요.");
			return false;
		}

		//체육관 운영시간
		var gym_time = $("[name='gym_time']").val();
		if (gym_time == null || gym_time == "") {
			alert("체육관 운영시간을 입력해주세요.");
			return false;
		}

		//체육관 공지사항
		var gym_notice = $("[name='gym_notice']").val();
		if (gym_notice == null || gym_notice == "") {
			alert("체육관 공지사항을 입력해주세요.");
			return false;
		}

		var file1 = $("input[name=file1]").val();
		//파일 선택 필수
		if (file1 == null || file1 == "") {
			alert("체육관 대표사진을 등록해주세요.");
			return false;
		}

		var file2 = $("input[name=file2]").val();
		//파일 선택 필수
		if (file2 == null || file2 == "") {
			alert("체육관 추가사진을 등록해주세요.");
			return false;
		}

	});
	

	///////////////////////////////////////////

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
	
	/*
	//입력 제한요건 (if문 아니고 배열에 넣어서 체크함)
	$(".gym_regi_btn").on("click", function() {

		var name = $("[name='book_sell_name']").val();

		var comName = $("[name='book_com_name']").val();

		var no = $("[name='book_business_no']").val();

		var roadAddress = $("[name='roadAddress']").val();

		var addressdetail = $("[name='addressdetail']").val();

		var bank = $("[name='book_sell_bank']").val();

		var acc = $("[name='book_sell_account']").val();

		var formArr = new Array(name, comName, no, roadAddress,
				addressdetail, bank, acc);

		for (var i = 0; i < formArr.length; i++) {
			if (!formArr[i]) {
				alert("모든 정보를 입력해 주세요");
				return false;
			};
		}

		return true;

	});
	*/
			
</script>

</html>