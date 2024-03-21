package m2m_phase2.clothing.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Otp;
import m2m_phase2.clothing.clothing.entity.Password;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Controller
public class ForgotPass {
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
		Account account = new Account();
		model.addAttribute("accountForgot", account);
		return "Front_End/pages/Forgotpassword";
	}

	@PostMapping("/submit-forgot-password")
	public String ForgotPassword(@ModelAttribute("accountForgot") Account accountRequest, Model model) {

		System.out.println(accountRequest.getUsername());
		System.out.println(accountRequest.getEmail());
		Otp otpForgot = new Otp();
		model.addAttribute("otpForgot", otpForgot);
		// Tạo email đưa vào session

		if ((accountServiceImpl.findByemail(accountRequest.getEmail())) != null
				&& accountServiceImpl.findByusername(accountRequest.getUsername()) != null) {

			// Lưu email vào session
			session.setAttribute("email", accountRequest.getEmail());
			// tạo otp
			String otp = accountServiceImpl.generateOTP();
			// gửi otp đến email
			accountServiceImpl.sendOTPEmail(accountRequest.getEmail(), otp);

			session.setAttribute("otp", otp);

			return "Front_End/pages/ConfirmPassword-Forgot";

		} else {
//			Thông báo ra lỗi
			String errorChangePass = "Email hoặc tên người dùng này không tồn tại !";
			System.out.println("Email hoặc tên người dùng này không tồn tại !");
			model.addAttribute("errorChangePass", errorChangePass);

			return "Front_End/pages/Forgotpassword";

		}
	}

	@PostMapping("/submit-forgot-password-otp")
	public String ForgotPasswordOtp(@ModelAttribute("otpForgot") Otp otp, Model model) {
		Password password = new Password();
		model.addAttribute("password", password);
		String otpOne = otp.getOtp1() + otp.getOtp2() + otp.getOtp3() + otp.getOtp4() + otp.getOtp5() + otp.getOtp6();
		String otpTwo = (String) session.getAttribute("otp");

		if (!(otp.getOtp1().equals("") || otp.getOtp2().equals("") || otp.getOtp3().equals("") || otp.getOtp4().equals("")
				|| otp.getOtp5().equals("") || otp.getOtp5().equals(""))) {
			if (otpOne.equals(otpTwo)) {
				return "Front_End/pages/ConfirmPassword-Forgot-mk";
			} else {
//				Thông báo ra lỗi
				String errorChangePass = "OTP này không  đúng !";
				System.out.println("OTP này không  đúng !");
				model.addAttribute("errorChangePass", errorChangePass);
				return "Front_End/pages/ConfirmPassword-Forgot";
			}
		} else {
//			Thông báo ra lỗi
			String errorChangePass = "Không được để trống !";
			System.out.println("Không được để trống !");
			model.addAttribute("errorChangePass", errorChangePass);
			return "Front_End/pages/ConfirmPassword-Forgot";
		}

	}

	@PostMapping("/submit-forgot-password-change")
	public String ForgotPasswordCheck(@ModelAttribute("password") Password password, Model model) {

		String passwordOne = password.getPasswordOne();
		String passwordTwo = password.getPasswordTwo();

		// Lấy email từ session
		String email = (String) session.getAttribute("email");
		Account account = accountServiceImpl.findByemail(email);

		if (passwordOne.equalsIgnoreCase(passwordTwo) && account != null) {

			String hashedPassword = PasswordEncoderUtil.encodePassword(passwordTwo);
			account.setHashedPassword(hashedPassword);
			accountServiceImpl.saveAccount(account);
			System.out.println(hashedPassword);

			return "Front_End/pages/sign-in";
		} else {

//			Thông báo ra lỗi
			String errorChangePass = " Pass không hợp lệ   ";
			System.out.println("Pass không hợp lệ");
			model.addAttribute("errorChangePass", errorChangePass);
			return "Front_End/pages/ConfirmPassword-Forgot-mk";
		}

	}
}
