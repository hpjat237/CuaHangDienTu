package vn.thietbidientu.repository;

import jakarta.validation.Valid;
import vn.thietbidientu.entity.Order;
import vn.thietbidientu.entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrder(Order order);

    Optional<Payment> findByOrder_OrderId(@Valid Long orderId);

}
