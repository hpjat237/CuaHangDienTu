package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}