package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}