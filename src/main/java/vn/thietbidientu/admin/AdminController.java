package vn.thietbidientu.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.thietbidientu.util.Logger;

import java.security.Principal;

//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    @GetMapping("/products")
//    public String manageProducts() {
//        return "admin/products";
//    }
//
//}
