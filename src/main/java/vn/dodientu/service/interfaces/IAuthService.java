package vn.dodientu.service.interfaces;

import vn.dodientu.dto.LoginRequest;
import vn.dodientu.dto.Response;
import vn.dodientu.model.User;

public interface IAuthService {
    Response login(LoginRequest request);
    Response register(User user);
    Response requestPasswordReset(String email);
    Response resetPassword(String email, String resetCode, String password);
}
