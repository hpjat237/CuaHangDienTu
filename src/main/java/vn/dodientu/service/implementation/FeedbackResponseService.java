package vn.dodientu.service.implementation;

import vn.dodientu.entity.FeedbackResponse;
import vn.dodientu.repository.FeedbackResponseRepository;
import vn.dodientu.service.interfaces.IFeedbackResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackResponseService implements IFeedbackResponse {
    @Autowired
    private FeedbackResponseRepository responseRepository;

    @Override
    public void saveResponse(FeedbackResponse response) {
        responseRepository.save(response);
    }

    @Override
    public void save(FeedbackResponse feedbackResponse) {
        responseRepository.save(feedbackResponse);
    }

    @Override
    public void update(FeedbackResponse feedbackResponse) {
        responseRepository.save(feedbackResponse);
    }

    @Override
    public Optional<FeedbackResponse> findById(Long id) {
        return responseRepository.findById(id);
    }

    // Phương thức tìm phản hồi theo feedbackId
    @Override
    public FeedbackResponse findByFeedbackId(Long feedbackId) {
        return responseRepository.findByProductFeedback_ProductFeedbackId(feedbackId);
    }
}
