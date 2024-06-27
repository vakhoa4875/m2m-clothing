package m2m_phase2.clothing.clothing.service;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.AccountDto;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.exception.CustomException;
import org.springframework.ui.Model;

import java.sql.SQLException;

public interface AccountService {

    Account saveAccount(Account account);

    Account findByusername(String username);

    Account findByUsername(String username) throws CustomException;

    Account findByemail(String email);

    void sendOTPEmail(String toEmail, String otp,String subject);

    String generateOTP();

    String concatOtp(String... args);

    void sendLinkEmail(String toEmail, String resetPasswordUrl);

    String sendUrl();

    String submitLogin(Account accountRequest, Model model) throws SQLException;

    Account findByUsernameAndEmail(String username, String email);

    String createAccount(AccountDto accountDto) throws SQLException, NullPointerException;

    long getTotalAccounts();

    long getGGAccount();

    long getDKAccount();

    boolean isLoggedIn(HttpSession session);
}
