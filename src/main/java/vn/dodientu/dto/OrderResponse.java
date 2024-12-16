package vn.dodientu.dto;

import java.time.LocalDateTime;
import java.util.List;

import vn.dodientu.model.Address;



public class OrderResponse {
	private Long orderId;
    private String name;
    private String phone;
    private Address address;
    private LocalDateTime orderDate;
    private LocalDateTime receiveDate;
    private String shippingStatus;
    private String paymentStatus;
    private String note;
    private double totalAmount;
    private List<LineItemResponse> items;
}
