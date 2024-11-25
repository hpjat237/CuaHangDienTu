package vn.dodientu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.dodientu.repository.RoleRepository;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")  // Đặt tên bảng trong cơ sở dữ liệu
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động tăng ID
    private Long id;

    private String username;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String image_url;
    private String resetCode;
    private Instant resetCodeExpiry;
    @Column(name = "is_verified")
    private Boolean isVerified = false; // Mặc định là false, khi chưa xác thực

    @ManyToOne(fetch = FetchType.EAGER) // One role per user, but roles can be shared among users
    @JoinColumn(name = "role_id") // Specifies the foreign key column
    private Role role;
   
 // Gán role bằng tên role
    public void setRole(String roleName, RoleRepository roleRepository) {
        this.role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }
    
    

}
