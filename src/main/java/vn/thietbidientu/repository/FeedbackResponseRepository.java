package vn.thietbidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import vn.thietbidientu.entity.FeedbackResponse;

public interface FeedbackResponseRepository extends JpaRepository<FeedbackResponse, Long> {
    FeedbackResponse findByProductFeedback_ProductFeedbackId(Long feedbackId);

}
