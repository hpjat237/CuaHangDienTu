package vn.thietbidientu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.thietbidientu.service.impl.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;
}
