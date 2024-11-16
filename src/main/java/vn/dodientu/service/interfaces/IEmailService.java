package vn.dodientu.service.interfaces;

public interface IEmailService {
    void sendEmail(String to, String subject, String content);
}
