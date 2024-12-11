package vn.dodientu.service.interfaces;

import vn.dodientu.dto.UserDetailsImpl;

public interface IUserService {

    // Phương thức để đăng ký người dùng mới
    void registerUser(String username, String email, String password);

    // Phương thức để kiểm tra người dùng đã tồn tại chưa
    boolean userExists(String username);

    // Phương thức để kiểm tra email người dùng đã tồn tại chưa
    boolean emailExists(String email);
    void updateUserProfile(UserDetailsImpl userDTO);
    UserDetailsImpl getUserProfile(String email);
}
