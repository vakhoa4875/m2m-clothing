package m2m_phase2.clothing.clothing.service.impl;

import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.repository.AccountRepo;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

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

    @Override
    public Account findByemail(String email) {
        // TODO Auto-generated method stub
        return repo.findByemail(email);
    }

    @Override
    public Account findByusername(String username) {
        // TODO Auto-generated method stub
        return repo.findByusername(username);
    }

    public void sendOTPEmail(String toEmail, String otp) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("kaisamaslain+Nerdyers@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Mã OTP cho đăng ký tài khoản");
            helper.setText("Mã OTP của bạn là: " + otp);
            emailSender.send(message);
            session.setAttribute("otp", otp);
            session.setAttribute("email", toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyOTP(String otp) {
        String sessionOtp = (String) session.getAttribute("otp");
        return sessionOtp != null && sessionOtp.equals(otp);
    }

    public String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    public boolean checkUsername(Account account, Model model) {
        if (findByusername(account.getUsername()) != null) {
            String messError = "Username already exists";
            model.addAttribute("messError", messError);
            return false;
        }
        return true;
    }

    public boolean checkEmail(Account account, Model model) {
        if (findByemail(account.getEmail()) != null) {
            String messError = "Email already exists";
            model.addAttribute("messError", messError);
            return false;
        }
        return true;
    }

    public boolean checkFillRegister(Model model, String... args) {
        for (String string : args) {
            if (string.equals("")) {
                String messError = "Please fill in complete information";
                model.addAttribute("messError", messError);
                return false;
            }
        }
        return true;

    }

    public boolean checkFillOtp(Model model, String... args) {
        for (String string : args) {
            if (string.equals("")) {
                String messError = "Please fill in complete otp";
                model.addAttribute("messError", messError);
                return false;
            }
        }
        return true;
    }

    public String concatOtp(String... args) {
        String otpConcatSuccess = "";
        for (int i = 0; i < args.length; i++) {
            otpConcatSuccess += args[i];
        }
        return otpConcatSuccess;
    }

    @Override
    public Account findByuserId(Integer id) {
        return repo.findByuserId(id);
    }

    @Override
    public boolean isDisable(Account account) {
        // TODO Auto-generated method stub
        return account != null && account.isDisable();
    }

//	@Override
//	public List<UserM> findAll() throws SQLException {
//		List<Account> listAccount = repo.findAll();
//		return UserM.convertListAccountToListUserM(listAccount);
//	}


    public void sendLinkEmail(String toEmail, String resetPasswordUrl) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("kaisamaslain+Nerdyers@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Đường link để lấy lại mật khẩu !!!");
            helper.setText("Vui lòng nhấp vào đường link sau để đặt lại mật khẩu: " + resetPasswordUrl);
            emailSender.send(message);
            session.setAttribute("resetPasswordUrl", resetPasswordUrl); // Lưu đường dẫn vào session
            session.setAttribute("email", toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendUrl() {

        String resetPasswordUrl = "http://localhost:8083/ConfirmPassword-Forgot-mk";
        return resetPasswordUrl;
    }


    public String submitLogin(Account accountRequest, Model model, HttpSession session) {
        String email = accountRequest.getEmail();
        String password = accountRequest.getHashedPassword();
        Account existingAccount = findByemail(email);
        if (existingAccount == null) {
            model.addAttribute("error", "Tài khoản không tồn tại");
            return "swappa/assests/html/acc_login";
        }
        boolean passwordMatch = PasswordEncoderUtil.verifyPassword(password, existingAccount.getHashedPassword());
        if (!passwordMatch) {
            model.addAttribute("error", "Mật khẩu không đúng");
            return "swappa/assests/html/acc_login";
        }
        // Kiểm tra xem tài khoản có bị vô hiệu hóa không
        if (isDisable(existingAccount)) {
            model.addAttribute("error", "Tài khoản của bạn tạm thời bị vô hiệu hóa");
            return "swappa/assests/html/acc_login";
        }
        // Lưu thông tin đăng nhập vào session hoặc làm bất kỳ xử lý nào khác cần thiết
        session.setAttribute("loggedInUser", accountRequest.getEmail());
		session.setAttribute("loggedInPassword", accountRequest.getHashedPassword());

		return "swappa/assests/html/trangchu";
    }
	@Override
	public Account findByUsernameAndEmail(String username, String email){
		return repo.findByUsernameAndEmail(username,email);
	}

	public boolean isLoggedIn(HttpSession session) {
		// Kiểm tra xem session có chứa thông tin người dùng hay không
		return session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInPassword") != null;
	}
}
