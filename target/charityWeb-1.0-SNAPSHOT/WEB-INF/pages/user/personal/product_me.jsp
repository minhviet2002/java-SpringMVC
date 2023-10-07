<%-- 
    Document   : product
    Created on : Aug 28, 2023, 2:21:16 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<c:url value="/" />">Trang chủ</a></li>
        <li class="breadcrumb-item"><a href="<c:url value="/" />">Sản phẩm</a></li>
        <li class="breadcrumb-item active" aria-current="page"></li>
    </ol>
</nav>


<div class="row">
    <div class="col-3">
        <div class="product_gallery">
            <div class="product_image-m">
                <c:choose>
                    <c:when test="${!empty product.image }">
                        <img src="${product.image}" />
                    </c:when>
                    <c:otherwise>
                        <img src="<c:url value="/images/cartnull.jpg" />" />
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="col-4">
        <h1 class="product_detail__name">${product.name}</h1>
        <div class="row pb-3">
            <div class="col-12">
                <div class="product_detail__countdown">
                    <div id="countnotice" style="font-size: 27px;
                         display: none;
                         color: red;
                         font-weight: 700;
                         font-style: italic;
                         padding-right: 30px;
                         border-right: 1px solid #000;
                         ">Thời gian đã hết</div>
                    <div translate="no" class="main_countdown_js product_detail__countdown_clock" id="countdown" data-endtime="${product.endDate}">
                        <div class="number">
                            <div><strong id="day">0</strong></div>
                            <div><span>NGÀY</span></div>
                        </div>
                        <div class="space"><strong>:</strong></div>
                        <div class="number">
                            <div><strong id="hour">0</strong></div>
                            <div><span>GIỜ</span></div>
                        </div>
                        <div class="space"><strong>:</strong></div>
                        <div class="number">
                            <div><strong id="minute">0</strong></div>
                            <div><span>PHÚT</span></div>
                        </div>
                        <div class="space"><strong>:</strong></div>
                        <div class="number">
                            <div><strong id="second">0</strong></div>
                            <div><span>GIÂY</span></div>
                        </div>
                    </div>
                    <script>
                        // Thời gian kết thúc
                        var countdown = document.getElementById("countdown");
                        var countnotice = document.getElementById("countnotice");
                        var endTimeString = countdown.getAttribute("data-endtime");

                        var endTime = new Date(endTimeString).getTime();

                        // Cập nhật đồng hồ đếm ngược mỗi giây
                        var countdownInterval = setInterval(function () {
                            // Lấy thời gian hiện tại
                            var currentTime = new Date().getTime();

                            // Tính khoảng thời gian còn lại
                            var timeRemaining = endTime - currentTime;

                            // Tính số ngày, giờ, phút và giây còn lại
                            var days = Math.floor(timeRemaining / (1000 * 60 * 60 * 24));
                            var hours = Math.floor((timeRemaining % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                            var minutes = Math.floor((timeRemaining % (1000 * 60 * 60)) / (1000 * 60));
                            var seconds = Math.floor((timeRemaining % (1000 * 60)) / 1000);

                            // Hiển thị thời gian còn lại trên giao diện
                            var day = document.getElementById("day");
                            var hour = document.getElementById("hour");
                            var minute = document.getElementById("minute");
                            var second = document.getElementById("second");

                            day.innerHTML = days;
                            hour.innerHTML = hours;
                            minute.innerHTML = minutes;
                            second.innerHTML = seconds;

                            // Kiểm tra nếu thời gian đã hết
                            if (timeRemaining <= 0) {
                                clearInterval(countdownInterval);
                                countdown.classList.add("none");
                                countnotice.classList.add("active");

                            }
                        }, 1000); // Cập nhật mỗi giây
                    </script>


                    <div class="product_detail__countdown_history">
                        <div>
                            <strong class="font_size_20" data-refresh-bids="">${countAuction}</strong> <span class="color_note">lượt đấu giá</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="product_detail__box ">
            <div class="product_detail__box_title">
                <strong>
                    <span>Giá khởi điểm:</span>
                </strong>
            </div>
            <div class="product_detail__box_body">
                <div class="row">
                    <div class="col-12">
                        <div class="product_detail__box_price mb-3">
                            <span class="priceSt" style="font-size: 23px; font-weight: bold; color:#00194F !important;" data-price-st="${product.startprice}">
                                <fmt:formatNumber value="${product.startprice}" pattern="#,### VNĐ" var="formattedAmount" />
                                ${formattedAmount}
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="product_detail__box mb-3">
            <div class="product_detail__box_title">
                <strong>
                    <span>Giá cao nhất:</span>
                </strong>
            </div>
            <div class="product_detail__box_body">
                <div class="row">
                    <div class="col-12">
                        <div class="product_detail__box_price mb-3">
                            <span style="font-size: 23px; font-weight: bold; color:#00194F !important;">

                                <c:choose>
                                    <c:when test="${maxPriceAuction != 0}"> 
                                        <fmt:formatNumber value="${maxPriceAuction}" pattern="#,### VNĐ" var="formattedAmount" />
                                        ${formattedAmount}
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatNumber value="${product.startprice}" pattern="#,### VNĐ" var="formattedAmount" />
                                        ${formattedAmount}
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </div>
    <div class="col-5">
        <div class="content-left">
            <div class="Box_wrapper__uAKHJ">

                <h4 class="Box_title__kFB9-">Hoạt động đấu giá</h4>
                <c:if test="${!empty listAuction}" >
                    <div>
                        <c:forEach items="${listAuction}" var="listAuc">
                            <div class="media">
                                <div class="media-body row-custom" style="font-size: 14px;">
                                    <div>
                                        <a href="<c:url value="/user/${listAuc.iduser.username}" />">${listAuc.iduser.username}</a>
                                        đã đầu giá sản phẩm này
                                        <br>
                                        <fmt:formatNumber value="${listAuc.price}" pattern="#,### VNĐ" var="formattedAmount" />
                                        <strong>${formattedAmount}</strong> - <small class="date">${listAuc.createdDate}</small>
                                    </div>
                                    <c:if test="${empty product.endprice}" >
                                        <c:url value="/auctionActive/${listAuc.idauction}" var="action" />
                                        <form:form method="POST" action="${action}" enctype="multipart/form-data">
                                            <button type="submit" onclick="return confirm('Bạn muốn chọn giá này?')" class="btn btn-success">
                                                <i class="fa-solid fa-check"></i>
                                            </button>
                                        </form:form>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty listAuction}" >
                    Chưa có hoạt động nào.
                </c:if>
            </div>
        </div>
        <c:if test="${product.endprice != null}" >
            <div class="product_detail__box mb-3">
                <div class="product_detail__box_title">
                    <strong>
                        <span>Giá chiến thắng:</span>
                    </strong>
                </div>
                <div class="product_detail__box_body">
                    <div class="row">
                        <div class="col-12">
                            <div class="product_detail__box_price mb-3">
                                <span style="font-size: 23px; font-weight: bold; color:#00194F !important;">
                                    <fmt:formatNumber value="${product.endprice}" pattern="#,### VNĐ" var="formattedAmount" />
                                    ${formattedAmount}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty product.endprice}" >
            <c:forEach items="${listAuction}" var="listAuc">
                <c:if test="${listAuc.active == true}" >
                    <div class="product_detail__box mb-3">
                        <div class="product_detail__box_title">
                            <strong>
                                <span>Người chiến thắng:</span>
                            </strong>
                        </div>
                        <div class="product_detail__box_body">
                            <div class="row">
                                <div class="col-12">
                                    <div class="product_detail__box_price mb-3">
                                        <span style="font-size: 23px; font-weight: bold; color:#00194F !important;">
                                            <a href="<c:url value="/user/${listAuc.iduser.username}" />">
                                                ${listAuc.iduser.fname} ${listAuc.iduser.lname}(${listAuc.iduser.username})
                                            </a>

                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>

        </c:if>
    </div>
</div>