package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Tìm người dùng theo email
    User findByEmail(String email);
}
