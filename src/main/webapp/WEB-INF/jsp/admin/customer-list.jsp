<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "jakarta.tags.core" %>
  


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý khách hàng</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <!-- Tiêu đề -->
    <h1 class="text-center">Danh sách khách hàng</h1>

    <!-- Tìm kiếm khách hàng -->
    <div class="row mt-4 mb-3">
        <div class="col-md-6 offset-md-3">
            <form id="searchForm" class="d-flex" method="get" action="/search">
                <input type="text" name="keyword" class="form-control me-0" id="keyword"
                       placeholder="Tìm kiếm khách hàng theo tên hoặc email"
                       style="border: 2px solid #444444; border-radius: 0; height: 40px"/>
                <button type="submit" class="search-btn"
                        style="background-color: #444444; color: white; border: none; padding: 10px; cursor: pointer; width: 40px; height: 40px; border-radius: 0;"
                        onmouseover="this.style.backgroundColor='#d8373e';"
                        onmouseout="this.style.backgroundColor='#444444';">
                    <i class="fa fa-search"></i>
                </button>
            </form>
        </div>
    </div>

    <!-- Bảng thông tin khách hàng -->
   <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Verified</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterating through the user list -->
            <c:forEach var="user" items="${userPage.content}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.role.name}</td>
                    <td>${user.isVerified ? 'Yes' : 'No'}</td>
                </tr>
                
            </c:forEach>
        </tbody>
    </table>

    <!-- Phân trang -->
    <nav>
        <ul class="pagination justify-content-center">
            <c:if test="${page > 0}">
                <li class="page-item">
                    <a class="page-link" href="?page=${page - 1}">Previous</a>
                </li>
            </c:if>
            <c:forEach var="i" begin="0" end="${totalPages - 1}">
                <li class="page-item ${page == i ? 'active' : ''}">
                    <a class="page-link" href="?page=${i}">${i + 1}</a>
                </li>
            </c:forEach>
            <c:if test="${page < totalPages - 1}">
                <li class="page-item">
                    <a class="page-link" href="?page=${page + 1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
