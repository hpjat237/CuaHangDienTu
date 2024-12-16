package vn.dodientu.service.interfaces;

import hcmute.edu.vn.entity.Order;
import hcmute.edu.vn.entity.Shop;
import hcmute.edu.vn.entity.User;

import java.util.List;

public interface IOrderService {
    // Phương thức thêm đơn hàng
    String addOrder(Integer userId, Integer productId, Integer productColorId, Integer quantity);

    List<Order> getOrdersByUser(User user);

    List<Order> getOrdersByShop(Shop shop);

    Order save(Order order);

    Order findById(Integer id);
}
