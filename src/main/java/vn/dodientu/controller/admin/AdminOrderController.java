package vn.dodientu.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
    @GetMapping
    public String index() {
        return "admin/order-list";
    }

    @GetMapping("/{id}")
    public String order(@PathVariable int id, Model model) {
        return "admin/order-details";
    }





}
