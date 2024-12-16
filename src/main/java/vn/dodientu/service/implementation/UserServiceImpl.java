package vn.dodientu.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dodientu.dto.UserDetailsImpl;
import vn.dodientu.model.User;
import vn.dodientu.repository.UserRepository;
import vn.dodientu.service.interfaces.ICrudService;
import vn.dodientu.service.interfaces.IUserService;

import java.util.Optional;

@Service
@Transactional // Đảm bảo tất cả các phương thức đều được thực hiện trong một transaction
public class UserServiceImpl implements IUserService, ICrudService<User, Long> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


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
    public void registerUser(String username, String email, String password) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(password);

        // Tạo đối tượng người dùng mới
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(newUser);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    // Lấy thông tin người dùng từ cơ sở dữ liệu và trả về UserDTO
    public User getUserDTO(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }
 // Cập nhật thông tin người dùng
    public void updateUser(Long id, User userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        userRepository.save(user);
    }

    // Chuyển đổi từ User Entity sang UserDTO
    public User convertToDTO(User user) {
        return new User(user.getFullName(), user.getEmail(), user.getPhone(), user.getAvatarUrl());
    }

}
