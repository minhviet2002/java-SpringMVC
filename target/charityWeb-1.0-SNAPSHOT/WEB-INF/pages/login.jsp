<%-- 
    Document   : login
    Created on : Aug 5, 2023, 3:05:02 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">ĐĂNG NHẬP</h1>
<c:if test="${param.error != null}">
    <div class="alert alert-danger">Đã có lỗi! Vui lòng đăng nhập lại.</div>
</c:if>
<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger">Quyền truy cập không cho phép.</div>
</c:if>
<c:url value="/login" var="action" />
<form method="post" action="${action}">
    <div class="form-floating mb-3 mt-3">
        <label for="name">Tên đăng nhập</label>
        <input type="text" class="form-control" id="name" placeholder="Tên đăng nhập" name="username">
    </div>

    <div class="form-floating mt-3 mb-3">
        <label for="pwd">Mật khẩu</label>
        <input type="password" class="form-control" id="pwd" placeholder="Mật khẩu" name="password">
    </div>
    <div class="form-floating mt-3 mb-3">
        <input type="submit" value="Đăng nhập" class="btn btn-danger" />
    </div>
</form>