package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}