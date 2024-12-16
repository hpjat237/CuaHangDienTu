package vn.dodientu.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import vn.dodientu.model.Order;
import vn.dodientu.model.OrderShippingStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	// Tìm tất cả các đơn hàng theo ID người dùng với phân trang
    Page<Order> findAllByUserId(Long id, Pageable pageable);

    // Tìm kiếm đơn hàng theo từ khóa hoặc trạng thái giao hàng
    @Query("SELECT o FROM Order o WHERE " +
            "(CAST(o.Id AS string) LIKE %:keyword% OR " +
            "o.user.fullName LIKE %:keyword% OR " +
            "o.shippingStatus = :status)")
    Page<Order> searchByKeywordAndStatus(@Param("keyword") String keyword,
                                               @Param("status") OrderShippingStatus status,
                                               Pageable pageable);

    // Tìm tất cả các đơn hàng có trạng thái giao hàng "DELIVERIED" và ngày nhận hàng trong khoảng thời gian
    @Query("SELECT o FROM Order o WHERE o.shippingStatus = 'DELIVERIED' AND o.payment.paymentDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersWithShippingStatusAndReceiveDate(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
