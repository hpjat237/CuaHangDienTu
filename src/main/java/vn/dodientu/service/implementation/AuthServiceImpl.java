package vn.dodientu.service.implementation;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Value("${reset.code.duration}")
    private String resetCodeDuration;

    @Override
    public Response login(LoginRequest request) {
        try{
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Set authentication in the context
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Generate JWT token
        String jwtToken = jwtUtil.generateToken(auth);

        // Create a response DTO with the token
        Response response = new Response();
        response.setResult(jwtToken);
        response.setMessage("Login successful");

        return response;

    } catch (BadCredentialsException e) {
        // Return error response for incorrect credentials
            throw new BadCredentialsException("Invalid email or password", e);
        }
    }


    @Override
    public Response register(User user) {
        return null;
    }

    @Override
    public Response requestPasswordReset(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optUser.isEmpty()) {
            throw new IllegalArgumentException("User not found with the provided email");
        }
        try{
            User user = optUser.get();
            String code = generateRandomString(8);
            user.setPassword(code);
            user.setResetCodeExpiry(Instant.now().plus(Duration.ofMinutes(Integer.parseInt(resetCodeDuration))));
            userRepository.save(user);

            emailService.sendEmail(email,"Password Reset Request" ,generateResetPasswordEmailBody(code, Integer.parseInt(resetCodeDuration)));
            return new Response(code,"Account sent to " + email + ". Please check your mailbox.");
        }catch (Exception e){
            return new Response(e.getMessage(),"An error occurred while trying to send email.");
        }

    }

    @Override
    public Response resetPassword(String email, String resetCode, String password) {
        Optional<User> optUser = userRepository.findByResetCode(resetCode);
        if (optUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid reset code. Please check again.");
        }
        try {
            User user = optUser.get();

            if (Instant.now().isAfter(user.getResetCodeExpiry())) {
                user.setResetCode(null);
                user.setResetCodeExpiry(null);
                userRepository.save(user);
                return new Response(null, "Reset code expired. Please request another code.");
            }

            user.setPassword(password);
            userRepository.save(user);
            return new Response(null, "Password successfully changed.");
        }catch(Exception e){
            return new Response(e.getMessage(), "An error occurred while attempting to reset your password. Please try again.");
        }
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }


    private String generateResetPasswordEmailBody(String token, int duration) {
        return """
        <html>
            <body>
                <h2>Password Reset Request</h2>
                <p>Your Password reset code is: %s</p>
                <p>The code will expire in %s minutes</p>
            </body>
        </html>
        """.formatted(token, duration);
    }
}
