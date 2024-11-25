<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Home</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Header -->
    <header>
        <div class="container">
            <h1>Welcome, ${sessionScope.username}!</h1>
            <nav>
                <ul>
                    <li><a href="home">Home</a></li>
                    <li><a href="products">Products</a></li>
                    <li><a href="orderHistory">Order History</a></li>
                    <li><a href="profile">Profile</a></li>
                    <li><a href="logout">Logout</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <!-- Main Content -->
    <main>
        <div class="container">
            <h2>Customer Dashboard</h2>
            <p>Explore the features and services available for you:</p>
            <div class="features">
                <div class="feature">
                    <h3>Products</h3>
                    <p>View and purchase from a wide range of products.</p>
                    <a href="products" class="btn">Browse Products</a>
                </div>
                <div class="feature">
                    <h3>Order History</h3>
                    <p>Check the status and details of your previous orders.</p>
                    <a href="orderHistory" class="btn">View Orders</a>
                </div>
                <div class="feature">
                    <h3>Your Profile</h3>
                    <p>Update your personal information and preferences.</p>
                    <a href="profile" class="btn">Update Profile</a>
                </div>
                <div class="feature">
                    <h3>Support</h3>
                    <p>Need help? Contact our support team for assistance.</p>
                    <a href="support" class="btn">Contact Support</a>
                </div>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer>
        <div class="container">
            <p>&copy; 2024 Your Company Name. All rights reserved.</p>
            <p>Contact us: <a href="mailto:support@yourcompany.com">support@yourcompany.com</a></p>
        </div>
    </footer>
</body>
</html>
