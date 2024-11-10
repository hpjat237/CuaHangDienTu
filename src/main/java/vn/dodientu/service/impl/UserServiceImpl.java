package vn.dodientu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.dodientu.model.User;
import vn.dodientu.repository.UserRepository;
import vn.dodientu.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;  // Inject thông qua field
    
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String password) {
        String encodedPassword = passwordEncoder.encode(password);
        // Lưu mật khẩu đã mã hóa vào cơ sở dữ liệu
    }

	public User findByEmail(String email) {
        return userRepository.findByEmail(email);  // Tìm người dùng qua email
	}
	
	public User saveUser(User user) {
        return userRepository.save(user);
    }
}

