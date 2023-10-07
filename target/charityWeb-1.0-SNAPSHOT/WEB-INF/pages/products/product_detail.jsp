<%-- 
    Document   : products
    Created on : Jul 22, 2023, 3:18:13 PM
    Author     : admin
<div class="form-floating mb-3 mt-3">
        <label for="endDate">Thời gian kết thúc</label>
        <form:input type="datetime-local" class="form-control" path="endDate" id="endDate" name="endDate"  />
    </div>

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
        <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
    </ol>
</nav>



<div class="row">
    <div class="col-4">
        <div class="product_gallery">
            <div class="product_image">
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
    <div class="col-5">
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
                    <div class="col-6">
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
                    <span>Giá hiện tại:</span>
                </strong>
            </div>
            <div class="product_detail__box_body">
                <div class="row">
                    <div class="col-6">
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
                <se:authorize access="hasRole('ROLE_USER')" >
                    <c:if test="${pageContext.request.userPrincipal.name != null}" > 
                        <div id="auctionBody">
                            <c:url value="/auction/add/${product.idproduct}" var="action" />
                            <form:form method="POST" action="${action}" modelAttribute="auction" enctype="multipart/form-data" >
                                <form:errors path="*" element="div" cssClass="alert alert-danger"/>
                                <form:hidden path="idauction"/>
                                <div class="form-floating mb-3 mt-3">
                                    <label for="price">Giá đấu</label>
                                    <form:input type="text" class="form-control" path="price" id="price" placeholder="Giá đấu" name="price" />
                                    <form:errors path="price" element="price" cssClass="text text-danger" />
                                    <span class="text text-danger text-err">Nhập giá đấu cao hơn giá khởi điểm</span>
                                    <div class="form-floating mb-3 mt-3">
                                        <button type="submit" class="btn-auction btn btn-primary btn-block mb-3 font_size_14 text-uppercase btn-open-bid" style="display: block; margin-left: auto;">
                                            Đấu giá
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </c:if>

                </se:authorize>
                <c:if test="${pageContext.request.userPrincipal.name == null}" > 
                    <span class="text text-danger" style="font-style: italic">(Vui lòng đăng nhập để có chức năng đấu giá.)</span>
                </c:if>
            </div>
        </div>

        <hr>
    </div>
    <div class="col-3">
        <div class="product_detail__sidebar">
            <div class="product_detail__sidebar_block">
                <div class="store_info">
                    <div class="store_info__data">
                        <a class="store_info__avarta">
                            <c:choose>
                                <c:when test="${user.avatar != null}"> <img src="${user.avatar}" lazyloading=""></c:when>
                                <c:otherwise><img src="<c:url value="/images/avatar.jpg" />" lazyloading=""></c:otherwise>
                            </c:choose>
                        </a>
                        <div class="store_info__center">
                            <h4 class="store_info__name">
                                <c:if test="${pageContext.request.userPrincipal.name == product.userCreated.username}" > 
                                    <a href="<c:url value="/me" />">${product.userCreated.username}</a>
                                </c:if>
                                <c:if test="${pageContext.request.userPrincipal.name != product.userCreated.username}" > 
                                    <a href="<c:url value="/user/${product.userCreated.username}" />">${product.userCreated.username}</a>
                                </c:if>


                            </h4>
                            <div class="color_note">
                                <strong class="color_text_primary">${countReport} Đánh giá </strong> 
                                <c:if test="${countReport > 0}" > 
                                    <a href="<c:url value="/user/${product.userCreated.username}" />">(xem)</a>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="comments-list">
                <c:forEach items="${listAuction}" var="listAuc">
                    <div class="media">
                        <div class="media-body" style="font-size: 14px;">
                            Bạn đã cập nhật giá đấu mới là:
                            <br>
                            <fmt:formatNumber value="${listAuc.price}" pattern="#,### VNĐ" var="formattedAmount" />
                            <strong>${formattedAmount}</strong> - <small class="date">${listAuc.createdDate}</small>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>

</div>
<div class="product_detail__info">
    <h3 class="font_size_16 mb-3">Mô tả sản phẩm</h3>
    <div class="row">
        <div class="col-2 mb-3">
            <div class="mb-1">
                <strong class="color_note">Trạng thái</strong>
            </div>
            <div>
                <c:choose>
                    <c:when test="${product.status == true}">
                        Đã sử dụng
                    </c:when>
                    <c:otherwise>
                        Chưa sử dụng
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-2 mb-3">
            <div class="mb-1">
                <strong class="color_note">Thời gian bắt đầu</strong>
            </div>
            <div>
                ${product.createdDate}
            </div>
        </div>

        <div class="col-2 mb-3">
            <div class="mb-1">
                <strong class="color_note">Thời gian kết thúc</strong>
            </div>
            <div>
                ${product.endDate}
            </div>
        </div>
        <div class="col-2 mb-3">
            <div class="mb-1">
                <strong class="color_note">Giá khởi điểm</strong>
            </div>
            <div>
                <fmt:formatNumber value="${product.startprice}" pattern="#,### VNĐ" var="formattedAmount" />
                <strong>${formattedAmount}</strong>
            </div>
        </div>

        <div class="col-2 mb-3">
            <div class="mb-1">
                <strong class="color_note">Trả giá cao nhất</strong>
            </div>
            <div>
                <strong>
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
                </strong>
            </div>
        </div>

        <div class="col-2 mb-3">
            <div class="mb-1">
                <strong class="color_note">Auction ID</strong>
            </div>
            <div>
                ${product.idproduct}
            </div>
        </div>

    </div>
</div>
<script>
    const btnAuction = document.querySelector(".btn-auction");
    const inputPrice = document.querySelector("#price");
    const priceSt = document.querySelector(".priceSt");
    const textErr = document.querySelector(".text-err");
    const auction = document.querySelector("#auction");
    btnAuction.onclick = function (e) {
        e.preventDefault();
        // Kiểm tra điều kiện
        console.log(typeof inputPrice.value);
        if (isNaN(parseInt(inputPrice.value))) {
            textErr.style.display = "block";
            textErr.innerHTML = "Nhập giá trị là số.";
        } else if (parseInt(priceSt.getAttribute("data-price-st")) <= parseInt(inputPrice.value)) {
            textErr.style.display = "none";
            auction.submit();
        } else {
            // Thực hiện hành động khi điều kiện sai
            textErr.innerHTML = "Nhập giá đấu cao hơn giá khởi điểm.";
            textErr.style.display = "block";
        }
    };

</script>
<script>
    // Thời gian kết thúc
    var countdown = document.getElementById("countdown");
    var countnotice = document.getElementById("countnotice");
    var auctionBody = document.getElementById("auctionBody");
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
            auctionBody.classList.add("none");
        }
    }, 1000); // Cập nhật mỗi giây
</script>
