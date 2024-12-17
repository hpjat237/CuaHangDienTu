package vn.dodientu.dto;

import lombok.*;
import vn.thietbidientu.entity.Role;
import vn.thietbidientu.enums.Gender;
import vn.thietbidientu.enums.RoleEnum;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private String phone;
    private Role role;
    private AddressForOrderDTO address; // Địa chỉ chính
    private List<AddressForOrderDTO> addresses; // Danh sách địa chỉ
    private String image;
    private LocalDate birthDate;


    public UserDTO(Long userId, String name, String email, String password, Gender gender, String phone, Role role, AddressForOrderDTO address, String image, LocalDate birthDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.image = image;
        this.birthDate = birthDate;
    }
}