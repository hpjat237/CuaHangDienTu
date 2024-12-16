<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Cá Nhân</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f7f9fb;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .container {
            width: 80%;
            max-width: 1000px;
            margin: 40px auto;
            background-color: #ffffff;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            font-size: 28px;
            color: #4CAF50;
            margin-bottom: 20px;
        }

        .info-section {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .info-section p {
            font-size: 18px;
            color: #555;
            margin: 5px 0;
        }

        .avatar-upload img {
            border-radius: 50%;
            width: 120px;
            height: 120px;
            object-fit: cover;
            border: 3px solid #ddd;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Thông Tin Cá Nhân</h2>
        <div class="info-section">
            <div class="row">
                <div class="col-sm-4">
                    <p><strong>Ảnh đại diện:</strong></p>
                </div>
                <div class="col-sm-8">
                    <div class="avatar-upload">
                        <img src="/images/${user.avatar}" alt="Avatar" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <p><strong>Tên:</strong></p>
                </div>
                <div class="col-sm-8">
                    <p>${user.name}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <p><strong>Email:</strong></p>
                </div>
                <div class="col-sm-8">
                    <p>${user.email}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <p><strong>Số điện thoại:</strong></p>
                </div>
                <div class="col-sm-8">
                    <p>${user.phone}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-8">
                    <button>Cập nhật thông tin</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
