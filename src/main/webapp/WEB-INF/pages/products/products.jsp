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

<c:choose>
    <c:when test="${product.idproduct != null}"><h1 class="text-center text-info mt-1">CẬP NHẬT SẢN PHẨM</h1></c:when>
    <c:otherwise><h1 class="text-center text-info mt-1">THÊM SẢN PHẨM</h1></c:otherwise>
</c:choose>
<c:url value="/products/add" var="action" />
<form:form method="POST" action="${action}" modelAttribute="product" enctype="multipart/form-data" >

    <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <form:hidden path="idproduct" />
    <form:hidden path="image" />
    <div class="form-floating mb-3 mt-3">
        <label for="name">Tên sản phẩm</label>
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên sản phẩm" name="name" />
        <form:errors path="name" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="description">Mô tả</label>
        <form:input type="text" class="form-control" path="description" id="description" placeholder="Mô tả" name="description" />
        <form:errors path="description" element="p" cssClass="text text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="startprice">Giá khởi điểm</label>
        <form:input type="text" class="form-control" path="startprice" id="startprice" placeholder="Giá khởi điểm" name="startprice" />
        <form:errors path="startprice" element="p" cssClass="text text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="date">Ngày kết thúc đấu giá</label>
        <form:input type="date" class="form-control" path="date" id="date" placeholder="Ngày kết thúc đấu giá" name="date" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="categoryId" class="form-label">Danh mục sản phẩm</label>
        <form:select class="form-select w-100" id="categoryId" name="categoryId" path="categoryId">
            <c:forEach items="${categories}" var="c">
                <c:choose>
                    <c:when test="${c.idcategory == product.categoryId.idcategory}"><option value="${c.idcategory}" selected>${c.name}</option></c:when>
                    <c:otherwise><option value="${c.idcategory}">${c.name}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="status" class="form-label">Loại sản phẩm</label>
        <form:select class="form-select w-100" id="status" name="status" path="status">
            <c:choose>
                <c:when test="${product.status == true}">
                    <option value="1" selected>Đã sử dụng</option> 
                    <option value="0">Chưa sử dụng</option>
                </c:when>
                <c:when test="${product.status == false}">
                    <option value="0" selected>Chưa sử dụng</option> 
                    <option value="1">Đã sử dụng</option>
                </c:when>
                <c:otherwise>
                    <option value="0">Chưa sử dụng</option>
                    <option value="1">Đã sử dụng</option>
                </c:otherwise>
            </c:choose>
        </form:select>
    </div>
    <div class="form-floating mb-3 mt-3">
        <label for="file">Ảnh sản phẩm</label>
        <form:input type="file" class="form-control" path="file" id="file"  />
        <c:if test="${product.image != null}">
            <img src="${product.image}" width="120" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${product.idproduct != null}">Cập nhật sản phẩm</c:when>
                <c:otherwise>Thêm sản phẩm</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>