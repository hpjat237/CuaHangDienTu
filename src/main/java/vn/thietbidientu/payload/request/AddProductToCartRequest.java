package vn.thietbidientu.payload.request;//package com.cosmeticsellingwebsite.payload.requestdabo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductToCartRequest {

    @NotNull(message = "productCode is required")
    private String productCode;
    @NotNull
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Long quantity;
}
