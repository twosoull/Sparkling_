<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="side_menu">
	<ul id="side_menu_mypage">
		<li>마이페이지</li>
	</ul>
	<c:if test="${authUser.book_type == 1}">
		<ul id="side_menu_booking">
			<li class="title"><span class="menu">예약판매관리</span></li>
			<li><a href="${pageContext.request.contextPath}/mypage/book/bookmanageform"><span class="menu">사업자계정
						관리</span><span class="arrow">></span></a></li>
			<li><a href="${pageContext.request.contextPath}/mypage/book/gym?no=${authUser.sell_no}&gymno=0"><span
					class="menu">체육관관리</span><span class="arrow">></span></a></li>
			<li><a href="${pageContext.request.contextPath}/mypage/book/bookmanage?no=${authUser.sell_no}&gymno=0"><span class="menu">대관관리</span><span
					class="arrow">></span></a></li>
			<li class="side_menu_last"><a href="${pageContext.request.contextPath}/user/profit?type=book"><span
					class="menu">수익관리</span><span class="arrow">></span></a></li>
		</ul>
	</c:if>
	<c:if test="${authUser.prod_type == 1}">
		<ul id="side_menu_delivery">
			<li class="title"><span class="menu">배송판매관리</span></li>
			<li><a href="${pageContext.request.contextPath}/mypage/prod"><span class="menu">상품관리</span><span
					class="arrow">></span></a></li>
			<li><a href="${pageContext.request.contextPath}/mypage/prod/delmanage"><span class="menu">배송관리</span><span
					class="arrow">></span></a></li>
			<li><a href="${pageContext.request.contextPath}/user/profit?type=prod"><span
					class="menu">수익관리</span><span class="arrow">></span></a></li>
			<li class="side_menu_last"><a href="${pageContext.request.contextPath}/mypage/prod/prodmanageform"><span class="menu">사업자계정
						관리</span><span class="arrow">></span></a></li>		
		</ul>
	</c:if>
	<c:if test="${authUser != null}">
		<ul id="side_menu_user">
			<li><a href="${pageContext.request.contextPath}/mypage/alarm"><span class="menu">알림</span><span
					class="arrow">></span></a></li>
			<li><a href="${pageContext.request.contextPath}/mypage/cart"><span class="menu">장바구니</span><span
					class="arrow">></span></a></li>
			<li><a href="${pageContext.request.contextPath}/mypage/buylist"><span class="menu">구매내역</span><span
					class="arrow">></span></a></li>
			<li><a href="${pageContext.request.contextPath }/sparring/mymatch?userno=${authUser.user_no}"><span class="menu">내 매치보기</span><span
					class="arrow">></span></a></li>		
			<li><a href="${pageContext.request.contextPath}/mypage/infoupdate"><span
					class="menu">회원정보관리</span><span class="arrow">></span></a></li>
			<li class="side_menu_last"><a href="../mypage_buy/matchinfo.html"><span
					class="menu">매칭프로필관리</span><span class="arrow">></span></a></li>
		</ul>
	</c:if>
	
		
	<c:if test="${authUser.book_type == 0 || authUser.prod_type == 0}">
		<ul id="side_menu_user">
			<li class="side_menu_last"><a
				href="${pageContext.request.contextPath}/user/notice"><span
					class="menu">판매자등록</span><span class="arrow">></span></a></li>
		</ul>
	</c:if>
</div>
<!--//side_menu//-->