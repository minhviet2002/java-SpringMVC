<%-- 
    Document   : register
    Created on : Aug 5, 2023, 3:05:02 PM
    Author     : admin
   <c:if test="${user.avatar != null}">
            <img src="${user.avatar}" width="120" />
        </c:if>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${user.iduser != null}"><h1 class="text-center text-info mt-1">SỬA THÔNG TIN</h1></c:when>
    <c:otherwise><h1 class="text-center text-info mt-1">ĐĂNG KÍ</h1></c:otherwise>
</c:choose>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>
<c:url value="/register" var="action" />
<form:form method="POST" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <form:hidden path="iduser" />
    <form:hidden path="avatar" />
    <div class="form-floating mb-3 mt-3">
        <label for="fname">Tên</label>
        <form:input type="text" class="form-control" path="fname" id="fname" placeholder="Tên" name="fname" />
        <form:errors path="fname" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="lname">Họ</label>
        <form:input type="text" class="form-control" path="lname" id="lname" placeholder="Họ" name="lname" />
        <form:errors path="lname" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="email">Email</label>
        <form:input type="email" class="form-control" path="email" id="email" placeholder="Email" name="email" />
        <form:errors path="email" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="username">Tên tài khoản</label>
        <form:input type="text" class="form-control" path="username" id="username" placeholder="Tên tài khoản" name="username" />
        <form:errors path="username" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="password">Mật khẩu</label>
        <form:input type="password" class="form-control" path="password" id="password" placeholder="Mật khẩu" name="password" />
        <form:errors path="password" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="confirmPassword">Xác nhận mật khẩu</label>
        <form:input type="password" class="form-control" path="confirmPassword" id="confirmPassword" placeholder="Xác nhận mật khẩu" name="confirmPassword" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="file">Ảnh đại diện</label>
        <form:input type="file" class="form-control" path="file" id="file" name="file" />
        <c:if test="${!empty user.avatar}">
            <img src="${user.avatar}" width="100" />
        </c:if>
    </div>
    <div class="form-group" >
        <c:choose>
            <c:when test="${user.iduser != null}"><input type="submit" value="Cập nhật" class="btn btn-info" /></c:when>
            <c:otherwise><input type="submit" value="Đăng kí" class="btn btn-info" /></c:otherwise>
        </c:choose>

    </div>
</form:form>