package vn.thietbidientu.payload.response;

import jakarta.persistence.*;
import lombok.*;
import vn.thietbidientu.dto.OrderLineForOrderDTO;
import vn.thietbidientu.entity.ShippingAddress;
import vn.thietbidientu.enums.OrderStatus;
import vn.thietbidientu.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse {

    private Long orderId;
    private Long customerId;
    private LocalDateTime orderDate;
    private Double total;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime deliveryDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderLineForOrderDTO> orderLines;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ShippingAddress shippingAddress;

    private PaymentMethod paymentMethod;




}
