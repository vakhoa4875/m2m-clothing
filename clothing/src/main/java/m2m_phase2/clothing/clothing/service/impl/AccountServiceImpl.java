package m2m_phase2.clothing.clothing.service.impl;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.constant.AccountEnum;
import m2m_phase2.clothing.clothing.data.dto.AccountDto;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.data.variable.StaticVariable;
import m2m_phase2.clothing.clothing.repository.AccountGGRepo;
import m2m_phase2.clothing.clothing.repository.AccountRepo;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo repo;
    @Autowired
    private AccountGGRepo accountGGRepo;
    @Autowired
    private JavaMailSender emailSender;
    private final HttpSession session;
//    private final UserService userService;

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

    @Override
    public void sendOTPEmail(String toEmail, String otp,String subject) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("kaisamaslain+Nerdyers@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
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

    @Override
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

    @Override
    public boolean checkEmail(Account account, Model model) {
        if (findByemail(account.getEmail()) != null) {
            String messError = "Email already exists";
            model.addAttribute("messError", messError);
            return false;
        }
        return true;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public String sendUrl() {

        String resetPasswordUrl = "/ConfirmPassword-Forgot-mk";
        return resetPasswordUrl;
    }

    @Override
    public String submitLogin(Account accountRequest, Model model) throws SQLException {
//        var loginUserDto = new UserDto();
//        loginUserDto.setEmail(accountRequest.getEmail());
//        loginUserDto.setUsername(accountRequest.getUsername());
//        loginUserDto.setPassword(accountRequest.getHashedPassword());
        String email = accountRequest.getEmail();
        String password = accountRequest.getHashedPassword();

        Account existingAccount = repo.findByemail(email);
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
        if (existingAccount.isDisable()) {
            model.addAttribute("error", "Tài khoản của bạn tạm thời bị vô hiệu hóa");
            return "swappa/assests/html/acc_login";
        }
        // Lưu thông tin đăng nhập vào session hoặc làm bất kỳ xử lý nào khác cần thiết
        session.setAttribute("loggedInUser", accountRequest.getEmail());
        StaticVariable.sessionEmail = accountRequest.getEmail();
        model.addAttribute("iduser", existingAccount.getUserId());
        model.addAttribute("email", existingAccount.getEmail());
        session.setAttribute("iduser", existingAccount.getUserId());
        session.setAttribute("email", existingAccount.getEmail());
        PasswordEncoderUtil.email = accountRequest.getEmail();
        return "swappa/assests/html/trangchu";
    }

    @Override
    public Account findByUsernameAndEmail(String username, String email) {
        return repo.findByUsernameAndEmail(username, email);
    }

    @Override
    public String createAccount(AccountDto accountDto)
            throws SQLException, NullPointerException {
        if (!accountDto.getPassword()
                .equals(accountDto.getConfirmPassword()))
            return AccountEnum.not_matched.getValue();
        var account = repo.findByUsernameOrEmail(accountDto.getUsername(), accountDto.getEmail());
        if (Objects.nonNull(account)) {
            return AccountEnum.existed.getValue();
        }
        var createdAccount = repo.save(AccountDto.convertAccountDtoToAccount(accountDto));
        return AccountEnum.succeed.getValue();
    }

    @Override
    public long getTotalAccounts() {
        long totalAccountsFromAccount = repo.count();
        long totalAccountsFromAccountGG = accountGGRepo.count();
        return totalAccountsFromAccount + totalAccountsFromAccountGG;
    }

    @Override
    public long getGGAccount() {
        long totalAccountsFromAccountGG = accountGGRepo.count();
        return totalAccountsFromAccountGG;
    }

    @Override
    public long getDKAccount() {
        long totalAccountsFromAccount = repo.count();
        return totalAccountsFromAccount;
    }

    @Override
    public boolean isLoggedIn(HttpSession session) {
        // Lấy thông tin người dùng từ session
        Object loggedInUser = session.getAttribute("loggedInUser");

        // Kiểm tra nếu thông tin người dùng không null và là một chuỗi không rỗng
        return loggedInUser instanceof String && !((String) loggedInUser).isEmpty();
    }
}
