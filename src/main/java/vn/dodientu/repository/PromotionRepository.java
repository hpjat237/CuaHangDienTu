package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}