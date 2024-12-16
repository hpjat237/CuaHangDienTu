package vn.dodientu.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItemRequest {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private double total;
}
