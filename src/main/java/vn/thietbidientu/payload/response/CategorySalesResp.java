package vn.thietbidientu.payload.response;

import lombok.Data;

@Data
public class CategorySalesResp {
    private String categoryName;
    private Long totalSold;
    private Double percentage;
}
