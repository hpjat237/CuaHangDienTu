package vn.dodientu.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import vn.thietbidientu.enums.PaymentMethod;
import vn.thietbidientu.enums.PaymentStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {
    private Long paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private Double total;
}
