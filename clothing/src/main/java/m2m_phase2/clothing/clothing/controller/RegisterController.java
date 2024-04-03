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
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Controller
public class RegisterController {

	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private HttpSession session;

	@GetMapping("/register") // hàm phatteacher
	public String getHome(Model model) {

		Account account = new Account();
		model.addAttribute("account", account);
		return "swappa/assests/html/acc_register";

	}
	@PostMapping("/submitRegister") // hàm phatteacher
	public String submitRegister(HttpServletRequest req, Model model) {
		Account accountRegister = new Account();
		accountRegister.setUsername(req.getParameter("firstName"));
		accountRegister.setEmail(req.getParameter("email2"));
		accountRegister.setHashedPassword(req.getParameter("password21"));
		session.setAttribute("accountRegister", accountRegister);
		String otp = accountServiceImpl.generateOTP();
		accountServiceImpl.sendOTPEmail(accountRegister.getEmail(), otp);
		session.setAttribute("otp", otp);
		return "swappa/assests/html/acc_register_OTP";
	}

	@PostMapping("/otp") // hàm phatteacher
	public String otp( Model model, HttpServletRequest request) {
		String otp1 = request.getParameter("otp1");
		String otp2 = request.getParameter("otp2");
		String otp3 = request.getParameter("otp3");
		String otp4 = request.getParameter("otp4");
		String otp5 = request.getParameter("otp5");
		String otp6 = request.getParameter("otp6");
		String otpSuccess = accountServiceImpl.concatOtp(otp1, otp2, otp3, otp4, otp5, otp6);
		Account accountSession = (Account) session.getAttribute("accountRegister");
		String sessionOTP = (String) session.getAttribute("otp");
		if (otpSuccess.equals(sessionOTP)) {
			String hashCode = accountSession.getHashedPassword();
			accountSession.setHashedPassword(PasswordEncoderUtil.encodePassword(hashCode));
			accountServiceImpl.saveAccount(accountSession);
			return "swappa/assests/html/acc_login";
		} else {
			return "swappa/assests/html/acc_register_OTP";
		}

	}

}
