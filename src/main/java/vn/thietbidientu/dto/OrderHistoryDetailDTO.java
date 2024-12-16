package vn.thietbidientu.dto;

import jakarta.persistence.*;
import lombok.*;
import vn.thietbidientu.entity.OrderLine;
import vn.thietbidientu.entity.Payment;
import vn.thietbidientu.enums.OrderStatus;
import vn.thietbidientu.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderHistoryDetailDTO {
    private Long orderId;
    private Long customerId;
    private LocalDateTime orderDate;
    private Double total;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime deliveryDate;

    private Set<OrderLineDTO> orderLines;

    private ShippingAddressDTO shippingAddress;
    private PaymentDTO payment;
    private List<OrderStatusHistoryDTO> orderStatusHistories;
}
