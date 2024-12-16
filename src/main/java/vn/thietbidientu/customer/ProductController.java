package vn.thietbidientu.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import vn.thietbidientu.config.AuthenticationHelper;
import vn.thietbidientu.entity.ProductFeedback;
import vn.thietbidientu.image.ImageService;
import vn.thietbidientu.payload.request.AddProductFeedbackReq;
import vn.thietbidientu.service.impl.ProductService;
import vn.thietbidientu.util.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/customer/product")
public class ProductController {
    @Autowired
    AuthenticationHelper authenticationHelper;
    @Autowired
    ProductService productService;
    @Autowired
    ImageService imageService;

    //add review
    @PostMapping(value = "/addReview", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<?> addReview(@RequestPart(value = "review") @NotNull String addProductFeedbackReqStr,
                                       @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Long customerId = authenticationHelper.getUserId();
        String imageUrl = null;
        if (image != null) {
//            imageUrl = imageService.saveImage(image);  // Giả sử bạn có dịch vụ lưu trữ ảnh
            imageUrl = imageService.uploadCloudinary(image);  // Giả sử bạn có dịch vụ lưu trữ ảnh
        }
        ObjectMapper objectMapper = new ObjectMapper();
        AddProductFeedbackReq addProductFeedbackReq = objectMapper.readValue(addProductFeedbackReqStr, AddProductFeedbackReq.class);
        addProductFeedbackReq.setImage(imageUrl);
        productService.addFeedback(customerId, addProductFeedbackReq);
        return ResponseEntity.ok("Review added successfully");
    }

    @GetMapping("/review")
    @ResponseBody
    public ResponseEntity<?> getReview(@RequestParam Long productId, @RequestParam Long orderId) {
        Long customerId = authenticationHelper.getUserId();
        Logger.log(orderId, productId);
        return ResponseEntity.ok(productService.getFeedback(customerId, orderId, productId));
    }
}
