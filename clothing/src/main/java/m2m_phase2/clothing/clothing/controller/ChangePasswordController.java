package m2m_phase2.clothing.clothing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

import java.util.concurrent.TimeUnit;

@Controller
public class ChangePasswordController {
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserServiceImpl userService;
	@GetMapping("/change-password")
	public String changePassword(Model model) {
		return "swappa/assests/html/change_pass";
	}
	@PostMapping("/submit-change-password")
	public String submitChangePassword(Model model, HttpServletRequest request) {
		Account account = accountServiceImpl.findByemail(session.getAttribute("loggedInUser")+"");
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("password2");
		if (password1.equalsIgnoreCase(password2)){
			account.setHashedPassword(PasswordEncoderUtil.encodePassword(password2));
			accountServiceImpl.saveAccount(account);
			return "swappa/assests/html/trangchu";
		}
		else {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "swappa/assests/html/change_pass";
		}

	}
}

