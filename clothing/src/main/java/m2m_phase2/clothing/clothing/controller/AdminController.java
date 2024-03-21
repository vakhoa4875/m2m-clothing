package m2m_phase2.clothing.clothing.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Userinfo;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Controller
public class AdminController {
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private ProductServiceImpl productServiceImpl;

	@GetMapping("/admin/login")
	public String adminLogin(Model model) {

		Account account = new Account();
		model.addAttribute("accadmin", account);
		model.addAttribute("authorities", false);

		return "Front_End/pages/sign-in-admin";
	}

	@PostMapping("/admin/loginPost")
	public String adminLoginPost(@ModelAttribute("accadmin") Account account, Model model) {

		Account accLogin = accountServiceImpl.findByemail(account.getEmail());

		String email = (String) accLogin.getEmail();
		String pass = (String) accLogin.getHashedPassword();
//        System.out.println(accLogin.isAdmin());

		if ((email.equalsIgnoreCase(accLogin.getEmail()))
				&& (PasswordEncoderUtil.verifyPassword(account.getHashedPassword(), pass) == true)) {
			if (accLogin.isAdmin() == true) {
				session.setAttribute("admin", accLogin);
				Map<Account, Userinfo> map = userService.getAll();
				model.addAttribute("listUser", map);
				model.addAttribute("turnOffUser",true);
				return "Front_End/pages/User(Management)";
			} else {
				model.addAttribute("authorities", true);
			}
		}
			return "Front_End/pages/sign-in-admin";
	}


	@GetMapping("/admin/UserID")
	public String getUserID(@RequestParam Integer id,@ModelAttribute("accadmin") Account account, Model model) {
		System.out.println(accountServiceImpl.findByuserId(id).isDisable());
		session.setAttribute("permission", accountServiceImpl.findByuserId(id).isDisable());
		session.setAttribute("iduser",accountServiceImpl.findByuserId(id).getUserId());

		Map<Account, Userinfo> map = userService.getAll();
		model.addAttribute("listUser", map);
		model.addAttribute("turnOffUser",true);
		return "Front_End/pages/sign-in-admin"; // Hoặc bạn có thể trả về một giá trị khác tùy thuộc vào logic của bạn
	}

	@GetMapping("/admin/admin-UserID-Success")
	public String Success_UserID(@RequestParam Integer id, Model model){
		System.out.println(session.getAttribute("iduser"));
		System.out.println(id);
		if(session.getAttribute("permission").equals(false) && id.equals(200)){
			Account acc = accountServiceImpl.findByuserId((Integer) session.getAttribute("iduser"));
			acc.setDisable(true);
			accountServiceImpl.saveAccount(acc);
		}

		Map<Account, Userinfo> map = userService.getAll();
		model.addAttribute("listUser", map);

		return "redirect:/admin/loginPost";
	}
//	@GetMapping("/admin/user-management")
//	public String toUserManagement(Model model) {
//
//		if (session.getAttribute("admin") == null)
//			return "Front_End/pages/sign-in";
//		Map<Account, Userinfo> map = userService.getAll();
//		Set<Account> set = map.keySet();
//
//		model.addAttribute("listAccount", set);
//
//		return "Front_End/pages/User(Management)";
//	}
}
