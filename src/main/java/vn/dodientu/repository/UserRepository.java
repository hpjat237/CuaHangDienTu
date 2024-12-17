package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.dodientu.model.User;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Tìm người dùng qua email
    Optional<User> findByUsername(String username); // Tìm người dùng qua tên người dùng (username)
    boolean existsByUsername(String username); // Kiểm tra sự tồn tại của username
    boolean existsByEmail(String email); // Kiểm tra sự tồn tại của email
    Optional<User> findByResetCode(String resetCode);
}
