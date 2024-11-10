package vn.dodientu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")  // Định nghĩa URL khi mở ứng dụng, đây sẽ là trang chủ
    public String getHome() {
		return "index";
    }
}
