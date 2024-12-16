package vn.thietbidientu.payload.response;

import jakarta.persistence.*;
import lombok.*;
import vn.thietbidientu.enums.PaymentMethod;
import vn.thietbidientu.enums.PaymentStatus;

import java.time.LocalDateTime;
// TODO: thêm validation cho các field cần thiết ở các class


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentResponse {
    private Long orderId;

    private Long paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;
    private Double total;

}
