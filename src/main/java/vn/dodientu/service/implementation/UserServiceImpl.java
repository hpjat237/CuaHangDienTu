package vn.dodientu.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.dodientu.model.User;
import vn.dodientu.repository.UserRepository;
import vn.dodientu.service.interfaces.ICrudService;
import vn.dodientu.service.interfaces.IEmailService;
import vn.dodientu.service.interfaces.IRedisService;
import vn.dodientu.service.interfaces.IUserService;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional // Đảm bảo tất cả các phương thức đều được thực hiện trong một transaction
public class UserServiceImpl implements IUserService, ICrudService<User, Long> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRedisService redisService;
    
    @Autowired
    private IEmailService emailService;
    
    @Override
    public User add(User entity) {
        String encodedPassword = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);
        if(userRepository.existsByEmail(entity.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAllPagination(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public User update(User updatedEntity) {
        Optional<User> optUser = userRepository.findById(updatedEntity.getId());
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User existingUser = optUser.get();

        // Update fields if not null
        if (updatedEntity.getFullName() != null) {
            existingUser.setFullName(updatedEntity.getFullName());
        }
        if (updatedEntity.getEmail() != null) {
            existingUser.setEmail(updatedEntity.getEmail());
        }
        if (updatedEntity.getPassword() != null) {
            existingUser.setPassword(updatedEntity.getPassword());
        }
        if (updatedEntity.getPhone() != null) {
            existingUser.setPhone(updatedEntity.getPhone());
        }
        if (updatedEntity.getAddress() != null) {
            existingUser.setAddress(updatedEntity.getAddress());
        }
        if (updatedEntity.getImage_url() != null) {
            existingUser.setImage_url(updatedEntity.getImage_url());
        }
        if (updatedEntity.getRole() != null) {
            existingUser.setRole(updatedEntity.getRole());
        }

        // Save and return the updated user
        return userRepository.save(existingUser);
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void registerUser(User user) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(user);
        
        // Gửi OTP qua email
        sendOtp(user.getEmail());
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void sendOtp(String email) {
        // Tạo mã OTP
        String otp = generateOtp();

        // Lưu OTP vào Redis với thời gian hết hạn 5 phút
        redisService.saveOtp(email, otp, 5);

        // Gửi OTP qua email
        String subject = "OTP Verification Code";
        String content = "Your OTP verification code is: " + otp;
        emailService.sendEmail(email, subject, content);
    }

    private String generateOtp() {
        Random rand = new Random();
        return String.format("%06d", rand.nextInt(999999));
    }
}
