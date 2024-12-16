package vn.thietbidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.thietbidientu.entity.CartItem;
import vn.thietbidientu.entity.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsByProduct(Product product);
}
