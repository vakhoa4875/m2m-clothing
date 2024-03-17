package m2m_phase2.clothing.clothing.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.repository.AccountRepo;
import m2m_phase2.clothing.clothing.service.AccountService;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo repo;
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return repo.save(account);
	}
	
	
	
	public void sendOTPEmail(String toEmail, String otp) {
	    try {
	        MimeMessage message = emailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setFrom("your_email_address@gmail.com");
	        helper.setTo(toEmail);
	        helper.setSubject("Mã OTP cho đăng ký tài khoản");
	        helper.setText("Mã OTP của bạn là: " + otp);

	        emailSender.send(message);
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Xử lý lỗi khi gửi email
	    }
	}
	
	


}
