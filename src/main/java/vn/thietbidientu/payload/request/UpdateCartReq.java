package vn.thietbidientu.payload.request;

import lombok.Data;

@Data
public class UpdateCartReq {
    private Long cartItemId;
    private int quantity;
}
