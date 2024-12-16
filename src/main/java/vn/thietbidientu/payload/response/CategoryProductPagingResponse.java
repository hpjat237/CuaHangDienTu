package vn.thietbidientu.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.thietbidientu.dto.ProductSummaryDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryProductPagingResponse {
    private Long categoryId;
    private String categoryName;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private int totalProducts;
    private List<ProductSummaryDTO> products;
}
