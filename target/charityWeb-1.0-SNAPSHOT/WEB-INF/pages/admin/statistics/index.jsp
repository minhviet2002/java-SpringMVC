<%-- 
    Document   : index
    Created on : Sep 9, 2023, 3:09:52 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <c:forEach items="${catecount}" var="c">
        <div class="countPostCate none">
            ${c[2]}
        </div>
        <div class="cate none">
            ${c[1]}
        </div>
    </c:forEach>
    <h2>Danh mục</h2>
    <canvas id="myChartCate"></canvas>
</div>
<!--<div>
    ${posts}
    <h2>Bài viết</h2>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>STT</th>
                <th>Tên danh mục</th>
                <th>Kí tự tìm kiếm</th>
                <th>Hình ảnh</th>
            </tr>
        </thead>
        <tbody>
            
            <c:forEach items="${posts}" varStatus="st"  var="p">
                <tr>
                    <td>${st.count}</td>
                    <td>${p[0]}</td>
                    <td>${p[1]}</td>
                    <td>${p[2]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>-->

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    const ctx = document.getElementById('myChartCate');
    const cates = document.querySelectorAll(".cate");
    const countPostCate = document.querySelectorAll(".countPostCate");
    let arrCate = [];
    let arrCount = [];
    cates.forEach((item, index) => {
        arrCate.push(item.innerText);
        arrCount.push(countPostCate[index].innerText);
    })
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: arrCate,
            datasets: [{
                    label: 'Số lượng bài viết trên danh mục',
                    data: arrCount,
                    borderWidth: 1,
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>