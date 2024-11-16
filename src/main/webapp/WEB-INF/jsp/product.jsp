<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="text-center mt-5">Product Page</h1>
        <p class="lead text-center">Here is a list of products.</p>
        
        <!-- Displaying a sample product -->
        <div class="product">
            <h2>Product Name: Example Product</h2>
            <p>Price: $99.99</p>
            <p>Description: This is a description of the example product.</p>
            <div class="alert alert-info">
                <strong>Product ID: 12345</strong>
            </div>
        </div>

        <div class="text-center mt-4">
            <a href="index.jsp" class="btn btn-primary">Back to Home</a>
        </div>
    </div>
    
    <!-- Adding some scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
