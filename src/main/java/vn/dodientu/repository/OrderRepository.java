package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}