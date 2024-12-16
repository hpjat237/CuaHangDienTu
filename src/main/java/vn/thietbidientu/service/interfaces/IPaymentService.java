package vn.thietbidientu.service.interfaces;

import jakarta.validation.Valid;
import vn.thietbidientu.entity.Order;
import vn.thietbidientu.enums.PaymentMethod;
import vn.thietbidientu.payload.response.PaymentResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface IPaymentService {
    @Transactional
    PaymentResponse updatePaymentStatus(@Valid Long orderId, @Valid String status);

    void updatePayment(String orderInfo, String paymentTime);

    PaymentMethod getPaymentMethodByOrder(Order order);
}
