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

	@GetMapping("/login")
	public String getLog(Model model) {

		Account accountlog = new Account();
		model.addAttribute("accountlog", accountlog);
		return "Front_End/pages/sign-in";

	}
	@PostMapping("/submitLogin")
	public String submitLogin(@ModelAttribute("accountlog") Account accountRequest, Model model) {
	    // Lấy email và mật khẩu từ đối tượng accountRequest
	    String email = accountRequest.getEmail();
	    String password = accountRequest.getHashedPassword();
	    
	    System.out.println(accountRequest.getHashedPassword());
		System.out.println(accountRequest.getEmail());
	    
	    // Kiểm tra xem tài khoản có tồn tại trong cơ sở dữ liệu không
	    Account existingAccount = accountServiceImpl.findByEmail(email);
	    
	    // Nếu không tìm thấy tài khoản
	    if(existingAccount == null) {
	        // Xử lý thông báo lỗi hoặc chuyển hướng đến trang đăng nhập với thông báo lỗi
	        model.addAttribute("error", "Tài khoản không tồn tại");
	        return "Front_End/pages/sign-in";
	    }
	    
	    // Kiểm tra tính hợp lệ của mật khẩu
	    boolean passwordMatch = PasswordEncoderUtil.verifyPassword(password, existingAccount.getHashedPassword());
	    
	    // Nếu mật khẩu không trùng khớp
	    if(!passwordMatch) {
	        // Xử lý thông báo lỗi hoặc chuyển hướng đến trang đăng nhập với thông báo lỗi
	        model.addAttribute("error", "Mật khẩu không đúng");
	        return "Front_End/pages/sign-in";
	    }
	    
	    // Lưu thông tin đăng nhập vào session hoặc làm bất kỳ xử lý nào khác cần thiết
	    session.setAttribute("loggedInUser", existingAccount);
	    
	    // Chuyển hướng đến trang chính sau khi đăng nhập thành công
	    return "Front_End/pages/sign-up";
	}

	
	@GetMapping("/register")
	public String getHome(Model model) {

		Account account = new Account();
		model.addAttribute("account", account);
		return "Front_End/pages/sign-up";

	}

	@PostMapping("/submitRegister")
	public String submitRegister(@ModelAttribute("account") Account accountRequest, Model model) {
		System.out.println(PasswordEncoderUtil.encodePassword("12345678"));
		System.out.println(accountRequest.getHashedPassword());
		System.out.println(accountRequest.getEmail());
		System.out.println(accountRequest.getUsername());
//		String a = PasswordEncoderUtil.encodePassword(accountRequest.getHashedPassword());
//		System.out.println(a);

		return "Front_End/pages/sign-in";

	}

}
