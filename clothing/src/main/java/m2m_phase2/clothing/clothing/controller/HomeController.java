package m2m_phase2.clothing.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Otp;
import m2m_phase2.clothing.clothing.entity.Password;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Controller
public class HomeController {

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

		session.setAttribute("acc", accountRequest);

		// Tạo mã OTP ngẫu nhiên gồm 6 chữ số
		String otp = accountServiceImpl.generateOTP();

		// Gửi mã OTP qua email
		accountServiceImpl.sendOTPEmail(accountRequest.getEmail(), otp);

		// Lưu mã OTP vào session để kiểm tra xác thực sau này
		session.setAttribute("otp", otp);
		session.setAttribute("email", accountRequest.getEmail());

		// tạo entity otp
		Otp otpNhap = new Otp();
		model.addAttribute("otpNhap", otpNhap);

		return "Front_End/pages/ConfirmPassword-signup";

	}

	@PostMapping("/otp") // hàm phatteacher
	public String otp(@ModelAttribute("otp") Otp otp, Model model) {

		// lấy account người dùng nhập ở trang đăng kí
		Account accountSession = (Account) session.getAttribute("acc");

		// lấy otp người dùng nhập vào
		String enteredOTP = otp.getOtp1() + otp.getOtp2() + otp.getOtp3() + otp.getOtp4() + otp.getOtp5()
				+ otp.getOtp6();
		// lấy otp ở trong session
		String sessionOTP = (String) session.getAttribute("otp");

		// so sánh hai otp nếu giống thì return về trang sign in khác thì return về
		// trang sign up
		if (enteredOTP.equals(sessionOTP)) {
			// Mã OTP hợp lệ, thực hiện các hành động tiếp theo
			String hashCode = accountSession.getHashedPassword();
			accountSession.setHashedPassword(PasswordEncoderUtil.encodePassword(hashCode));
			accountServiceImpl.saveAccount(accountSession);

			return "Front_End/pages/sign-in";

		} else {
			return "Front_End/pages/sign-up";
		}

	}

//	function of Tài
	@GetMapping("/change-password")
	public String changePassword() {

		return "Front_End/pages/changePassword";
	}

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
//	    Tạo email đưa vào session
		

		if ((accountServiceImpl.findByemail(accountRequest.getEmail())) != null
				&& accountServiceImpl.findByusername(accountRequest.getUsername()) != null) {
			
//			Lưu email vào session
			session.setAttribute("email", accountRequest.getEmail());
			// tạo otp
			String otp = accountServiceImpl.generateOTP();
			// gửi otp đến email
			accountServiceImpl.sendOTPEmail(accountRequest.getEmail(), otp);

			session.setAttribute("otp", otp);

			return "Front_End/pages/ConfirmPassword-Forgot";

		} else {

			return "Front_End/pages/Forgotpassword";

		}
	}

	@PostMapping("/submit-forgot-password-otp")
	public String ForgotPasswordOtp(Otp otp, Model model) {
		Password password = new Password();
		model.addAttribute("password",password);
		String otpOne = otp.getOtp1() + otp.getOtp2() + otp.getOtp3() + otp.getOtp4() + otp.getOtp5() + otp.getOtp6();
		String otpTwo = (String) session.getAttribute("otp");

		if (otpOne.equals(otpTwo)) {
			return "Front_End/pages/ConfirmPassword-Forgot-mk";
		} else {
			return "Front_End/pages/Forgotpassword";
		}

	}

	@PostMapping("/submit-forgot-password-change")
	public String ForgotPasswordCheck(@ModelAttribute("password") Password password, Model model) {

		String passwordOne = password.getPasswordOne();
		String passwordTwo = password.getPasswordTwo();

//	    Lấy email từ session
		String email = (String) session.getAttribute("email");
		Account account = accountServiceImpl.findByemail(email);
		
		if (passwordOne.equalsIgnoreCase(passwordTwo) && account != null) {

			String hashedPassword = PasswordEncoderUtil.encodePassword(passwordTwo);
			account.setHashedPassword(hashedPassword);
            accountServiceImpl.saveAccount(account);
            System.out.println(hashedPassword);
            
			return "Front_End/pages/sign-in";
		} else {
			return "Front_End/pages/ConfirmPassword-Forgot-mk";
		}

	}

}
