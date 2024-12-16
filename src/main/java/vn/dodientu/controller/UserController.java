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
    
    // Hiển thị thông tin cá nhân của người dùng
    @GetMapping("/profile")
    public String getUserProfile(@RequestParam Long id, Model model) {
        User userDTO = userService.getUserDTO(id);
        model.addAttribute("user", userDTO); // Truyền dữ liệu UserDTO vào model
        return "customer/profile"; // Chuyển tới trang profile.jsp
    }

    // Cập nhật thông tin cá nhân
    @PostMapping("/updateProfile")
    public String updateUserProfile(@RequestParam Long id, User userDTO, Model model) {
        userService.updateUser(id, userDTO);
        model.addAttribute("user", userDTO); // Truyền lại thông tin sau khi cập nhật
        model.addAttribute("message", "Profile updated successfully");
        return "customer/profile"; // Sau khi cập nhật, quay lại trang profile.jsp
    }

}
