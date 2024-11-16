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
}
