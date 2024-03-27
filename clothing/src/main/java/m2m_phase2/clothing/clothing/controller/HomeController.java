package m2m_phase2.clothing.clothing.controller;

import java.io.Console;
import java.security.SecureRandom;
import java.util.List;

import m2m_phase2.clothing.clothing.entity.Password;
import m2m_phase2.clothing.clothing.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Otp;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private ProductServiceImpl productServiceImpl;

	@GetMapping("/")
	public String defaultPage(Model model){
		List<Product> list =   productServiceImpl.findAll();
		model.addAttribute("products", list) ;
		return "Front_End/TrangChu";
	}

	@GetMapping("/trangchu")
	public String getTrangchu(Model model){
		List<Product> list =   productServiceImpl.findAll();
		model.addAttribute("products", list) ;
		return "Front_End/TrangChu";
	}


	@GetMapping("/lienhe")
	public String getLienHe(){
		return "Front_End/LienHe";
	}

	@GetMapping("/Setting")
	public String getSetting(){
		return "Front_End/Seting(User)";
	}

	@GetMapping("/giohang")
	public String getGioHang(){
		return "Front_End/Giohang";
	}

	@GetMapping("/login")
	public String getLog(Model model) {
		Account accountlog = new Account();
		model.addAttribute("accountlog", accountlog);
		return "Front_End/pages/sign-in";
	}
	@PostMapping("/submitLogin")
	public String submitLogin(@ModelAttribute("accountlog") Account accountRequest, Model model) {
		return accountServiceImpl.submitLogin(accountRequest, model, session);
	}	
}
