package vn.thietbidientu.service.interfaces;

import vn.thietbidientu.dto.UserDTO;
import vn.thietbidientu.entity.User;

import java.util.Optional;

public interface IPersonalInformationService {
    UserDTO fetchPersonalInfo(Long userID);
    boolean savePersonalInfo(UserDTO userModel, Long userID);
    Optional<User> findUserById(Long userID);
    boolean updatePassword(Long userId, String encodedNewPassword);

}
