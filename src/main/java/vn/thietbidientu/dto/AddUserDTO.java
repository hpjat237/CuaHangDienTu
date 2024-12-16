package vn.thietbidientu.dto;

import lombok.Data;
import vn.thietbidientu.entity.Role;
import vn.thietbidientu.entity.User;
import vn.thietbidientu.enums.Gender;

@Data
public class AddUserDTO {
    protected Long userId;
    protected String username;
    protected String password;
    protected String email;
    protected String fullname;
    protected String phone;
    protected Gender gender;
    protected String image;
    protected Boolean active;
    protected Role role;
}
