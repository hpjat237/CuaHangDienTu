package vn.thietbidientu.dto;

import vn.thietbidientu.entity.FeedbackResponse;
import vn.thietbidientu.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackDTO {
    private Long productFeedbackId;
    private String customerName;
    private String comment;
    private LocalDateTime feedbackDate;
    private Long customerId;
    private String image;
    private Double rating;
    private FeedbackResponse feedbackResponse;
    private String productSnapshotName;

}
