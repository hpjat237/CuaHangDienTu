package vn.dodientu.service.implementation;

import hcmute.edu.vn.dto.ReviewDTO;
import hcmute.edu.vn.entity.Product;
import hcmute.edu.vn.entity.ReviewUser;
import hcmute.edu.vn.entity.User;
import hcmute.edu.vn.repository.ReviewUserRepository;
import hcmute.edu.vn.service.IReviewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class ReviewUserService implements IReviewUserService {

    @Autowired
    private ReviewUserRepository reviewUserRepository;

    public List<ReviewDTO> getReviewDetailsByProductId(Integer productId) {
        // Gọi phương thức trong repository để lấy thông tin review và ảnh
        return reviewUserRepository.findReviewDetailsByProductId(productId);
    }

    public ReviewUser addReview(Product product, User user, String reviewContent, Integer productQuality, String reviewImage) {
        // Tạo đối tượng ReviewUser mới
        ReviewUser review = new ReviewUser();
        review.setProduct(product);
        review.setUser(user);
        review.setReviewContent(reviewContent);
        review.setProductQuality(productQuality);
        review.setReviewImage(reviewImage);
        review.setCreationTime(new Date()); // Lấy thời gian hiện tại

        // Lưu vào cơ sở dữ liệu
        return reviewUserRepository.save(review);
    }
}
