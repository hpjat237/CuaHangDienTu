package vn.thietbidientu.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private String productCode;
    private String productName;
    private String category;
    private Double cost;
    private String description;
    private String brand;
    private LocalDate manufactureDate;
    private LocalDate expirationDate;
    private String ingredient;
    private String how_to_use;
    private String volume;
    private String origin;
    private String image;
    private Long stock;
}
