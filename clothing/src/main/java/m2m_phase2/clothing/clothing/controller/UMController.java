package m2m_phase2.clothing.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UMController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserServiceImpl service;

	@GetMapping("/")
	public String toUserManagement() {

		if (!service.isAdminAuth())
			return "Front_End/pages/sign-in";

		return "Front_End/pages/User(Management)";
	}
	
}
