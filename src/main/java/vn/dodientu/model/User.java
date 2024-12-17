package vn.dodientu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
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

    @ManyToOne(fetch = FetchType.EAGER) // One role per user, but roles can be shared among users
    @JoinColumn(name = "role_id") // Specifies the foreign key column
    private Role role;
    
    public User() {}

    public User(String name, String email, String phone, String avatar) {
        this.fullName = name;
        this.email = email;
        this.phone = phone;
        this.image_url = avatar;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPhoneNumber() {
        return phone;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getAvatarUrl() {
        return image_url;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.image_url = avatarUrl;
    }
}
