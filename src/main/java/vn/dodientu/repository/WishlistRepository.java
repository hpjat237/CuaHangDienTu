package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
}