package vn.dodientu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/home/{token}")
    public String showHome(@PathVariable String token, Model model) {
        // Kiểm tra token nếu cần thiết, bạn có thể thêm logic kiểm tra token ở đây
        model.addAttribute("token", token);
        return "customer/home"; // Chuyển đến trang home.jsp và hiển thị token trong model
    }
}
