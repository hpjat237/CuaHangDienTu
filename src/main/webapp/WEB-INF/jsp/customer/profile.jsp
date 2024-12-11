<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Cá Nhân</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* Thiết lập cho body */
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

        /* Section thông tin */
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
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            align-self: center;
        }

        button:hover {
            background-color: #45a049;
        }

        /* Phần chỉnh sửa */
        .edit-section {
            display: none;
            margin-top: 30px;
        }

        .edit-section form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .edit-section input[type="text"], 
        .edit-section input[type="email"], 
        .edit-section input[type="file"] {
            padding: 12px;
            border-radius: 8px;
            border: 1px solid #ddd;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
        }

        .edit-section label {
            font-weight: bold;
            font-size: 16px;
            color: #555;
        }

        .edit-section input[type="file"] {
            padding: 10px;
        }

        .edit-section .avatar-upload {
            text-align: center;
        }

        .button-container {
            text-align: center;
        }

        /* Footer */
        .footer {
            text-align: center;
            margin-top: 30px;
            font-size: 14px;
            color: #888;
        }

        .footer a {
            color: #4CAF50;
            text-decoration: none;
        }

        .footer a:hover {
            text-decoration: underline;
        }

        /* Hiệu ứng transition cho phần chỉnh sửa */
        .edit-section, .info-section {
            transition: opacity 0.3s ease-in-out;
        }

        /* Các hiệu ứng loading và hover */
        .info-section button {
            margin-top: 20px;
            font-size: 16px;
        }

        /* Cải thiện responsive */
        @media (max-width: 768px) {
            .container {
                width: 95%;
            }

            .info-section {
                align-items: center;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Thông tin cá nhân</h2>

    <!-- Thông tin cá nhân -->
    <div class="info-section">
        <p><strong>Họ tên:</strong> <span id="fullNameDisplay">${user.fullName}</span></p>
        <p><strong>Email:</strong> <span id="emailDisplay">${user.email}</span></p>
        <p><strong>Số điện thoại:</strong> <span id="phoneDisplay">${user.phoneNumber}</span></p>
        <div class="avatar-upload">
            <p><strong>Ảnh đại diện:</strong></p>
            <img id="avatarDisplay" src="${user.avatarUrl}" alt="Avatar" />
        </div>
        <button onclick="toggleEditSection()">Chỉnh sửa</button>
    </div>

    <!-- Phần chỉnh sửa -->
    <div class="edit-section">
        <form action="/updateProfile" method="post" enctype="multipart/form-data">
            <div>
                <label for="fullName">Họ tên:</label>
                <input type="text" id="fullName" name="fullName" value="${user.fullName}" placeholder="Nhập họ tên của bạn" />
            </div>

            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}" readonly />
            </div>

            <div>
                <label for="phoneNumber">Số điện thoại:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" placeholder="Nhập số điện thoại của bạn" />
            </div>

            <div class="avatar-upload">
                <label for="avatar">Ảnh đại diện:</label>
                <input type="file" id="avatar" name="avatar" accept="image/*" onchange="previewImage(event)" />
                <br />
                <img id="avatarPreview" src="${user.avatarUrl}" alt="Avatar" />
            </div>

            <div class="button-container">
                <button type="submit">Cập nhật thông tin</button>
            </div>
        </form>
    </div>

    <div class="footer">
        <p>© 2024 <a href="#">Nhị Gia Group</a>. All rights reserved.</p>
    </div>
</div>

<script>
    // Hàm để hiển thị phần chỉnh sửa khi nhấn nút "Chỉnh sửa"
    function toggleEditSection() {
        const editSection = document.querySelector('.edit-section');
        const infoSection = document.querySelector('.info-section');
        
        // Chuyển đổi hiển thị phần chỉnh sửa
        editSection.style.display = editSection.style.display === 'none' ? 'block' : 'none';
        infoSection.style.display = infoSection.style.display === 'none' ? 'block' : 'none';

        // Thêm hiệu ứng fade
        editSection.style.opacity = editSection.style.display === 'none' ? '0' : '1';
        infoSection.style.opacity = infoSection.style.display === 'none' ? '0' : '1';
    }

    // Hàm để xem trước ảnh khi người dùng tải lên
    function previewImage(event) {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.getElementById("avatarPreview");
            img.src = e.target.result;
        }
        reader.readAsDataURL(file);
    }
</script>

</body>
</html>>