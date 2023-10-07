<%-- 
    Document   : index
    Created on : Jul 8, 2023, 3:08:58 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/" var="action" />

<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item" aria-current="page">Trang chủ</li>
    </ol>
</nav>
<section >
    <div class="row">
        <div class="col-sm-2 list">
            <aside id="sidebar" class="mb60">
                <div class="sidebar_container sidebar_category">
                    <div class="sidebar_header">
                        <div class="sidebar_title">Danh mục</div>
                    </div>

                    <div class="sidebar_body">
                        <div class="sidebar_accordion" id="sidebar_accordion">
                            <div class="sidebar_accordion__group">
                                <div class="sidebar_accordion__header">
                                    <a href="<c:url value="/" />" title="Tất cả">
                                        Tất cả
                                    </a>
                                </div>
                            </div>
                            <c:forEach items="${categories}" var="c">
                                <c:url value="/productsbycategory/${c.idcategory}" var="searchUrl">
                                </c:url>
                                <div class="sidebar_accordion__group">
                                    <div class="sidebar_accordion__header">
                                        <a href="${searchUrl}" title="${c.name}">
                                            ${c.name}
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </aside>
        </div>
        <div class="col-sm-10">
            <h1 class="text-center text-info mt-1">DANH SÁCH SẢN PHẨM </h1>
            <c:if test="${counter > 1}">
                <ul class="pagination mt-1">
                    <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                        <c:forEach begin="1" end="${counter}" var="i">
                            <c:url value="/" var="pageUrl">
                                <c:param name="page" value="${i}" />
                            </c:url>
                        <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                        </c:forEach>
                </ul>
            </c:if>
            <c:if test="${counterCate > 1}">
                <ul class="pagination mt-1">
                    <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                        <c:forEach begin="1" end="${counterCate}" var="i">
                            <c:url value="" var="pageUrl">
                                <c:param name="page" value="${i}" />
                            </c:url>
                        <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                        </c:forEach>
                </ul>
            </c:if>
            <c:choose>
                <c:when test="${empty products}">
                    <div><p>Danh sách sản phẩm trống. <a href="<c:url value="/products/add" />" class="">Thêm sản phẩm</a></p></div>
                </c:when>    
                <c:otherwise>
                    <div class="row">
                        <c:forEach items="${products}" var="p">
                            <div class="mb-4 pl-2 pr-2">
                                <div class="card" style="width: 18rem;">
                                    <a href="<c:url value="/products/${p.idproduct}" />"> <img 
                                            style="height: 250px;"
                                            <c:choose>
                                                <c:when test="${!empty p.image }">
                                                    src="${p.image}" 
                                                </c:when>
                                                <c:otherwise>
                                                    src="<c:url value="/images/cartnull.jpg" />" 
                                                </c:otherwise>
                                            </c:choose>   
                                            class="card-img-top" alt="${p.name}"></a>
                                    <div class="card-body">
                                        <h5 class="card-title"><a href="<c:url value="/products/${p.idproduct}" />"> ${p.name}</a></h5>
                                        <div class="product_item__countdown clearfix">
                                            <i class="far fa-clock"></i>
                                            <span translate="no" class="countdown" data-endtime="${p.endDate}">0d -0h 00' 00s</span>
                                        </div>
                                        <div class="product_item__countdown clearfix">
                                            <i class="fa-regular fa-circle-user"></i>
                                            <c:set var="counter" value="0" /> 
                                            <c:forEach items="${listAuction}" varStatus="st"  var="c">
                                                <c:if test="${c.idproduct.idproduct == p.idproduct}">
                                                    <c:set var="counter" value="${counter + 1}" />
                                                </c:if>
                                            </c:forEach>
                                            <span class="slot">${counter} Lượt đấu giá</span>
                                        </div>
                                        <div class="product_item__countdown clearfix">
                                            <c:choose>
                                                <c:when test="${p.status == true}">
                                                    <i class="fa-solid fa-check text-green"></i>
                                                    <span class="slot text-green">Đã sử dụng</span>

                                                </c:when>
                                                <c:otherwise>
                                                    <i class="fa-regular fa-circle-xmark text-danger"></i>
                                                    <span class="slot text-danger">Chưa sử dụng</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="product_item__by">
                                            Người bán
                                            <c:if test="${pageContext.request.userPrincipal.name == p.userCreated.username}" > 
                                                <a class="color_blue" href="<c:url value="/me" />">
                                                    ${p.userCreated.username}
                                                </a>
                                            </c:if>
                                            <c:if test="${pageContext.request.userPrincipal.name != p.userCreated.username}" > 
                                                <a class="color_blue" href="<c:url value="/user/${p.userCreated.username}" />">
                                                    ${p.userCreated.username}
                                                </a>
                                            </c:if>

                                        </div>
                                        <div class="product_item__price">
                                            <div class="price_group">
                                                <div class="price_converted ">
                                                    <span class="product_price_exchange text-danger" >
                                                        <fmt:formatNumber value="${p.startprice}" pattern="#,### VNĐ" var="formattedAmount" />
                                                        ${formattedAmount}
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</section>

<script>
    // Thời gian kết thúc

    var countdowns = document.querySelectorAll(".countdown");
    countdowns.forEach((item, index) => {
        var countdownInterval = setInterval(function () {
            let endTimeString = item.getAttribute("data-endtime");
            var endTime = new Date(endTimeString).getTime();

            // Cập nhật đồng hồ đếm ngược mỗi giây

            // Lấy thời gian hiện tại
            var currentTime = new Date().getTime();

            // Tính khoảng thời gian còn lại
            var timeRemaining = endTime - currentTime;

            // Tính số ngày, giờ, phút và giây còn lại
            var days = Math.floor(timeRemaining / (1000 * 60 * 60 * 24));
            var hours = Math.floor((timeRemaining % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((timeRemaining % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((timeRemaining % (1000 * 60)) / 1000);
            item.innerHTML = days + "d - " + hours + "h " + minutes + "' " + seconds + "s";

            // Kiểm tra nếu thời gian đã hết
            if (timeRemaining <= 0) {
                clearInterval(countdownInterval);
                item.innerHTML = "Hết thời gian";
                item.style.color = "red";
            }
        }, 1000);
    })


</script>