package vn.thietbidientu.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/revenue")
public class RevenueViewController {

    @GetMapping("/page")
    public String getRevenuePage() {
        return "admin/revenue"; // Trả về file HTML trong thư mục templates/admin/
    }
}
