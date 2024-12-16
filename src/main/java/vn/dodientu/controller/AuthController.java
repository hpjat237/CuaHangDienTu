package vn.dodientu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.dodientu.dto.LoginRequest;
import vn.dodientu.service.interfaces.IAuthService;
import vn.dodientu.service.interfaces.IUserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Trang đăng nhập
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Chuyển tới login.jsp
    }

    // Xử lý yêu cầu đăng nhập
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            LoginRequest loginRequest = new LoginRequest(username, password);
            authService.login(loginRequest); // Giả sử method login() trong IAuthService xử lý đăng nhập
            return "redirect:/"; // Đăng nhập thành công, chuyển đến trang chủ hoặc trang bạn muốn
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Quay lại trang login nếu đăng nhập thất bại
        }
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Chuyển tới register.jsp
    }

    // Xử lý yêu cầu đăng ký
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email, 
                           @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        try {
            // Kiểm tra xem mật khẩu và xác nhận mật khẩu có trùng khớp không
            if (!password.equals(confirmPassword)) {
                model.addAttribute("error", "Passwords do not match");
                return "register"; // Quay lại trang register nếu mật khẩu không trùng khớp
            }
            userService.registerUser(username, email, passwordEncoder.encode(password));
            return "redirect:/auth/login"; // Sau khi đăng ký thành công, chuyển tới trang đăng nhập
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register"; // Quay lại trang register nếu có lỗi
        }
    }
    
    @GetMapping("/forgot-password")
    public String forgotpasswordPage() {
        return "forgot-password"; // Chuyển tới register.jsp
    }
    
    // Xử lý yêu cầu đặt lại mật khẩu
    @PostMapping("/forgot-password")
    public String sendForgotPasswordRequest(@RequestParam String email, Model model) {
        try {
            authService.requestPasswordReset(email);
            model.addAttribute("message", "Password reset email has been sent.");
            return "login"; // Quay lại trang login sau khi gửi email đặt lại mật khẩu
        } catch (Exception e) {
            model.addAttribute("error", "Failed to send password reset email: " + e.getMessage());
            return "login"; // Quay lại trang login nếu có lỗi
        }
    }

    // Xử lý đặt lại mật khẩu
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email,
                                 @RequestParam String resetCode,
                                 @RequestParam String password, Model model) {
        try {
            authService.resetPassword(email, resetCode, password);
            model.addAttribute("message", "Your password has been reset successfully.");
            return "login"; // Quay lại trang login sau khi đặt lại mật khẩu thành công
        } catch (Exception e) {
            model.addAttribute("error", "Failed to reset password: " + e.getMessage());
            return "login"; // Quay lại trang login nếu có lỗi
        }
    }
}
