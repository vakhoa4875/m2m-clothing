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

		return "Front_End/pages/sign-in-admin";
	}



	@GetMapping("/admin/UserIDProfile")
	public String getUserIDPrfile(@RequestParam Integer id,@ModelAttribute("accadmin") Account account, Model model) {
//		System.out.println(accountServiceImpl.findByuserId(id).isDisable());
		System.out.println(accountServiceImpl.findByuserId(id).getUserId());
		session.setAttribute("idprofile", accountServiceImpl.findByuserId(id).getUserId());


		Account acc = accountServiceImpl.findByuserId(id);
		System.out.println(acc.getEmail());

		model.addAttribute("iduser",acc.getUsername());
//		model.addAttribute("username",acc.getUsername());
//		model.addAttribute("email",acc.getEmail());
//		model.addAttribute("pass",acc.getHashedPassword());
//		model.addAttribute("is_disable",acc.isDisable());
//		model.addAttribute("role",acc.isAdmin());

		Map<Account, Userinfo> map = userService.getAll();
		model.addAttribute("listUser", map);
//		model.addAttribute("turnOffUser",true);
		return "Front_End/pages/User(Management)"; // Hoặc bạn có thể trả về một giá trị khác tùy thuộc vào logic của bạn
	}

	@GetMapping("/admin/admin-UserIDProFile-Success")
	public String Edit_UserID(@RequestParam Integer id, Model model){
		System.out.println(session.getAttribute("idprofile"));
		System.out.println(id);

		Map<Account, Userinfo> map = userService.getAll();
		model.addAttribute("listUser", map);

		return "Front_End/pages/sign-in-admin";
	}

}
