package vn.thietbidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.thietbidientu.entity.OrderLine;
import vn.thietbidientu.entity.Product;

import java.util.Optional;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {


    boolean existsByProduct(Product product);
    @Query("SELECT SUM(ol.quantity) FROM OrderLine ol WHERE ol.product = :product")
    Optional<Long> sumQuantityByProduct(Product product);

}
