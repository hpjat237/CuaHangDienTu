package vn.dodientu.service.interfaces;

public interface IRedisService {

	// Lưu OTP với thời gian hết hạn
	void saveOtp(String email, String otp, long timeout);

	// Lấy OTP từ Redis
	String getOtp(String email);

	// Xóa OTP
	void deleteOtp(String email);

}