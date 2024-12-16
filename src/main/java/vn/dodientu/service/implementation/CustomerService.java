package vn.dodientu.service.implementation;

import vn.dodientu.entity.Cart;
import vn.dodientu.entity.Customer;
import vn.dodientu.entity.Order;
import vn.dodientu.entity.User;
import vn.dodientu.repository.CustomerRepository;
import vn.dodientu.repository.UserRepository;
import vn.dodientu.service.interfaces.ICustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void updateActiveStatus(Long userId, boolean status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + userId));
        user.setActive(status);
        userRepository.save(user); // Lưu thay đổi
    }


    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }



    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> searchByKeyword(String keyword,Pageable pageable) {
        return customerRepository.findByFullnameContainingOrEmailContaining(keyword, keyword,pageable);
    }

    @Override
    public void lockAccount(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng."));
        customer.setActive(false);
        customerRepository.save(customer);
    }

    @Override
    public void unlockAccount(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng."));
        customer.setActive(true);
        customerRepository.save(customer);
    }

    @Override
    public void toggleActiveStatus(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng."));
        customer.setActive(!customer.getActive());
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        // Tìm khách hàng
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng."));

        // Kiểm tra giỏ hàng (Cart) liên kết với khách hàng
        Optional<Cart> cartOptional = cartService.findCartByCustomer(customer);
        if (cartOptional.isPresent() && !cartOptional.get().getCartItems().isEmpty()) {
            throw new RuntimeException("Không thể xoá: Khách hàng có giỏ hàng chưa xử lý.");
        }

        // Kiểm tra đơn hàng (Orders) liên kết với khách hàng
        List<Order> orders = orderService.findByCustomerId(customerId);
        if (!orders.isEmpty()) {
            throw new RuntimeException("Không thể xoá: Khách hàng có đơn hàng liên quan.");
        }

        // Nếu không có ràng buộc, xoá khách hàng
        customerRepository.delete(customer);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
