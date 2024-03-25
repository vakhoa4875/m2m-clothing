package m2m_phase2.clothing.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Otp;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
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
		return "Front_End/pages/sign-up";

	}

	@PostMapping("/submitRegister") // hàm phatteacher
	public String submitRegister(@ModelAttribute("account") Account accountRequest, Model model) {
		String username = accountRequest.getUsername();
		String email = accountRequest.getEmail();
		String password = accountRequest.getHashedPassword();
		
		model.addAttribute("usernameCurrent", username);
		model.addAttribute("emailCurrent", email);
		model.addAttribute("passwordCurrent", password);

		Boolean checkFillRegister = accountServiceImpl.checkFillRegister(model, username, email, password);
		Boolean checkUsername = accountServiceImpl.checkUsername(accountRequest, model);
		Boolean checkEmail = accountServiceImpl.checkEmail(accountRequest, model);
		if (checkFillRegister == false || checkUsername == false || checkEmail == false) {
			return "Front_End/pages/sign-up";
		}
		session.setAttribute("accountRegister", accountRequest);
		String otp = accountServiceImpl.generateOTP();
		accountServiceImpl.sendOTPEmail(accountRequest.getEmail(), otp);
		session.setAttribute("otp", otp);
		return "Front_End/pages/ConfirmPassword-signup";
	}

	@PostMapping("/otp") // hàm phatteacher
	public String otp(@ModelAttribute("otp") Otp otp, Model model, HttpServletRequest request) {
		String otp1 = request.getParameter("otp1");
		String otp2 = request.getParameter("otp2");
		String otp3 = request.getParameter("otp3");
		String otp4 = request.getParameter("otp4");
		String otp5 = request.getParameter("otp5");
		String otp6 = request.getParameter("otp6");
		String otpSuccess = accountServiceImpl.concatOtp(otp1, otp2, otp3, otp4, otp5, otp6);
		System.out.println(otpSuccess);
		boolean checkOtp = accountServiceImpl.checkFillOtp(model, otp1, otp2, otp3, otp4, otp5, otp6);
		if (checkOtp == false) {
			return "Front_End/pages/ConfirmPassword-signup";
		}
		Account accountSession = (Account) session.getAttribute("accountRegister");
		String sessionOTP = (String) session.getAttribute("otp");
		if (otpSuccess.equals(sessionOTP)) {
			String hashCode = accountSession.getHashedPassword();
			accountSession.setHashedPassword(PasswordEncoderUtil.encodePassword(hashCode));
			accountServiceImpl.saveAccount(accountSession);
			return "Front_End/pages/sign-in";
		} else {
			return "Front_End/pages/ConfirmPassword-signup";
		}

	}

}
