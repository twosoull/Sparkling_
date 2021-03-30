<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스파클링</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/store.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
</head>

<body>
    
<div class="wrap">
    <div class="container">
        
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	    <!--//header//-->

    </div><!--//container-->
</div><!--//wrap-->


<!--내용-->
<div class="wrap">
    <div class="container">

        <!--상단여백-->
        <div class="searching_item"><!----></div>

        <!--결제완료 체크 이미지-->
        <div class="pay_cp">
            <div class="ico_cp">
                <img src="${pageContext.request.contextPath}/assets/image/store_img/check-icon.png" class="ico_check"> 
            </div>
        </div>

        <!--주문 완료 메세지-->
        <div class="pay_tit">
            <strong>
                <span>주문이 완료되었습니다.</span>
            </strong>
        </div>

        <!--버튼-->
        <div class="btn_area">
            <div class="btn_pay">
                <button type="submit"><a href="${pageContext.request.contextPath}/mypage/buylist" style="font-size: 16px; color: #ffffff;">구매내역 보기</a></button>
            </div> 
            <div class="btn_pay">
                <button type="submit"><a href="${pageContext.request.contextPath}/store/list" style="font-size: 16px; color: #ffffff;">스토어 홈 가기</a></button>
            </div>    
        </div> 
    </div><!--//container-->
</div><!--//wrap-->



<div class="wrap">
    <div class="container">
        <!--추천상품 리스트-->
        <div class="goods-add-product">
            <h3 class="goods-add-product-title">체육관 갈 준비는 다 되셨나요? ${prodList[0].event_cate} 관련 상품 보러가기</h3>

            <div class="goods-add-product-wrapper __slide-wrapper" data-slide-item="5">
                <button class="goods-add-product-move goods-add-product-move-left __slide-go-left">왼쪽으로 슬라이드 이동</button>
                <button class="goods-add-product-move goods-add-product-move-right __slide-go-right">오른쪽으로 슬라이드 이동</button>
                <div class="goods-add-product-list-wrapper" style="height:320px">
                    <ul class="goods-add-product-list __slide-mover">
                    
                    
                    <!-- 저장값이 1개밖에 없어서 임시로 [0]통일 -->
                        <li class="goods-add-product-item __slide-item">
                            <div class="goods-add-product-item-figure">
                            <a href="" target="_blank">
                                <img src="${pageContext.request.contextPath}/upload/${prodList[0].prod_img_savename}" class="goods-add-product-item-image">
                            </a>
                            </div>
                            <div class="goods-add-product-item-content">
                                <div class="goods-add-product-item-content-wrapper">
                                    <p class="goods-add-product-item-name">[${prodList[0].prod_brand}] ${prodList[0].prod_name}</p>
                                    <p class="goods-add-product-item-price"><fmt:formatNumber value="${prodList[0].prod_price}" pattern="#,###"/>원</p>
                                </div>
                            </div>
                            <input type="hidden" name="prod_no" value="${prodList[0].prod_no}">
                        </li>
                        <li class="goods-add-product-item __slide-item">
                            <div class="goods-add-product-item-figure">
                            <a href="" target="_blank">
                                <img src="${pageContext.request.contextPath}/upload/${prodList[1].prod_img_savename}" class="goods-add-product-item-image">
                            </a>
                            </div>
                            <div class="goods-add-product-item-content">
                                <div class="goods-add-product-item-content-wrapper">
                                    <p class="goods-add-product-item-name">[${prodList[1].prod_brand}] ${prodList[1].prod_name}</p>
                                    <p class="goods-add-product-item-price"><fmt:formatNumber value="${prodList[1].prod_price}" pattern="#,###"/>원</p>
                                </div>
                            </div>
                            <input type="hidden" name="prod_no" value="${prodList[1].prod_no}">
                        </li>
                        <li class="goods-add-product-item __slide-item">
                            <div class="goods-add-product-item-figure">
                            <a href="" target="_blank">
                                <img src="${pageContext.request.contextPath}/upload/${prodList[2].prod_img_savename}" class="goods-add-product-item-image">
                            </a>
                            </div>
                            <div class="goods-add-product-item-content">
                                <div class="goods-add-product-item-content-wrapper">
                                    <p class="goods-add-product-item-name">[${prodList[2].prod_brand}] ${prodList[2].prod_name}</p>
                                    <p class="goods-add-product-item-price"><fmt:formatNumber value="${prodList[2].prod_price}" pattern="#,###"/>원</p>
                                </div>
                            </div>
                            <input type="hidden" name="prod_no" value="${prodList[2].prod_no}">
                        </li>
                        <li class="goods-add-product-item __slide-item">
                            <div class="goods-add-product-item-figure">
                            <a href="" target="_blank">
                                <img src="${pageContext.request.contextPath}/upload/${prodList[3].prod_img_savename}" class="goods-add-product-item-image">
                            </a>
                            </div>
                            <div class="goods-add-product-item-content">
                                <div class="goods-add-product-item-content-wrapper">
                                    <p class="goods-add-product-item-name">[${prodList[3].prod_brand}] ${prodList[3].prod_name}</p>
                                    <p class="goods-add-product-item-price"><fmt:formatNumber value="${prodList[3].prod_price}" pattern="#,###"/>원</p>
                                </div>
                            </div>
                            <input type="hidden" name="prod_no" value="${prodList[3].prod_no}">
                        </li> 
                       
                                     
                    </ul>
                </div>
            </div><!--goods-add-product-wrapper __slide-wrapper-->
        </div> <!--goods-add-product-->

    </div><!--//container-->
</div><!--//wrap-->

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>


<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
<!--//footer//-->




</body>
</html>