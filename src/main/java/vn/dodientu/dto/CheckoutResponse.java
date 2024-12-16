package vn.dodientu.dto;


import lombok.*;
import vn.dodientu.model.Address;
import vn.dodientu.model.OrderShippingStatus;
import vn.dodientu.model.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {
    private String name;
    private String email;
    private String phone;
    private String note;
    private Address address;
    private List<LineItemRequest> cartItems;
    private int total;

    private Long orderId;  // Mã đơn hàng
    private LocalDateTime orderDate;  // Ngày đặt
    private OrderShippingStatus shippingStatus;  // Trạng thái giao hàng
    private PaymentStatus paymentStatus;
}
