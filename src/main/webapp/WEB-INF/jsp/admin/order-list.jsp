<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý đơn hàng</title>
    <style>
        /* CSS tùy chỉnh cho nút Khóa tài khoản */
        .btn-danger.custom-hover {
            background-color: #dc3545; /* Màu đỏ */
            border-color: #dc3545; /* Viền màu đỏ */
            color: #fff; /* Chữ màu trắng */
        }

        /* Hiệu ứng hover */
        .btn-danger.custom-hover:hover {
            background-color: #444444; /* Màu xám khi hover */
            border-color: #444444; /* Viền màu xám khi hover */
            color: #fff; /* Chữ giữ nguyên màu trắng */
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Danh sách đơn hàng</h1>

    <!-- Tìm kiếm và lọc -->
    <form id="searchForm" class="d-flex justify-content-between mb-3" action="/orders" method="get">
        <!-- Trường nhập từ khóa tìm kiếm -->
        <input type="text" id="keyword" name="keyword" class="form-control me-2" placeholder="Nhập mã đơn hàng">
        <!-- Dropdown chọn trạng thái -->
        <select id="status" name="status" class="form-select me-2">
            <option value="">Tất cả</option>
            <option value="CANCELED">Đã huỷ</option>
            <option value="DELIVERING">Đang giao</option>
            <option value="DELIVERIED">Đã giao</option>
        </select>
        <!-- Nút tìm kiếm -->
        <button type="submit" class="search-btn"
                style="background-color: #444444; color: white; border: none; padding: 10px; cursor: pointer; width: 40px; height: 40px; border-radius: 0;"
                onmouseover="this.style.backgroundColor='#d8373e';"
                onmouseout="this.style.backgroundColor='#444444';">
            <i class="fa fa-search"></i>
        </button>
    </form>

    <!-- Bảng danh sách đơn hàng -->
    <table class="table table-bordered table-striped text-center" id="orderTable">
        <thead class="table-custom-header">
        <tr>
            <th style="border: 3px solid black;text-align: center; vertical-align: middle; " scope="col">STT</th>
            <th style="border: 3px solid black;text-align: center; vertical-align: middle; " scope="col">Mã đơn hàng</th>
            <th style="border: 3px solid black;text-align: center; vertical-align: middle; " scope="col">Ngày đặt</th>
            <th style="border: 3px solid black;text-align: center; vertical-align: middle; " scope="col">Tên khách hàng</th>
            <th style="border: 3px solid black;text-align: center; vertical-align: middle; " scope="col">Giá trị đơn hàng</th>
            <th style="border: 3px solid black;text-align: center; vertical-align: middle; " scope="col">Trạng thái</th>
            <th style="border: 3px solid black;text-align: center; vertical-align: middle; " scope="col">Thông tin chi tiết</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${orders.indexOf(order) + 1}</td>
                <td>${order.Id}</td>
                <td><fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy"/></td>
                <td>${order.user.fullName}</td>
                <td>${order.totalAmount}</td>
                <td>
                    <c:choose>
                        <c:when test="${order.shippingStatus == 'CANCELED'}">Đã huỷ</c:when>
                        <c:when test="${order.shippingStatus == 'DELIVERING'}">Đang giao</c:when>
                        <c:when test="${order.shippingStatus == 'DELIVERIED'}">Đã giao</c:when>
                        <c:otherwise>Chưa xác định</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="/admin/orders/${order.id}" class="btn btn-danger custom-hover">Chi tiết</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav>
        <ul class="pagination justify-content-center">
            <c:forEach begin="1" end="${totalPages}" var="page">
                <li class="page-item ${page == currentPage ? 'active' : ''}">
                    <a class="page-link" href="?page=${page}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
</body>
</html>
