<%-- 
    Document   : personal
    Created on : Aug 26, 2023, 6:21:34 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Button trigger modal -->


<section
    class="index-module_grid__1q71E index-module_wide__3c1pI"
    style="max-width: 1100px; padding-top: 46px;"
    >
    <div
        class="Profile_banner__tdS71"
        src=""
        style="
        background-image: url('<c:url value="/images/imagePer.png" />');
        position: relative;
        "
        >
        <div class="Profile_user__iDkf1">
            <div class="Profile_user-avatar__y8fSV">
                <div class="FallbackAvatar_avatar__gmj3S" style="--font-size: 17.2px">
                    <img
                        class="avatar"
                        <c:choose>
                            <c:when test="${!empty user.avatar}">src="${user.avatar}"</c:when>
                            <c:otherwise>src="<c:url value="/images/avatar.jpg" />"</c:otherwise>
                        </c:choose>
                        alt="${user.username}"
                        />
                </div>
            </div>
            <div class="Profile_user-name__xIJlY"><span>${user.fname} ${user.lname}</span></div>
        </div>
        <c:if test="${user.username != pageContext.request.userPrincipal.name}">
            <div style="display: flex;
                 flex: 1;
                 justify-content: end;
                 position: relative;
                 top: 50px;
                 ">
                <div class="btnOptionPer">
                    <i class="fa-solid fa-ellipsis" style="font-size: 30px;"></i>
                </div>
                <ul class="optionListPer rounded shadow-lg">
                    <li class="report-btn dropdown-item">Báo cáo</li>
                </ul>
                <div class="popup rounded shadow-lg" style="height: 35vh;">
                    <c:url value="/user/report/${user.username}" var="action" />
                    <form:form method="POST" action="${action}" modelAttribute="report" cssClass="p-3">
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
                        <form:hidden path="idreasonReport" />
                        <div class="form-floating mb-3 mt-3">
                            <label for="idreason" class="form-label">Lí do</label>
                            <form:select class="form-select w-100" id="idreason" name="idreason" path="idreason">
                                <c:forEach items="${reasons}" var="r">
                                    <option value="${r.idreason}">${r.name}</option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="form-floating mb-3 mt-3">
                            <label for="content">Nội dung</label>
                            <form:input type="text" class="form-control" path="content" id="content" placeholder="Nội dung báo cáo" name="content" />
                        </div>
                        <div class="form-floating mb-3 mt-3">
                            <button type="submit" class="btn btn-info">
                                Báo cáo
                            </button>
                            <div type="submit" class="btn btn-danger btn-cancel">
                                Hủy
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </c:if>

    </div>
    <div class="Profile_container__BVoU6">
        <section class="row">
            <section
                class="col-5"
                >
                <c:if test="${user.username == pageContext.request.userPrincipal.name}">
                    <div class="nav-item w-100" style="text-align: end;">
                        <a href="<c:url value="/products/add" />" class="btn btn-info mb-2 w-100">Thêm sản phẩm</a>
                    </div>
                </c:if>
                <div class="Box_wrapper__uAKHJ">
                    <h4 class="Box_title__kFB9-">Các sản phẩm đã đăng</h4>
                    <div>
                        <c:choose>
                            <c:when test="${!empty products}">
                                <c:forEach items="${products}" var="product">
                                    <div class="Profile_inner__EK7zA">
                                        <a class="Profile_thumb__dY3wD" href="/courses/javascript-co-ban"
                                           ><img
                                                <c:choose>
                                                    <c:when test="${!empty product.image}">src="<c:url value="${product.image}" />"</c:when>
                                                    <c:otherwise>src="<c:url value="/images/cartnull.jpg" />"</c:otherwise>
                                                </c:choose>
                                                class="Profile_thumb-image__vz1Iq"
                                                alt="${product.name}"
                                                />
                                        </a>
                                        <div class="info">
                                            <h3 class="Profile_info-title__nwecV">
                                                <c:if test="${pageContext.request.userPrincipal.name == product.userCreated.username}" > 
                                                    <a href="<c:url value="/me/product/${product.idproduct}" />">${product.name}</a>
                                                </c:if>
                                                <c:if test="${pageContext.request.userPrincipal.name != product.userCreated.username}" > 
                                                    <a href="<c:url value="/products/${product.idproduct}" />">${product.name}</a>
                                                </c:if>

                                            </h3>
                                            <p class="Profile_info-desc__JTJYr date">
                                                ${product.createdDate}
                                            </p>
                                            <hr
                                                <p class="Profile_info-desc__JTJYr">
                                            ${product.description}
                                            </p> 

                                        </div>
                                        <c:if test="${pageContext.request.userPrincipal.name == product.userCreated.username}" > 


                                            <div style="display: flex;
                                                 flex: 1;
                                                 justify-content: end;
                                                 position: relative;
                                                 ">
                                                <div class="btnOptions">
                                                    <i class="fa-solid fa-ellipsis"></i>
                                                </div>
                                                <ul class="optionList rounded shadow-lg">
                                                    <li><a class="dropdown-item "  href="<c:url value="/products/update/${product.idproduct}" />">Sửa</a></li>
                                                    <li>
                                                        <c:url value="/products/delete/${product.idproduct}" var="action_del" />
                                                        <form:form method="POST" action="${action_del}"  >
                                                            <button type="submit" class="dropdown-item"
                                                                    onclick="return confirm('Bạn muốn xóa sản phẩm này không?')">Xóa
                                                            </button>
                                                        </form:form>
                                                    </li>
                                                </ul>
                                            </div>
                                        </c:if>
                                    </div> 

                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="Profile_no-result__O7P-W">
                                    Chưa có hoạt động 
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="content-left">
                    <div class="Box_wrapper__uAKHJ">
                        <h4 class="Box_title__kFB9-">Hoạt động</h4>
                        <div>
                            <c:choose>
                                <c:when test="${!empty listAuction}">
                                    <c:forEach items="${listAuction}" var="listAuc">
                                        <div class="media">
                                            <div class="media-body" style="font-size: 14px;">
                                                Đấu giá sản phẩm <a href="<c:url value="/products/${listAuc.idproduct.idproduct}" />" />${listAuc.idproduct.name}</a>
                                                <br
                                                    <fmt:formatNumber value="${listAuc.price}" pattern="#,### VNĐ" var="formattedAmount" />
                                                    <strong>${formattedAmount}</strong> - <small class="date">${listAuc.createdDate}</small>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="Profile_no-result__O7P-W">
                                        Chưa có hoạt động 
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
                <div class="content-left">
                    <div class="Box_wrapper__uAKHJ">
                        <h4 class="Box_title__kFB9-">Đánh giá</h4>
                        <div>
                            <c:choose>
                                <c:when test="${!empty listReport}">
                                    <c:forEach items="${listReport}" var="l">
                                        <div class="media">
                                            <div class="media-body" style="font-size: 14px;">
                                                <div class="store_info">
                                                    <div class="store_info__data align-items-center">
                                                        <a class="store_info__avarta">
                                                            <c:choose>
                                                                <c:when test="${l.iduser.avatar != null}"> <img src="${l.iduser.avatar}" lazyloading=""></c:when>
                                                                <c:otherwise><img src="<c:url value="/images/avatar.jpg" />" lazyloading=""></c:otherwise>
                                                            </c:choose>

                                                        </a>
                                                        <div class="store_info__center ">
                                                            <h4 class="store_info__name">
                                                                <c:if test="${pageContext.request.userPrincipal.name == l.iduser.username}" > 
                                                                    <a href="<c:url value="/me" />">${l.iduser.username}</a> - ${l.idreason.name}
                                                                </c:if>
                                                                <c:if test="${pageContext.request.userPrincipal.name != l.iduser.username}" > 
                                                                    <a href="<c:url value="/user/${l.iduser.username}" />">${l.iduser.username}  </a> - ${l.idreason.name}
                                                                </c:if>

                                                            </h4>
                                                            <div class="color_note">
                                                                <strong class="color_text_primary">${countReport}${l.content}</strong> 
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="Profile_no-result__O7P-W">
                                        Chưa có đánh giá nào
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </section>
            <section
                class="col-7"
                >
                <div class="posts" style="display: flex;
                     align-items: start;
                     flex-direction: column;
                     ">
                    <c:if test="${user.username == pageContext.request.userPrincipal.name}">
                        <div class="nav-item w-100" style="text-align: end;">
                            <a href="<c:url value="/post/add" />" class="btn btn-info mb-2 w-100">Thêm bài viết</a>
                        </div>
                    </c:if>
                    <c:choose>
                        <c:when test="${!empty posts}"> 
                            <c:forEach items="${posts}" var="p">
                                <div class="post-item">
                                    <div class="post-head">
                                        <div class="store_info">
                                            <div class="store_info__data">
                                                <a class="store_info__avarta">
                                                    <c:choose>
                                                        <c:when test="${p.iduser.avatar != null}"> <img src="${p.iduser.avatar}" lazyloading=""></c:when>
                                                        <c:otherwise><img src="<c:url value="/images/avatar.jpg" />" lazyloading=""></c:otherwise>
                                                    </c:choose>

                                                </a>
                                                <div class="store_info__center">
                                                    <div class="name_head">
                                                        <h4 class="store_info__name">
                                                            <c:if test="${pageContext.request.userPrincipal.name == p.iduser.username}" > 
                                                                <a href="<c:url value="/me" />">${p.iduser.username}</a>
                                                            </c:if>
                                                            <c:if test="${pageContext.request.userPrincipal.name != p.iduser.username}" > 
                                                                <a href="<c:url value="/user/${p.iduser.username}" />">${p.iduser.username}</a>
                                                            </c:if>
                                                        </h4>
                                                        <c:if test="${pageContext.request.userPrincipal.name == p.iduser.username}" > 
                                                            <div class="btnOptionP">
                                                                <i class="fa-solid fa-ellipsis" style="font-size: 18px; padding-top: 2px;"></i>
                                                                <ul class="optionListP rounded shadow-lg">
                                                                    <li><a class="dropdown-item "  href="<c:url value="/posts/update/${p.idpost}" />">Sửa</a></li>
                                                                    <li>
                                                                        <c:url value="/me/posts/delete/${p.idpost}" var="action_del" />
                                                                        <form:form method="POST" action="${action_del}"  >
                                                                            <button type="submit" class="dropdown-item"
                                                                                    onclick="return confirm('Bạn muốn xóa bài viết này không?')">Xóa
                                                                            </button>
                                                                        </form:form>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                    <c:if test="${!empty p.createdDate}" > 
                                                        <div class="color_note date" style="text-align: justify; margin-top: -3px;">
                                                            ${p.createdDate}
                                                        </div> 
                                                    </c:if>
                                                    <div class="color_note line-hide" style="text-align: justify; padding-top: 2px;">
                                                        ${p.content}
                                                    </div>
                                                    <div class="seemore" style="display: flex; align-items: center;">
                                                        <a href="">...Xem thêm</a>
                                                    </div>
                                                    <div class="color_note" style="text-align: justify;">
                                                        <a href="<c:url value="/hastags/${p.hastag}" />">#${p.hastag}</a>
                                                    </div>
                                                    <div class="product_gallery">
                                                        <div class="post_image">
                                                            <c:choose>
                                                                <c:when test="${!empty p.image }">
                                                                    <img src="${p.image}" />
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                    </div>
                                                    <div class="Reaction_wrapper__Jjsxl">
                                                        <div class="Reaction_btnReact__O0zy5" title="Nhấn để thích bài này"><svg aria-hidden="true" focusable="false"
                                                                                                                                 data-prefix="far" data-icon="heart" class="svg-inline--fa fa-heart " role="img" xmlns="http://www.w3.org/2000/svg"
                                                                                                                                 viewBox="0 0 512 512">
                                                            <path fill="currentColor"
                                                                  d="M244 84L255.1 96L267.1 84.02C300.6 51.37 347 36.51 392.6 44.1C461.5 55.58 512 115.2 512 185.1V190.9C512 232.4 494.8 272.1 464.4 300.4L283.7 469.1C276.2 476.1 266.3 480 256 480C245.7 480 235.8 476.1 228.3 469.1L47.59 300.4C17.23 272.1 0 232.4 0 190.9V185.1C0 115.2 50.52 55.58 119.4 44.1C164.1 36.51 211.4 51.37 244 84C243.1 84 244 84.01 244 84L244 84zM255.1 163.9L210.1 117.1C188.4 96.28 157.6 86.4 127.3 91.44C81.55 99.07 48 138.7 48 185.1V190.9C48 219.1 59.71 246.1 80.34 265.3L256 429.3L431.7 265.3C452.3 246.1 464 219.1 464 190.9V185.1C464 138.7 430.4 99.07 384.7 91.44C354.4 86.4 323.6 96.28 301.9 117.1L255.1 163.9z">
                                                            </path>
                                                            <c:set var="counterlike" value="0" /> 
                                                            <c:forEach items="${likes}" varStatus="st"  var="l">
                                                                <c:if test="${l.idpost.idpost == p.idpost}">
                                                                    <c:set var="counterlike" value="${counterlike + 1}" />
                                                                </c:if>
                                                            </c:forEach>
                                                            </svg><span>${counterlike}</span></div>
                                                        <div class="Reaction_btnReact__O0zy5"><svg aria-hidden="true" focusable="false" data-prefix="far" data-icon="comment"
                                                                                                   class="svg-inline--fa fa-comment " role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                                                            <path fill="currentColor"
                                                                  d="M256 32C114.6 32 .0272 125.1 .0272 240c0 47.63 19.91 91.25 52.91 126.2c-14.88 39.5-45.87 72.88-46.37 73.25c-6.625 7-8.375 17.25-4.625 26C5.818 474.2 14.38 480 24 480c61.5 0 109.1-25.75 139.1-46.25C191.1 442.8 223.3 448 256 448c141.4 0 255.1-93.13 255.1-208S397.4 32 256 32zM256.1 400c-26.75 0-53.12-4.125-78.38-12.12l-22.75-7.125l-19.5 13.75c-14.25 10.12-33.88 21.38-57.5 29c7.375-12.12 14.37-25.75 19.88-40.25l10.62-28l-20.62-21.87C69.82 314.1 48.07 282.2 48.07 240c0-88.25 93.25-160 208-160s208 71.75 208 160S370.8 400 256.1 400z">
                                                            </path>
                                                            <c:set var="counter" value="0" /> 
                                                            <c:forEach items="${comments}" varStatus="st"  var="c">
                                                                <c:if test="${c.idpost.idpost == p.idpost}">
                                                                    <c:set var="counter" value="${counter + 1}" />
                                                                </c:if>
                                                            </c:forEach>
                                                            </svg><span>${counter}</span></div>
                                                    </div>
                                                    <div class="commentBody">
                                                        <c:forEach items="${comments}" varStatus="st"  var="c">
                                                            <c:if test="${c.idpost.idpost == p.idpost}">
                                                                <div class="store_info">
                                                                    <div class="store_info__data">
                                                                        <div class="post__avarta">
                                                                            <c:choose>
                                                                                <c:when test="${!empty c.iduser.avatar }"> <img src="${c.iduser.avatar}" lazyloading=""></c:when>
                                                                                <c:otherwise><img src="<c:url value="/images/avatar.jpg" />" lazyloading=""></c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <div class="store_info__center">
                                                                            <div class="name_head">
                                                                                <h4 class="store_info__name">
                                                                                    <c:if test="${pageContext.request.userPrincipal.name == c.iduser.username}" > 
                                                                                        <a href="<c:url value="/me" />">${c.iduser.username}</a>
                                                                                    </c:if>
                                                                                    <c:if test="${pageContext.request.userPrincipal.name != c.iduser.username}" > 
                                                                                        <a href="<c:url value="/user/${c.iduser.username}" />">${c.iduser.username}</a>
                                                                                    </c:if>
                                                                                </h4>

                                                                                <c:if test="${pageContext.request.userPrincipal.name == c.iduser.username}" > 
                                                                                    <div class="btnOptionP">
                                                                                        <i class="fa-solid fa-ellipsis" style="font-size: 18px; padding-right: 25px;"></i>
                                                                                        <ul class="optionListP rounded shadow-lg">
                                                                                            <li>
                                                                                                <c:url value="/me/comment/delete/${c.idcomment}" var="action_del" />
                                                                                                <form:form method="POST" action="${action_del}"  >
                                                                                                    <button type="submit" class="dropdown-item"
                                                                                                            onclick="return confirm('Bạn muốn xóa bình luận này không?')">Xóa
                                                                                                    </button>
                                                                                                </form:form>
                                                                                            </li>
                                                                                        </ul>
                                                                                    </div>
                                                                                </c:if>
                                                                            </div>

                                                                            <div class="color_note">
                                                                                <strong class="color_text_primary">${c.comment}</strong>
                                                                                - <span class="date">${c.createdDate}</span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="post-new">
                                                        <c:url value="/me/comment/add/${p.idpost}" var="action" /> 
                                                        <div class="form-cmt">
                                                            <div class="post__avarta">
                                                                <c:choose>
                                                                    <c:when test="${user.avatar != null}"> <img src="${user.avatar}" lazyloading=""></c:when>
                                                                    <c:otherwise><img src="<c:url value="/images/avatar.jpg" />" lazyloading=""></c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <form:form method="POST" action="${action}" cssClass="commentBox_commentWrapper" modelAttribute="comment">
                                                                <form:hidden path="idcomment" />
                                                                <div class="form-floating mb-3 mt-3 w-100">
                                                                    <form:textarea rows="1" cssClass="text_text commentBox_commentInput" path="comment" id="comment" name="comment"></form:textarea>
                                                                    </div>
                                                                    <div class="commentBox_actionWrapper">
                                                                        <button type="submit" class="btn_cmt">Thêm</button>
                                                                    </div>
                                                            </form:form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>Chưa có bài đăng nào</c:otherwise>
                    </c:choose>

                </div>
            </section>
        </section>
    </div>
</section>
<script>
    var btnOptions = document.querySelectorAll(".btnOptions");
    var optionList = document.querySelectorAll(".optionList");
    var optionListPer = document.querySelector(".optionListPer");
    var btnOptionPer = document.querySelector(".btnOptionPer");
    btnOptions.forEach((btnOption, index) => {
        btnOption.onclick = function () {
            if (document.querySelector(".optionList.active")) {
                (document.querySelector(".optionList.active")).classList.remove("active");
                optionList[index].classList.add("active");

            } else {
                optionList[index].classList.add("active");
            }


        }
    })



    btnOptionPer.onclick = function () {
        if (document.querySelector(".optionListPer.active")) {
            (document.querySelector(".optionListPer.active")).classList.remove("active");
        } else {
            optionListPer.classList.add("active");
        }


    }



    var reportBtn = document.querySelector(".report-btn");
    var popup = document.querySelector(".popup");
    var cancelBtn = document.querySelector(".btn-cancel");
    reportBtn.onclick = function () {
        if (document.querySelector(".popup.active")) {
            (document.querySelector(".popup.active")).classList.remove("active");
            popup.classList.add("active");

        } else {
            popup.classList.add("active");
        }


    }
    cancelBtn.onclick = function () {
        if (document.querySelector(".popup.active")) {
            (document.querySelector(".popup.active")).classList.remove("active");
        }
    }
</script>

<script>
    var seemore = document.querySelectorAll(".seemore");
    var lineHide = document.querySelectorAll(".line-hide");
    seemore.forEach((item, index) => {
        item.onclick = function (e) {
            e.preventDefault();
            item.classList.add("none");
            lineHide[index].style.maxHeight = "none";
        }
    })

    var optionListP = document.querySelectorAll(".optionListP");
    var btnOptionP = document.querySelectorAll(".btnOptionP");

    btnOptionP.forEach((item, index) => {
        item.onclick = function ()
        {
            if (document.querySelector(".optionListP.active")) {
                (document.querySelector(".optionListP.active")).classList.remove("active");
            } else {
                optionListP[index].classList.add("active");
            }
        }
    });


    let formCmts = document.querySelectorAll(".commentBox_commentWrapper");
    let btnCmts = document.querySelectorAll(".btn_cmt");
    let cmts = document.querySelectorAll(".commentBox_commentInput");
    formCmts.forEach((item, index) => {
        let comment = item.querySelector(".commentBox_commentInput");
        let btn = item.querySelector(".btn_cmt");
        comment.addEventListener("input", function () {
            // Kiểm tra nếu giá trị trường textarea không rỗng
            if (comment.value.trim() !== "") {
                btn.style.boxShadow = "0 -4px 32px rgb(0 0 0 / 20%)";
                btn.style.backgroundColor = "#f27c08";
                btn.style.cursor = "pointer";
            } else {
                // Nếu giá trị trống, đặt lại màu nút submit
                btn.style.boxShadow = "";
                btn.style.backgroundColor = "#888";
                btn.style.cursor = "default";
            }
        });
        item.addEventListener("submit", function (event) {
            if (comment.value.trim() === "") {
                // Ngăn chặn việc gửi form
                event.preventDefault();
            }

        });
    })
</script>
