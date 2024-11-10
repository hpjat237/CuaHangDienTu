package vn.dodientu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Trả về trang login.jsp hoặc login.html
    }

    @PostMapping("/login")
    public String login(HttpSession session) {
        // Giả sử bạn đã xác minh thông tin người dùng, nếu đăng nhập thành công, lưu thông tin người dùng vào session
        session.setAttribute("user", "userDetails"); // Ví dụ, lưu thông tin người dùng vào session

        return "redirect:/home"; // Chuyển hướng sau khi đăng nhập thành công
    }


    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Trả về trang register.jsp hoặc register.html
    }

    @PostMapping("/register")
    public String registerUser() {
        // Xử lý đăng ký người dùng và lưu thông tin vào cơ sở dữ liệu
        return "redirect:/login"; // Chuyển hướng sau khi đăng ký thành công
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password"; // Trả về trang forgot-password.jsp hoặc forgot-password.html
    }

    @PostMapping("/forgot-password")
    public String forgotPassword() {
        // Xử lý gửi mã OTP để thay đổi mật khẩu
        return "redirect:/login"; // Chuyển hướng về trang login sau khi gửi mã OTP
    }
}
