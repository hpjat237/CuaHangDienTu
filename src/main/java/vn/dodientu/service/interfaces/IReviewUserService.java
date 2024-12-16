package vn.dodientu.service.interfaces;

import hcmute.edu.vn.dto.ReviewDTO;

import java.util.List;

public interface IReviewUserService {
    List<ReviewDTO> getReviewDetailsByProductId(Integer productId);
}
