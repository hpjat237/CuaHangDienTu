package vn.thietbidientu.dto;

import lombok.Data;

@Data
public class OrderLineDTO {
    private ProductSnapshotDTO productSnapshot;
    private Long quantity;
}
