package vn.thietbidientu.payload.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.thietbidientu.dto.CartItemForOrderDTO;
import vn.thietbidientu.dto.ShippingAddressDTO;
import vn.thietbidientu.entity.ShippingAddress;
import vn.thietbidientu.enums.PaymentMethod;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderRequest {

    @NotNull(message = "Address is required")
    private ShippingAddressDTO address; // Địa chỉ giao hàng

    @NotNull(message = "Payment method is required")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // Phương thức thanh toán (có thể là enum hoặc một đối tượng khác)

    @NotNull(message = "Cart item list is required")
    private Set<CartItemForOrderDTO> cartItemForOrderDTOS; // Danh sách sản phẩm và số lượng sản phẩm mà khách hàng muốn mua

    private Set<String> voucherCodes; // Danh sách mã voucher mà khách hàng muốn sử dụng
}