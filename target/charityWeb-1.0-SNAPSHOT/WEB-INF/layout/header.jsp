<%-- 
Document   : header
Created on : Aug 17, 2023, 1:42:40 PM
Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<!-- Button trigger modal -->


<nav class="navbar navbar-expand-lg navbar-light header">
    <div class="container">
        <div style="width: 148px;">
            <a class="navbar-brand ml-2" href="<c:url value="/" />" >
                <img style="width: 100%; height: 100%; padding-top: 8px;" src="<c:url value="/images/logo-white.png" />" alt="logo"/>
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <se:authorize access="hasRole('ROLE_ADMIN')" >
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Quản lí 
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<c:url value="/" />">Sản phẩm</a></li>
                            <li><a class="dropdown-item" href="<c:url value="/admin/categories" />">Danh mục</a></li>
                            <li><a class="dropdown-item" href="<c:url value="/admin/users" />">Người dùng</a></li>
                        </ul>
                    </se:authorize>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="<c:url value="/admin/statistics" />" >Thống kê</a>
                </li>

                <se:authorize access="hasRole('admin')">
                    <a href="<c:url value="/add" />" class="btn btn-info mt-1">Thêm sản phẩm</a>
                </se:authorize>
            </ul>
            <ul class="navbar-nav ml-auto">

                <c:if test="${pageContext.request.userPrincipal.name == null}" > 
                    <li class="nav-item">
                        <a class="nav-link text-white" href="<c:url value="/login" />" >Đăng nhập /</a>
                    </li>

                    <li class="nav-item" style="margin-left: -10px;">
                        <a class="nav-link text-white" href="<c:url value="/register" />" >Đăng ký</a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}" > 
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            ${pageContext.request.userPrincipal.name}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="<c:url value="/posts" />">Danh sách bài viết</a></li>
                                <se:authorize access="hasRole('ROLE_USER')" >
                                <li><a class="dropdown-item" href="<c:url value="/products/add" />">Thêm sản phẩm</a></li>
                                </se:authorize>
                            <li><a class="dropdown-item" href="<c:url value="/post/add" />">Thêm bài viết</a></li>
                                <se:authorize access="hasRole('ROLE_USER')" >
                                <li><a class="dropdown-item" href="<c:url value="/me" />">Trang cá nhân</a></li>
                                </se:authorize>
                            <li><a class="dropdown-item" href="<c:url value="/me/update" />">Sửa thông tin</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<c:url value="/logout" />" >Đăng xuất</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>