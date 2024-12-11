<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple JSP Example</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>

    <div class="container">
        <h1 class="text-center mt-5">Welcome to My Simple JSP Page!</h1>
        <p class="lead text-center">This is a basic example of using JSP in a Spring Boot application.</p>
        
        <!-- Simple dynamic content using JSP -->
        <div class="alert alert-info">
            <strong>Hello, <%= request.getParameter("name") != null ? request.getParameter("name") : "Guest" %>!</strong>
        </div>

        <div class="text-center">
            <!-- Link to controller to show product page -->
            <a href="/product" class="btn btn-success">Go to Product Page</a>
        </div>
        <h1>Sản phẩm nổi bật</h1>
    	<div id="top-products">
        <div th:each="product : ${topProducts}">
            <h2 th:text="${product.name}"></h2>
            <img th:src="${product.imageUrl}" alt="Product Image">
            <p th:text="${product.price}"></p>
        </div>
    </div>
    
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
    
    <!-- Adding some scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
