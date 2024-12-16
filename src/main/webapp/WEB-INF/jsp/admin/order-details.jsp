<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{layout.html}">
<div class="row" layout:fragment="content">
    <head>
        <meta charset="UTF-8">
        <title>Chi tiết đơn hàng</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" th:href="@{/assets/css/style.css}">

    </head>
    <body>

    <div class="container mt-5">
        <!-- Nút quay lại và tiêu đề -->
        <div class="d-flex align-items-center justify-content-between mb-4">
            <!-- Nút quay lại -->
            <a th:href="@{/admin/orders}" class="btn btn-secondary"
               style="display: inline-block; background-color: #444444; color: white; padding: 10px; text-align: center; width: 40px; height: 40px; border-radius: 0; text-decoration: none; cursor: pointer;"
               onmouseover="this.style.backgroundColor='#d8373e';"
               onmouseout="this.style.backgroundColor='#444444';">
                <i class="fa fa-arrow-left" style="font-size: 20px;"></i>
            </a>
            <!-- Tiêu đề -->
            <h1 class="text-center flex-grow-1">Chi tiết đơn hàng</h1>
        </div>

        <!-- Thông tin đơn hàng -->
        <div class="card mt-4">
            <div class="card-header">
                <h4>Thông tin đơn hàng</h4>
            </div>
            <div class="card-body">
                <p><strong>Mã đơn hàng:</strong> <span id="Id"></span></p>
                <p><strong>Tên khách hàng:</strong> <span id="name"></span></p>
                <p><strong>Số đện thoại:</strong> <span id="phone"></span></p>
                <p><strong>Địa chỉ giao:</strong><span id="address" ></span></p>
                <p><strong>Ngày đặt:</strong> <span id="orderDate"></span></p>
                <p><strong>Ngày nhận:</strong> <span id="receiveDate"></span></p>
                <p><strong>Trạng thái:</strong> <span id="shippingStatus"></span></p>
                <p><strong>Trạng thái thanh toán:</strong> <span id="paymentStatus"></span></p>
                <!--                <p><strong>Phương thức thanh toán:</strong> <span id="paymentMethod"></span></p>-->
                <p><strong>Ghi chú:</strong> <span id="note"></span></p>
            </div>
        </div>

        <!-- Trạng thái vận chuyển -->
        <div class="mt-3">
            <a id="delivering" class="btn btn-danger custom-hover" style="display: none;"
               onclick="confirmDelivering()">
                Xác đang đang giao
            </a>
            <a id="deliveried" class="btn btn-danger btn-custom-gray" style="display: none;"
               onclick="confirmDeliveried()">
                Xác nhận đã giao
            </a>
        </div>

        <!-- Danh sách sản phẩm -->
        <div class="card mt-4">
            <div class="card-header">
                <h4>Danh sách sản phẩm</h4>
            </div>
            <div class="card-body">
                <table class="table table-bordered table-striped text-center" id="itemTable">
                    <thead class="group-product-table">
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Ảnh sản phẩm</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Giá</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>

                    <tfoot>
                    <tr>
                        <td colspan="4" style="text-align: right; font-weight: bold;">Tổng tiền:</td>
                        <td id="total">30,000,000 VND</td>
                    </tr>
                    </tfoot>

                </table>
            </div>
        </div>

        <!-- Hiển thị thông báo nếu trạng thái đơn hàng đã được hủy -->
        <!--            <div th:if="${order.shippingStatus == 'Đã hủy'}">-->
        <!--                <p class="text-danger">Đơn hàng này đã bị hủy.</p>-->
        <!--            </div>-->

    </div>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        const path = window.location.pathname;
        const parts = path.split('/'); // ["", "customers", "1"]
        const orderID = parts[parts.length - 1];

        function confirmDelivering(){
            $.ajax({
                url: `/api/orders/delivering/${orderID}`,
                type: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`
                },
                success: function (respone){
                    alert(respone)
                    window.location.reload();
                }
            })
        }


        function confirmDeliveried(){
            $.ajax({
                url: `/api/orders/deliveried/${orderID}`,
                type: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`
                },
                success: function (respone){
                    alert(respone)
                    window.location.reload();
                }
            })
        }



        $(document).ready(function () {
            const token = localStorage.getItem('jwtToken');

            function loadOrderDetail(){
                if(token){
                    $.ajax({
                        url: `/api/orders/${orderID}`,
                        type: 'GET',
                        headers: {
                            'Authorization': `Bearer ${token}`
                        },
                        success: function (order){
                            const statusMap = {
                                NONDELIVERY: "Chưa giao",
                                DELIVERING: "Đang giao",
                                DELIVERIED: "Đã giao",
                                CANCELED: "Đã hủy",
                                COMPLETED: "Hoàn thành",
                                ERRORED: "Lỗi",
                                PENDING: "Chờ thanh toán",
                                PAID: "Đã thanh toán"
                            };
                            console.log(order)
                            $('#orderId').text(order.orderId);
                            $('#name').text(order.name);
                            $('#phone').text(order.phone);

                            var address = order.address
                            var addressStr = ""
                            if(address != null){
                                addressStr += address.commune + ", " + address.district + ", " +address.province + ", " +address.country
                            }
                            $('#address').text(addressStr !== "" ? addressStr : "Chưa có")
                            $('#orderDate').text(new Date(order.orderDate).toLocaleDateString("vi-VN"))
                            $('#receiveDate').text(new Date(order.receiveDate).toLocaleDateString("vi-VN"))
                            $('#shippingStatus').text(statusMap[order.shippingStatus]);
                            $('#paymentStatus').text(statusMap[order.paymentStatus]);
                            // $('#paymentMethod').text(order.paymentMethod);
                            $('#note').text(order.note);

                            if(order.shippingStatus == "NONDELIVERY")
                                $('#delivering').show()
                            if(order.shippingStatus == "DELIVERING")
                                $('#deliveried').show()

                            var tableBody = $('#itemTable tbody');
                            var items = order.items
                            if(items.length === 0){
                                tableBody.append('<tr><td colspan="8" class="text-center">Không có đơn hàng nào!</td></tr>');
                            }
                            else {
                                items.forEach(function (order, index) {
                                    var image = order.urlImage
                                        ? `<img src="${order.urlImage}" alt="Ảnh sản phẩm" style="max-width: 100px; height: auto;"/>`
                                        : `<span>Không có ảnh</span>`; // Hiển thị nếu không có ảnh
                                    const row = `
                                            <tr data-id="${order.orderId}">
                                                <td>${index + 1}</td>
                                                <td>${image}</td>
                                                <td>${order.productName}</td>
                                                <td>${order.quantity}</td>
                                                <td>${order.price.toLocaleString("vi-VN", { style: "currency", currency: "VND" })}</td>
                                            </tr>
                                        `;
                                    tableBody.append(row);
                                });
                                $('#total').text(order.totalAmount.toLocaleString("vi-VN", { style: "currency", currency: "VND" }))

                            }
                        }
                    })
                }
            }
            // Tải trang đầu tiên khi trang được load
            loadOrderDetail()
        })


    </script>
    </body>
</div>
</html>