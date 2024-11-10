package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}