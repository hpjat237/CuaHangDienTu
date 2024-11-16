package vn.dodientu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.dodientu.dto.LoginRequest;
import vn.dodientu.dto.Response;
import vn.dodientu.service.interfaces.IAuthService;
import vn.dodientu.service.interfaces.IEmailService;
import vn.dodientu.service.interfaces.IUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IEmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest request) {
        try{
            Response response = authService.login(request);
            return ResponseEntity.ok().body(response);
        }catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new Response(null, e.getMessage()));
        }
    }

    // Xử lý yêu cầu đặt lại mật khẩu
    @PostMapping("/forgot-password")
    public ResponseEntity<Response> sendForgotPasswordRequest(@RequestParam String email) {
        try{
            return ResponseEntity.ok().body(authService.requestPasswordReset(email));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new Response(null, e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(null, "An unexpected error occurred. Please try again later."));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Response> resetPassword(@RequestParam String email,
                                                  @RequestParam String resetCode,
                                                  @RequestParam String password) {
        try{
            return ResponseEntity.ok().body(authService.resetPassword(email, resetCode, password));
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new Response(null, e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(null, "An unexpected error occurred. Please try again later."));
        }
    }
}