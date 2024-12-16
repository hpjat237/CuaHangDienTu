package vn.thietbidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.thietbidientu.entity.Cart;
import vn.thietbidientu.entity.User;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    boolean existsByCartItems_Product_ProductId(Long productId);

    Optional<Cart> findByCustomer(User user);

    Optional<Cart> findByCustomer_UserId(Long userId);
}
