<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .apple-nav {
            background: rgba(0, 0, 0, 0.8);
            backdrop-filter: blur(20px);
            -webkit-backdrop-filter: blur(20px);
            position: fixed;
            width: 100%;
            z-index: 9999;
            height: 44px;
        }
        
        .nav-list {
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
        }
        
        .nav-item {
            margin: 0 20px;
        }
        
        .nav-link {
            color: #f5f5f7;
            text-decoration: none;
            font-size: 12px;
            font-weight: 400;
            opacity: 0.8;
            transition: opacity 0.2s ease;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
        }
        
        .nav-link:hover {
            opacity: 1;
            color: #f5f5f7;
            text-decoration: none;
        }
        
        .apple-logo {
            width: 16px;
            opacity: 0.8;
            transition: opacity 0.2s ease;
        }
        
        .apple-logo:hover {
            opacity: 1;
        }
        
        .auth-buttons {
            position: absolute;
            right: 20px;
            top: 50%;
            transform: translateY(-50%);
        }
        
        .auth-link {
            color: #f5f5f7;
            text-decoration: none;
            font-size: 12px;
            margin-left: 15px;
            opacity: 0.8;
            transition: opacity 0.2s ease;
        }
        
        .auth-link:hover {
            opacity: 1;
            color: #f5f5f7;
            text-decoration: none;
        }
        
        @media (max-width: 768px) {
            .nav-item {
                margin: 0 10px;
            }
            
            .auth-buttons {
                display: none;
            }
        }
    </style>
</head>
<body>
    <nav class="apple-nav">
        <div class="container-fluid">
            <ul class="nav-list">
                <li class="nav-item">
                    <a href="/" class="nav-link">
                        <svg class="apple-logo" viewBox="0 0 17 48" fill="currentColor">
                            <path d="M15.5752 19.0833C13.2502 19.0833 11.8335 20.5167 11.8335 22.8333C11.8335 25.1583 13.2502 26.5833 15.5752 26.5833C17.9002 26.5833 19.3252 25.1583 19.3252 22.8333C19.3252 20.5167 17.9002 19.0833 15.5752 19.0833Z"/>
                        </svg>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/store" class="nav-link">Cửa hàng</a>
                </li>
                <li class="nav-item">
                    <a href="/mac" class="nav-link">Mac</a>
                </li>
                <li class="nav-item">
                    <a href="/ipad" class="nav-link">iPad</a>
                </li>
                <li class="nav-item">
                    <a href="/iphone" class="nav-link">iPhone</a>
                </li>
                <li class="nav-item">
                    <a href="/watch" class="nav-link">Watch</a>
                </li>
                <li class="nav-item">
                    <a href="/support" class="nav-link">Hỗ trợ</a>
                </li>
            </ul>
            
            <div class="auth-buttons">
                <a href="/login" class="auth-link">Đăng nhập</a>
                <a href="/register" class="auth-link">Đăng ký</a>
            </div>
        </div>
    </nav>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>