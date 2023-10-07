<%-- 
    Document   : index
    Created on : Sep 10, 2023, 3:12:38 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<section class="container">
    <h1 class="text-center text-info mt-1">DANH SÁCH NGƯỜI DÙNG</h1>
    <c:choose>
        <c:when test="${empty users}">
            <div><p>Danh sách danh mục trống.</p></div>
        </c:when>    
        <c:otherwise>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên tài khoản</th>
                        <th>Họ và tên</th>
                        <th>Email</th>
                        <th>Hình ảnh</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" varStatus="st"  var="u">
                        <c:if test="${u.role == 'ROLE_USER'}">
                            <tr>
                                <td>${u.iduser}</td>
                                <td>${u.username}</td>
                                <td>${u.lname} ${u.fname}</td>
                                <td>${u.email}</td>
                                <td>
                                    <img src="${u.avatar}" alt="${c.name}" width="70" height="70" />
                                </td>
                                <td style="display: flex;">
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <c:if test="${u.active == true}">
                                            <c:url value="/admin/users/unactive/${u.iduser}" var="action_del" />
                                            <form:form method="POST" action="${action_del}"  >
                                                <button type="submit" class="btn btn-danger ml-1" 
                                                        onclick="return confirm('Bạn muốn vô hiệu hóa người dùng này?')">Vô hiệu hóa</button>
                                            </form:form>
                                        </c:if>
                                        <c:if test="${u.active == false}">
                                            <c:url value="/admin/users/active/${u.iduser}" var="action_del" />
                                            <form:form method="POST" action="${action_del}"  >
                                                <button type="submit" class="btn btn-info ml-1" 
                                                        onclick="return confirm('Bạn muốn vô hiệu hóa người dùng này?')">Bỏ vô hiệu hóa</button>
                                            </form:form>
                                        </c:if>

                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</section>