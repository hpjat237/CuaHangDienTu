package vn.thietbidientu.payload.response;


import lombok.*;
import vn.thietbidientu.dto.ProductSummaryDTO;
import vn.thietbidientu.entity.Product;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryProductResponse {
    private Long categoryId;
    private String categoryName;
    private List<ProductSummaryDTO> products;
}
