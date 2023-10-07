<%-- 
    Document   : index
    Created on : Aug 19, 2023, 6:48:10 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/" var="action" />
<section class="container">
    <h1 class="text-center text-info mt-1">DANH SÁCH DANH MỤC</h1>
    <c:choose>
        <c:when test="${empty categories}">
            <div><p>Danh sách danh mục trống. <a href="<c:url value="/admin/categories/add" />" class="">Thêm danh mục</a></p></div>
        </c:when>    
        <c:otherwise>
            <sec:authorize access="hasRole('ROLE_ADMIN')" >
                <div class="nav-item mb-2">
                    <a href="<c:url value="/admin/categories/add" />" class="btn btn-info mb-2">Thêm danh mục</a>
                </div>
            </sec:authorize>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên danh mục</th>
                        <th>Kí tự tìm kiếm</th>
                        <th>Hình ảnh</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categories}" varStatus="st"  var="c">
                        <tr>
                            <td>${st.count}</td>
                            <td>${c.name}</td>
                            <td>${c.searchTerm}</td>
                            <td>
                                <img src="${c.image}" alt="${c.name}" width="120" />
                            </td>
                            <td style="display: flex;">
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <c:url value="/admin/categories/update/${c.idcategory}" var="action_update" />
                                    <a href="${action_update}" class="btn btn-info">Cập nhật</a>
                                    <c:url value="/admin/categories/delete/${c.idcategory}" var="action_del" />
                                    <form:form method="POST" action="${action_del}"  >
                                        <button type="submit" class="btn btn-danger ml-1" 
                                                onclick="return confirm('Bạn muốn xóa danh mục này không?')">Xóa</button>
                                    </form:form>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</section>
