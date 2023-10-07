<%-- 
    Document   : categories
    Created on : Aug 19, 2023, 6:46:33 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
    <c:when test="${categories.idcategory != null}"><h1 class="text-center text-info mt-1">CẬP NHẬT DANH MỤC</h1></c:when>
    <c:otherwise><h1 class="text-center text-info mt-1">THÊM DANH MỤC</h1></c:otherwise>
</c:choose>
<c:url value="/admin/categories/add" var="action" />
<form:form method="POST" action="${action}" modelAttribute="categories" enctype="multipart/form-data" >
    <form:errors path="*" element="div" cssClass="text text-danger" />
    <form:hidden path="idcategory" />
    <form:hidden path="image" />
    <div class="form-floating mb-3 mt-3">
        <label for="name">Tên danh mục</label>
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên danh mục" name="name" />
        <form:errors path="name" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="searchTerm">Từ khóa danh mục</label>
        <form:input type="text" class="form-control" path="searchTerm" id="searchTerm" placeholder="Từ khóa danh mục" name="searchTerm" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="description">Mô tả</label>
        <form:input type="text" class="form-control" path="description" id="description" placeholder="Mô tả" name="description" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="file">Ảnh danh mục</label>
        <form:input type="file" class="form-control" path="file" id="file"  />

        <c:if test="${categories.image != null}">
            <img src="${categories.image}" width="120" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${categories.idcategory != null}">Cập nhật danh mục</c:when>
                <c:otherwise>Thêm danh mục</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
