<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify OTP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="text-center mt-5">Verify OTP</h2>
        
		<div class="alert alert-danger" role="alert" style="${not empty error ? '' : 'display:none;'}">
		    ${error}
		</div>

        
        <form action="/auth/confirm-email" method="post" class="mt-4">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${email}" readonly>
            </div>
            <div class="form-group mt-3">
                <label for="otp">Enter OTP</label>
                <input type="text" class="form-control" id="otp" name="otp" placeholder="Enter OTP" required>
            </div>
            <button type="submit" class="btn btn-success mt-3">Verify OTP</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
