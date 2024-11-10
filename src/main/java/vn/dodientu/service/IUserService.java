package vn.dodientu.service;

import vn.dodientu.model.User;

public interface IUserService {
    User findByEmail(String email);  // Phương thức tìm người dùng theo email
    void registerUser(String password);  // Phương thức đăng ký người dùng
}
