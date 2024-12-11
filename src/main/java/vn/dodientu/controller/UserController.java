package vn.dodientu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dodientu.dto.UserDetailsImpl;
import vn.dodientu.model.User;
import vn.dodientu.service.implementation.UserServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    
 // Hiển thị thông tin cá nhân
    @GetMapping("/profile")
    public String viewProfile(Model model) {
        // Giả sử email của người dùng là user@example.com
        String email = "user@example.com";
        UserDetailsImpl userDTO = userService.getUserProfile(email);
        model.addAttribute("user", userDTO);
        return "profile";
    }

    // Cập nhật thông tin cá nhân
    @PostMapping("/updateProfile")
    public String updateProfile(UserDetailsImpl updatedUser) {
        userService.updateUserProfile(updatedUser);
        return "redirect:/profile";
    }

}
