package vn.dodientu.service.implementation;

import vn.dodientu.model.Order;
import vn.dodientu.model.OrderShippingStatus;
import vn.dodientu.repository.AddressRepository;
import vn.dodientu.repository.OrderRepository;
import vn.dodientu.service.interfaces.IOrderService;
import vn.dodientu.service.interfaces.IProductService;
import vn.dodientu.service.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
	@Override
	public Page<Order> getAllOrders(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return orderRepository.findAll(pageable);
	}
	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}
	@Override
	public Page<Order> getAllOrdersByUserId(Long id, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAllByUserId(id, pageable);
	}
	@Override
	public Page<Order> searchOrders(String keyword, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

        OrderShippingStatus status = null;

        // Kiểm tra nếu keyword khớp với enum
        try {
            status = OrderShippingStatus.valueOf(keyword.toUpperCase());
        } catch (IllegalArgumentException ex) {
            // Nếu keyword không khớp enum, bỏ qua
        }

        return orderRepository.searchByKeywordAndStatus(keyword, status, pageable);
	}
	@Override
	public void setDelivering(Long id) {
		Order order = orderRepository.findById(id).get();
        order.setShippingStatus(OrderShippingStatus.DELIVERING);
        orderRepository.save(order);
	}
	@Override
	public void setDeliveried(Long id) {
		Order order = orderRepository.findById(id).get();
        order.setShippingStatus(OrderShippingStatus.DELIVERIED);
        orderRepository.save(order);
	}
    

	

	
}
