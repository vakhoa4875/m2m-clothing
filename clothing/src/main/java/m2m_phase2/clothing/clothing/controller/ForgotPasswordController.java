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
import m2m_phase2.clothing.clothing.entity.Password;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
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
		Account account = new Account();
		model.addAttribute("accountForgot", account);
		return "Front_End/pages/Forgotpassword";
	}
	@PostMapping("/submit-forgot-password")
	public String ForgotPassword(@ModelAttribute("accountForgot") Account accountRequest, Model model,HttpServletRequest request) {
		System.out.println(accountRequest.getUsername());
		System.out.println(accountRequest.getEmail());
		String userName = request.getParameter("username");
		String email = request.getParameter("email");	
		if(!(userName.equalsIgnoreCase("")&& email.equalsIgnoreCase(""))) {
			if ((accountServiceImpl.findByemail(accountRequest.getEmail())) != null
					&& accountServiceImpl.findByusername(accountRequest.getUsername()) != null) {
				session.setAttribute("email", accountRequest.getEmail());			
				String link = accountServiceImpl.sendUrl(); //Lấy link 
				accountServiceImpl.sendLinkEmail(accountRequest.getEmail(), link);		// gửi link đến email
				session.setAttribute("link", link);
				return "Front_End/pages/ConfirmPassword-Forgot";
			} else {
				String errorChangePass = "Email hoặc tên người dùng này không tồn tại !";
				System.out.println("Email hoặc tên người dùng này không tồn tại !");
				model.addAttribute("errorChangePass", errorChangePass);
				return "Front_End/pages/Forgotpassword";
			}
		}else {
			String errorChangePass = "Không được để trống !";
			System.out.println("Không được để trống !");
			model.addAttribute("errorChangePass", errorChangePass);
			return "Front_End/pages/Forgotpassword";
		}
	}
	@GetMapping("/ConfirmPassword-Forgot-mk")
	public String ForgotLink() {
		
		return "Front_End/pages/ConfirmPassword-Forgot-mk";
	}
	@PostMapping("/submit-forgot-password-change")
	public String ForgotPasswordCheck(HttpServletRequest request, Model model) {
		String passwordOne =request.getParameter("passwordOne");
		String passwordTwo = request.getParameter("passwordTwo");	
		String email = (String) session.getAttribute("email");// Lấy email từ session
		Account account = accountServiceImpl.findByemail(email);
		if (passwordOne.equalsIgnoreCase(passwordTwo) && account != null) {
			String hashedPassword = PasswordEncoderUtil.encodePassword(passwordTwo);
			account.setHashedPassword(hashedPassword);
			accountServiceImpl.saveAccount(account);
			System.out.println(hashedPassword);
			session.removeAttribute("email");
			return "Front_End/pages/sign-in";
		} else {
			String errorChangePass = "Pass không hợp lệ hoặc link đã được sử dụng trước đó !";
			System.out.println("Pass không hợp lệ hoặc link đã được sử dụng trước đó !");
			model.addAttribute("errorChangePass", errorChangePass);
			return "Front_End/pages/ConfirmPassword-Forgot-mk";
		}
	}
}
