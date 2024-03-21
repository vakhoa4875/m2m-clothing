package m2m_phase2.clothing.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Password;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Controller
public class ChangePass {

	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping("/change-password")
	public String changePassword(Model model) {
		Password password = new Password();
		model.addAttribute("passwordChange", password);

		return "Front_End/pages/change	Password";
	}

	@PostMapping("/submit-change-password")
	public String submitChangePassword(@ModelAttribute("passwordChange") Password pass, Model model) {
		if (session.getAttribute("loggedInUser") != null) {

			Account account = accountServiceImpl.findByemail(session.getAttribute("loggedInUser") + "");
			String passOld = account.getHashedPassword();
			String passUser = pass.getPasswordOne();
			String passNewOne = (pass.getPasswordTwo());
			String passNewTwo = (pass.getPasswordThree());

			try {
				if (PasswordEncoderUtil.verifyPassword(passUser, passOld)) {
					if ((!passNewTwo.equalsIgnoreCase(pass.getPasswordThree()))){
						
						
						String errorChangePass = "Mật khẩu một phải giống  mật khẩu hai ";
						System.out.println("Mật khẩu một phải giống  mật khẩu hai");
						model.addAttribute("errorChangePass", errorChangePass);
						
						
						
						return "Front_End/pages/changePassword";
					} else if ((passNewOne.equalsIgnoreCase(pass.getPasswordOne()))
							|| (pass.getPasswordOne().equalsIgnoreCase(passNewTwo))){
//						Thông báo ra lỗi
						String errorChangePass = "Mật khẩu này đang được sử dụng ";
						System.out.println("Mật khẩu này đang được sử dụng");
						model.addAttribute("errorChangePass", errorChangePass);
						return "Front_End/pages/changePassword";
					}
					
					else {
						account.setHashedPassword(PasswordEncoderUtil.encodePassword(pass.getPasswordThree()));
						accountServiceImpl.saveAccount(account);
						return "Front_End/TrangChu";
					}
				} else {
//					Thông báo ra lỗi
					String errorChangePass = "Mật khẩu không chính xác";
					System.out.println("Mật khẩu không chính xác");
					model.addAttribute("errorChangePass", errorChangePass);
					
					return "Front_End/pages/changePassword";
				}
			} catch (Exception e) {
//				Thông báo ra lỗi
				String errorChangePass = "Mật khẩu hiện tại không chính xác";
				System.out.println("Mật khẩu hiện tại không chính xác");
				model.addAttribute("errorChangePass", errorChangePass);
				return "Front_End/pages/changePassword";
			}

		} else {
			return "Front_End/pages/changePassword";
		}

	}
	
}
