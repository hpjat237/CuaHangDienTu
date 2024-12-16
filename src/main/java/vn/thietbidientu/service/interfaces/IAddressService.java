package vn.thietbidientu.service.interfaces;

import java.util.List;

import vn.thietbidientu.dto.AddressForOrderDTO;

public interface IAddressService {
    boolean updateAddressForUser(AddressForOrderDTO addressModel, Long addressID);

    // Lấy danh sách địa chỉ của người dùng
    List<AddressForOrderDTO> getAddressesForUser(Long userId);

    // Lấy thông tin địa chỉ cụ thể
    AddressForOrderDTO getAddressById(Long addressId);

    // Lưu địa chỉ (thêm hoặc cập nhật)
    boolean saveAddressForUser(AddressForOrderDTO addressDTO, Long userId);

    void deleteAddressById(Long id);

    boolean checkAddressBelongToUser(Long id, Long userID);
}
