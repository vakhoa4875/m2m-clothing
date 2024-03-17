package m2m_phase2.clothing.clothing.controller;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
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

	@PostMapping("/submitRegister")// hàm phatteacher
	public String submitRegister(@ModelAttribute("account") Account accountRequest, Model model) {
		
//		System.out.println(PasswordEncoderUtil.encodePassword("12345678"));
//		System.out.println(PasswordEncoderUtil.verifyPassword("12345678",PasswordEncoderUtil.encodePassword("12345678")));
//		System.out.println(accountRequest.getHashedPassword());
//		System.out.println(accountRequest.getEmail());
//		System.out.println(accountRequest.getUsername());
		
		accountRequest.setHashedPassword(PasswordEncoderUtil.encodePassword(accountRequest.getHashedPassword()));
		accountServiceImpl.saveAccount(accountRequest);
		

		return "Front_End/pages/sign-in";

	}

}
