package vn.dodientu.service.implementation;

import jakarta.validation.Valid;
import vn.dodientu.entity.Order;
import vn.dodientu.entity.Payment;
import vn.dodientu.enums.OrderStatus;
import vn.dodientu.enums.PaymentMethod;
import vn.dodientu.enums.PaymentStatus;
import vn.dodientu.exception.CustomException;
import vn.dodientu.payload.response.PaymentResponse;
import vn.dodientu.repository.OrderRepository;
import vn.dodientu.repository.PaymentRepository;
import vn.dodientu.service.interfaces.IPaymentService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderRepository orderRepository;
    @Transactional
    @Override
    public PaymentResponse updatePaymentStatus(@Valid Long orderId, @Valid String status) {
        Payment payment = paymentRepository.findByOrder_OrderId(orderId).orElseThrow(() -> new CustomException("Order not found"));
        PaymentStatus paymentStatus = PaymentStatus.valueOf(status);
        payment.setPaymentStatus(paymentStatus);
        if (paymentStatus == PaymentStatus.PAID) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            payment.setPaymentDate(LocalDateTime.now());
//            đánh dấu order đã thanh toán
            Order order = payment.getOrder();
            order.setOrderStatus(OrderStatus.CONFIRMED);
        }

        Payment savedPayment = paymentRepository.save(payment);
        PaymentResponse paymentResponse = new PaymentResponse();
        BeanUtils.copyProperties(savedPayment, paymentResponse);
        paymentResponse.setOrderId(payment.getOrder().getOrderId());
        return paymentResponse;
    }

    @Override
    public void updatePayment(String orderInfo, String paymentTime) {

    }

    @Override
    public PaymentMethod getPaymentMethodByOrder(Order order) {
        Payment payment = paymentRepository.findByOrder(order).orElseThrow(() -> new CustomException("Payment not found"));
        return payment.getPaymentMethod();
    }
}
