<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm/Sửa Sản phẩm</title>
    <!-- Bootstrap 4 CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">
    <c:choose>
        <c:when test="${product.id == null}">Thêm sản phẩm mới</c:when>
        <c:otherwise>Sửa sản phẩm</c:otherwise>
    </c:choose>
</h1>

        <form action="<c:if test='${product.id == null}'>/product/add</c:if>
                      <c:if test='${product.id != null}'>/product/edit/${product.id}</c:if>" 
              method="post">
            <div class="form-group">
                <label for="name">Tên sản phẩm:</label>
                <input type="text" id="name" name="name" value="${product.name}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="description">Mô tả:</label>
                <textarea id="description" name="description" class="form-control" rows="3">${product.description}</textarea>
            </div>

            <div class="form-group">
                <label for="price">Giá:</label>
                <input type="number" id="price" name="price" value="${product.price}" step="0.01" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="quantity">Số lượng:</label>
                <input type="number" id="quantity" name="quantity" value="${product.quantity}" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="imageUrl">URL Ảnh:</label>
                <input type="text" id="imageUrl" name="imageUrl" value="${product.imageUrl}" class="form-control">
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-success">Lưu</button>
                <a href="/product/list" class="btn btn-secondary">Hủy</a>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
