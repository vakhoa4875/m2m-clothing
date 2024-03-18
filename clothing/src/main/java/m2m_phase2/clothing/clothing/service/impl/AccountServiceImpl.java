package m2m_phase2.clothing.clothing.service.impl;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.repository.AccountRepo;
import m2m_phase2.clothing.clothing.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo repo;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private HttpSession session;

    @Override
    public Account saveAccount(Account account) {
        // TODO Auto-generated method stub
        return repo.save(account);
    }

    public void sendOTPEmail(String toEmail, String otp) {
        try {
        	MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("hieuphung2111@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Mã OTP cho đăng ký tài khoản");
            helper.setText("Mã OTP của bạn là: " + otp);

            emailSender.send(message);

            // Lưu mã OTP vào session hoặc database để kiểm tra xác thực sau này
            session.setAttribute("otp", otp);
            session.setAttribute("email", toEmail);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi khi gửi email
        }
    }

    // Kiểm tra mã OTP nhập vào có trùng khớp với mã OTP đã gửi hay không
    public boolean verifyOTP(String otp) {
        String sessionOtp = (String) session.getAttribute("otp");
        return sessionOtp != null && sessionOtp.equals(otp);
    }
    
    // Phương thức để tạo mã OTP ngẫu nhiên gồm 6 chữ số
    public String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }
    
    

}
