package vn.dodientu.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.dodientu.model.User;
import vn.dodientu.service.IUserService;

@Controller
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Hiển thị trang đăng nhập
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Trả về trang login.html
    }

    // Xử lý đăng nhập
    @PostMapping("/auth/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("user", user); // Lưu thông tin người dùng vào session
            return "redirect:/home"; // Chuyển hướng đến trang chủ
        }
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác");
        return "login"; // Quay lại trang đăng nhập nếu thất bại
    }

    // Hiển thị trang đăng ký
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Trả về trang register.html
    }

    // Xử lý đăng ký
    @PostMapping("/auth/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "register"; // Quay lại trang đăng ký nếu tên đăng nhập đã tồn tại
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
        userService.saveUser(user); // Lưu thông tin người dùng mới vào cơ sở dữ liệu
        return "redirect:/login"; // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
    }

    // Hiển thị trang quên mật khẩu
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password"; // Trả về trang forgot-password.html
    }

    // Xử lý yêu cầu đặt lại mật khẩu
    @PostMapping("/auth/forgot-password")
    public String forgotPassword(@RequestParam String email, Model model) {
        User user = userService.findByEmail(email);
        if (user != null) {
            // Xử lý logic gửi email reset password hoặc cập nhật lại mật khẩu
            model.addAttribute("message", "Yêu cầu đặt lại mật khẩu đã được gửi đến email của bạn.");
        } else {
            model.addAttribute("error", "Email không tồn tại trong hệ thống.");
        }
        return "forgot-password";
    }
}
