package vn.thietbidientu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.thietbidientu.entity.FeedbackResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFeedbackDTO {
    private Long productFeedbackId;
    private Long orderId;
    private String customerName;
    private LocalDateTime feedbackDate;
    private FeedbackResponse feedbackResponse;

}
