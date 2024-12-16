package vn.dodientu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findById(Long id);
    
    Page<User> findByFullNameContainingOrEmailContaining(String fullName, String email, Pageable pageable);
    @Query("SELECT u FROM User u " +
            "WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<User> searchUser(@Param("keyword") String keyword, Pageable pageable);
}
