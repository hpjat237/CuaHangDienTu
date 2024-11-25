<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple JSP Example</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
            background-color: #ffffff;
            color: #1d1d1f;
        }
        .nav-link {
            color: #1d1d1f;
            font-size: 0.9em;
            padding: 12px 24px;
            transition: color 0.3s ease;
        }
        .nav-link:hover {
            color: #2997ff;
        }
        .apple-header {
            background: rgba(255, 255, 255, 0.8);
            backdrop-filter: blur(20px);
            border-bottom: 1px solid #d2d2d7;
            position: fixed;
            width: 100%;
            z-index: 999;
        }
        .main-content {
            padding-top: 120px;
        }
        .hero-section {
            text-align: center;
            padding: 80px 0;
        }
        .hero-title {
            font-size: 56px;
            font-weight: 600;
            letter-spacing: -0.005em;
        }
        .hero-subtitle {
            font-size: 28px;
            font-weight: 400;
            color: #6e6e73;
            margin: 20px 0;
        }
        .apple-button {
            background: #0071e3;
            color: white;
            border-radius: 980px;
            padding: 12px 24px;
            font-size: 17px;
            transition: all 0.3s ease;
        }
        .apple-button:hover {
            background: #0077ed;
            color: white;
            text-decoration: none;
        }
        .welcome-alert {
            background: #f5f5f7;
            border: none;
            border-radius: 18px;
            padding: 20px;
            margin: 40px 0;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>


    <!-- Main content -->
    <div class="container main-content">
        <div class="hero-section">
            <h1 class="hero-title">Welcome to Our Platform</h1>
            <p class="hero-subtitle">Experience the difference with our innovative solutions.</p>
            
            <div class="welcome-alert">
                <strong>Xin chào, <%= request.getParameter("name") != null ? request.getParameter("name") : "Guest" %>!</strong>
            </div>

            <div class="mt-4">
                <a href="/product" class="apple-button">Khám phá sản phẩm</a>
            </div>
        </div>
    </div>
    
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
