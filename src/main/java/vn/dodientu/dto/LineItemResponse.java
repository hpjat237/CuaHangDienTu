package vn.dodientu.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class LineItemResponse {
    private String productName;
    private String urlImage;
    private double price;
    private int quantity;
    private double subtotal; // price * quantity
}
