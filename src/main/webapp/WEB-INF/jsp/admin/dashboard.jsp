<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{layout.html}">
<section layout:fragment="content">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý doanh thu</title>

    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>

<!-- breadcrumb area start -->

<!-- breadcrumb area end -->
<div class="container mt-5">
    <!--    <h2 class="text-center mb-4">Quản lý doanh thu</h2>-->

    <!-- Thông tin doanh thu -->
    <div class="row mb-4">
        <div class="col-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Doanh thu hôm nay</h5>
                    <p id="todayRevenue" class="display-6">0 VND</p>
                </div>
            </div>
        </div>
        <div class="col-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Doanh thu trong tháng</h5>
                    <p id="monthlyRevenue" class="display-6">0 VND</p>
                </div>
            </div>
        </div>
        <div class="col-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Doanh thu trong năm</h5>
                    <p id="annualRevenue" class="display-6">0 VND</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Biểu đồ doanh thu 12 tháng -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Biểu đồ doanh thu 12 tháng</h5>
            <canvas id="monthlyRevenueChart"></canvas>
            <!--            <canvas id="monthlyRevenueChart" style="max-width: 600px; height: 300px; margin: 0 auto;"></canvas>-->

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Custom JS -->
<script>
    function checkAdminRole() {
        const token = localStorage.getItem('jwtToken'); // Lấy token từ localStorage hoặc cookie
        if (!token) {
            alert('Bạn không có quyền truy cập!');
            return false;
        }

        try {
            const decoded = jwt_decode(token); // Giải mã token
            const role = decoded.role; // Lấy vai trò từ payload
            if (role === 'Customer' || role ==='Manager') {
                alert('Chỉ quản trị viên mới có quyền truy cập!');
                return false;
            }
            return true;
        } catch (error) {
            console.error('Token không hợp lệ:', error);
            alert('Token không hợp lệ! Vui lòng đăng nhập lại.');
            return false;
        }
    }
    $(document).ready(() => {
        if (checkAdminRole()) {// bỏ hết script ở đây
            $.ajax({
                url: '/api/revenue/today',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    $('#todayRevenue').text(new Intl.NumberFormat().format(data.revenue) + " VND");
                },
                error: function(xhr, status, error) {
                    console.error('Có lỗi khi lấy dữ liệu doanh thu hôm nay:', error);
                }
            });

            // Gọi API lấy dữ liệu doanh thu tháng
            $.ajax({
                url: '/api/revenue/monthly',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    $('#monthlyRevenue').text(new Intl.NumberFormat().format(data.revenue) + " VND");
                },
                error: function(xhr, status, error) {
                    console.error('Có lỗi khi lấy dữ liệu doanh thu tháng:', error);
                }
            });

            // Gọi API lấy dữ liệu doanh thu năm
            $.ajax({
                url: '/api/revenue/annual',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    $('#annualRevenue').text(new Intl.NumberFormat().format(data.revenue) + " VND");
                },
                error: function(xhr, status, error) {
                    console.error('Có lỗi khi lấy dữ liệu doanh thu năm:', error);
                }
            });

            // Gọi API lấy dữ liệu doanh thu hàng tháng và vẽ biểu đồ
            $.ajax({
                url: '/api/revenue/annual-data',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    // Vẽ biểu đồ
                    const ctx = document.getElementById('monthlyRevenueChart').getContext('2d');
                    new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: [
                                "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
                                "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
                            ],
                            datasets: [{
                                label: 'Doanh thu (VND)',
                                data: data.monthlyRevenue, // Dữ liệu doanh thu tháng
                                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                                borderColor: 'rgba(54, 162, 235, 1)',
                                borderWidth: 1
                            }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });
                },
                error: function(xhr, status, error) {
                    console.error('Có lỗi khi lấy dữ liệu doanh thu 12 tháng:', error);
                }
            }); }
        });
    // Gọi API lấy dữ liệu doanh thu hôm nay


</script>


</body>
</section>
</html>
