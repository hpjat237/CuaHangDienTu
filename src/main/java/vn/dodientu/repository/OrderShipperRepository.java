package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.OrderShipper;
import vn.dodientu.model.OrderShipperId;

public interface OrderShipperRepository extends JpaRepository<OrderShipper, OrderShipperId> {
}