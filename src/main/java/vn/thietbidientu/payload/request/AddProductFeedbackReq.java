package vn.thietbidientu.payload.request;

import lombok.Data;
import vn.thietbidientu.entity.ProductFeedback;

@Data
public class AddProductFeedbackReq {
    private Long orderId;
    private Long productId;
    private String comment;
    private Double rating;
    private String image;
}
