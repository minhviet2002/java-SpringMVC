<%-- 
    Document   : post
    Created on : Sep 3, 2023, 3:05:47 PM
    Author     : ASUS

--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<c:url value="/" />">Trang chủ</a></li>
        <li class="breadcrumb-item active" aria-current="page">Danh sách bài viết</li>
    </ol>
</nav>
<div class="post-new form-wrap">
    <c:url value="/post/add" var="action" />
    <form:form method="POST" action="${action}" cssClass="commentBox_commentWrapper" modelAttribute="post" enctype="multipart/form-data">
        <div class="form-body">
            <form:hidden path="idpost" />
            <form:hidden path="image" />
            <div class="form-floating mb-3 mt-3 w-100">
                <label for="content" class="text_text">Nội dung</label>
                <form:textarea id="content" cssStyle="border: 1px solid #ccc !important; min-height: 110px;" name="content" cssClass="text_text commentBox_commentInput rounded pl-2 form-contro" path="content"></form:textarea>
                </div>
                <div class="form-floating mb-3 mt-3 w-100">
                <form:input cssClass="text_text commentBox_commentInput rounded pl-2" type="text" class="form-control" path="hastag" id="hastag" placeholder="hastag" name="hastag" />
            </div>
            <div class="form-floating mb-3 mt-3 w-100">
                <form:input type="file" class="form-control rounded pl-2" path="file" id="file" name="file" />
            </div>
            <div style="width: 100%;text-align: end;">
                <button type="submit" class="commentBox_cmt">Đăng bài</button>
            </div>
        </div>
    </form:form>
</div>
<div class="posts" style="display: flex;
     align-items: start;
     flex-direction: column;
     ">
    <c:forEach items="${posts}" var="p">
        <div class="post-item col-8">
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
                                                <c:url value="/posts/delete/${p.idpost}" var="action_del" />
                                                <form:form method="POST" action="${action_del}"  >
                                                    <button type="submit" class="dropdown-item"
                                                            onclick="return confirm('Bạn muốn xóa bài viết này không?')">Xóa
                                                    </button>
                                                </form:form>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>
                                <c:if test="${pageContext.request.userPrincipal.name != p.iduser.username}" > 
                                    <div class="btnOptionP">
                                        <i class="fa-solid fa-ellipsis" style="font-size: 18px; padding-top: 2px;"></i>
                                        <ul class="optionListP rounded shadow-lg">
                                            <li><a class="dropdown-item "  href="<c:url value="user/${p.iduser.username}" />">Báo cáo</a></li>
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
                                                            <a href="/charityWeb/user/tanthanh">${c.iduser.username}</a>
                                                        </h4>
                                                        <c:if test="${pageContext.request.userPrincipal.name == c.iduser.username}" > 
                                                            <div class="btnOptionP">
                                                                <i class="fa-solid fa-ellipsis" style="font-size: 18px; padding-right: 25px;"></i>
                                                                <ul class="optionListP rounded shadow-lg">
                                                                    <li>
                                                                        <c:url value="/comment/delete/${c.idcomment}" var="action_del" />
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
                                <c:url value="/comment/add/${p.idpost}" var="action" /> 
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
</div>
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
