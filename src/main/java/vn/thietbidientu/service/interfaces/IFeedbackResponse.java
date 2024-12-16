package vn.thietbidientu.service.interfaces;

import java.util.Optional;

import vn.thietbidientu.entity.FeedbackResponse;

public interface IFeedbackResponse {
    void saveResponse(FeedbackResponse response);

    void save(FeedbackResponse feedbackResponse);

    void update(FeedbackResponse feedbackResponse);

    Optional<FeedbackResponse> findById(Long id);

    // Phương thức tìm phản hồi theo feedbackId
    FeedbackResponse findByFeedbackId(Long feedbackId);
}
