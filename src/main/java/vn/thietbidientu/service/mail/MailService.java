package vn.thietbidientu.service.mail;

import vn.thietbidientu.dto.mail.MailDTO;
import vn.thietbidientu.entity.User;
import vn.thietbidientu.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;

    public void sendPasswordResetEmail(MailDTO mailDTO) {
        userRepository.findByEmail(mailDTO.getTo()).orElseThrow(() -> new RuntimeException("Không tìm thấy email"));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@example.com");
        message.setTo(mailDTO.getTo());
        message.setSubject("Khôi phục mật khẩu");
        message.setText(mailDTO.getText());
        javaMailSender.send(message);
    }
}
