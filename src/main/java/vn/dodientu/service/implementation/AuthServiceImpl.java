package vn.dodientu.service.implementation;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.dodientu.Jwt.JwtUtil;
import vn.dodientu.dto.LoginRequest;
import vn.dodientu.dto.Response;
import vn.dodientu.model.User;
import vn.dodientu.repository.UserRepository;
import vn.dodientu.service.interfaces.IAuthService;
import vn.dodientu.service.interfaces.IEmailService;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final IEmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Tiêm PasswordEncoder vào đây

    @Override
    public Response login(LoginRequest request) {
        try {
            // Kiểm tra username hoặc email
            Optional<User> userOpt = userRepository.findByUsernameOrEmail(request.getUsername(), request.getUsername());
            if (userOpt.isEmpty()) {
                throw new BadCredentialsException("Tài khoản sai");
            }

            User user = userOpt.get();

            // So sánh mật khẩu nhập vào với mật khẩu đã mã hóa trong cơ sở dữ liệu
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Mật khẩu sai");
            }

            // Xác thực thông tin đăng nhập
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getUsername(),  // Đảm bảo truyền username (dù đăng nhập bằng email)
                    request.getPassword()
                )
            );

            // Set Authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Tạo token JWT
            String jwtToken = jwtUtil.generateToken(auth);

            // Lấy vai trò người dùng
            String role = auth.getAuthorities().stream()
                              .map(GrantedAuthority::getAuthority)
                              .findFirst()
                              .orElse("ROLE_USER");

            // Tạo response chứa JWT và vai trò
            Response response = new Response();
            response.setResult(jwtToken);
            response.setRole(role);  // Thêm vai trò vào trong Response
            response.setMessage("Login successful");

            return response;

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials", e);	
        }
    }


	@Override
	public Response register(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response requestPasswordReset(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response resetPassword(String email, String resetCode, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
