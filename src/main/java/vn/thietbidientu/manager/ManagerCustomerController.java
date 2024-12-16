package vn.thietbidientu.manager;

import vn.thietbidientu.entity.Customer;
import vn.thietbidientu.entity.Order;
import vn.thietbidientu.service.impl.CartService;
import vn.thietbidientu.service.impl.CustomerService;
import vn.thietbidientu.service.impl.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/customers")
public class ManagerCustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;


    @PostMapping("/{id}/delete")
    public String removeCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomerById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xoá khách hàng thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/customers";
    }

//    @GetMapping("/search")
//    public String searchCustomersFilter(@RequestParam String keyword, Model model) {
//        Page<Customer> customers = customerService.searchByKeyword(keyword);
//        model.addAttribute("customers", customers);
//        model.addAttribute("keyword", keyword);
//        return "manager/customer-list"; // Tên file HTML
//    }

    @GetMapping("")
    public String getAllOfCustomers(@RequestParam(defaultValue = "", required = false) String keyword, @RequestParam(defaultValue = "0") int page, Model model) {
        // Tạo Pageable với trang hiện tại và số lượng khách hàng mỗi trang
        Pageable pageable = PageRequest.of(page, 5); // 5 khách hàng mỗi trang
//        Page<Customer> customerPage = customerService.findAll(pageable);
        Page<Customer> customerPage = customerService.searchByKeyword(keyword, pageable);

        // Thêm dữ liệu vào model
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("keyword", keyword);

        return "manager/customer-list"; // Tên file HTML
    }

    @PostMapping("/{id}/toggle-active")
    public String toggleCustomerActive(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.toggleActiveStatus(id);
        redirectAttributes.addFlashAttribute("success", "Thay đổi trạng thái thành công.");
        return "redirect:/admin/customers";
    }

    @GetMapping("/{id}")
    public String getCustomerDetails(@PathVariable Long id, Model model) {
        // Lấy thông tin khách hàng
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng."));

        // Lấy danh sách đơn hàng của khách hàng
        List<Order> orders = orderService.findByCustomerId(id);

        // Thêm thông tin vào model
        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);

        return "manager/customer-detail"; // Tên view

    }

    @PostMapping("/check-cart-empty")
    @ResponseBody
    public boolean isCustomerCartEmpty(@RequestParam Long customerId) {
        Optional<Customer> optionalCustomer = customerService.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Không tìm thấy khách hàng với ID: " + customerId); // Nếu không có customer, ném lỗi
        }

        Customer customer = optionalCustomer.get(); // Lấy giá trị Customer

        return cartService.findCartByCustomer(customer)
                .map(cart -> cart.getCartItems().isEmpty()) // Nếu tìm thấy giỏ hàng, kiểm tra rỗng
                .orElse(true); // Nếu không tìm thấy giỏ hàng, coi như giỏ hàng rỗng
    }

}
