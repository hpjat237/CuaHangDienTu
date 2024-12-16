package vn.dodientu.service.interfaces;


import org.springframework.data.domain.Page;



import vn.dodientu.dto.CheckoutResponse;
import vn.dodientu.dto.LineItemRequest;
import vn.dodientu.dto.OrderResponse;
import vn.dodientu.model.Order;

import java.util.List;

public interface IOrderService {
	 	//public OrderResponse toOrderResponse(Order order);
	    public Page<Order> getAllOrders(int page, int size);
	    public Order getOrderById(Long id);
	    public Page<Order> getAllOrdersByUserId(Long id, int page, int size);
	    public Page<Order> searchOrders(String keyword, int page, int size);
	  //  public Order createOrder(String token, CheckoutResponse response);
	  //  public CheckoutResponse checkOutOrder(String token, List<LineItemRequest> cartItems);
	    public void setDelivering(Long id);
	    public void setDeliveried(Long id);
}
