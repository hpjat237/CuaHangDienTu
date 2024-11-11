package vn.dodientu.service;

import vn.dodientu.model.User;

public interface IUserService {
    User findByEmail(String email);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User saveUser(User user);
    void updatePassword(User user, String newPassword);
}
