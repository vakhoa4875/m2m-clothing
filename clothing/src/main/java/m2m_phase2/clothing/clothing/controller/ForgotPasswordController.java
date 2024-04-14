package m2m_phase2.clothing.clothing.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import java.util.concurrent.TimeUnit;

@Controller
public class ForgotPasswordController {
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserServiceImpl userService;
	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
//		Account account = new Account();
//		model.addAttribute("accountForgot", account);
		return "swappa/assests/html/acc_forgot_pass";
	}
	@PostMapping("/submit-forgot-password")
	public String ForgotPassword(Model model, HttpServletRequest request) {
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		Account findByusername = accountServiceImpl.findByusername(userName);
		Account findByemail = accountServiceImpl.findByemail(email);
		if (findByusername != null && findByemail != null) {
			String link = accountServiceImpl.sendUrl();
			accountServiceImpl.sendLinkEmail(email, link);
			session.setAttribute("email", email);
			return "/swappa/assests/html/acc_forgot_pass";
		} else {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "/swappa/assests/html/acc_forgot_pass";
		}
	}

	@GetMapping("/ConfirmPassword-Forgot-mk")
	public String ForgotLink() {
		return "swappa/assests/html/acc_forgot_pass_xacnhan";
	}
	@PostMapping("/submit-forgot-password-change")
	public String ForgotPasswordCheck(HttpServletRequest request, Model model) {
		String newPassword =request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = (String) session.getAttribute("email");// Lấy email từ session
		Account account = accountServiceImpl.findByemail(email);
			String hashedPassword = PasswordEncoderUtil.encodePassword(confirmPassword);
			account.setHashedPassword(hashedPassword);
			accountServiceImpl.saveAccount(account);
			session.removeAttribute("email");
			return "swappa/assests/html/acc_login";
	}
}
