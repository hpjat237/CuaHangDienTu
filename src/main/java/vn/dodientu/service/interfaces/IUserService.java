package vn.dodientu.service.interfaces;

import vn.dodientu.model.User;

public interface IUserService {

    // Phương thức để đăng ký người dùng mới
    void registerUser(User user);
    
    // Phương thức để xử lý gửi OTP
    void sendOtp(String email);

    // Phương thức để kiểm tra người dùng đã tồn tại chưa
    boolean userExists(String username);

    // Phương thức để kiểm tra email người dùng đã tồn tại chưa
    boolean emailExists(String email);
    
    User update(User updatedEntity);
}
