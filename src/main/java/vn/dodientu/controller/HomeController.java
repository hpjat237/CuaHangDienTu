package vn.dodientu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/")  // Định nghĩa URL khi mở ứng dụng, đây sẽ là trang chủ
    public String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() { return "login"; }

    @GetMapping("/register")
    public String getRegister() { return "register"; }
}
