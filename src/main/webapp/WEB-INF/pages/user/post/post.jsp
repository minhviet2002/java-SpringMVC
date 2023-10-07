<%-- 
    Document   : post
    Created on : Sep 7, 2023, 5:52:45 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>

<c:choose>
    <c:when test="${!empty post.idpost}"><h1 class="text-center text-info mt-1">CẬP NHẬT BÀI VIẾT</h1></c:when>
    <c:otherwise><h1 class="text-center text-info mt-1">THÊM BÀI VIẾT</h1></c:otherwise>
</c:choose>
<c:url value="/post/add" var="action" />
<form:form method="POST" action="${action}" modelAttribute="post" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <form:hidden path="idpost" />
    <form:hidden path="image" />
    <div class="form-floating mb-3 mt-3">
        <label for="content">Nội dung</label>
        <form:textarea id="content" rows="5" name="content" cssClass="w-100 form-control" path="content" placeholder="Nội dung"></form:textarea>
        <form:errors path="content" element="content" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="name">Hastag</label>
        <form:input type="text" class="form-control" path="hastag" id="hastag" placeholder="hastag" name="hastag" />
        <form:errors path="hastag" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="file">Ảnh bài đăng</label>
        <form:input type="file" class="form-control" path="file" id="file"  />
        <c:if test="${post.image != null}">
            <img src="${post.image}" width="120" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3" style="width: 100%;text-align: end;">
        <button type="submit" class="commentBox_cmt">
            <c:choose>
                <c:when test="${post.idpost != null}">Cập nhật bài viết</c:when>
                <c:otherwise>Thêm bài viết</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>