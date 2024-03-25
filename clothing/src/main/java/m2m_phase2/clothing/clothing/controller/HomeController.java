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

	@GetMapping("/")
	public String defaultPage(){
		return "Front_End/TrangChu";
	}

	@GetMapping("/trangchu")
	public String getTrangchu(){
		return "Front_End/TrangChu";
	}

	@GetMapping("/sanpham")
	public String getSanPham(){
		return "Front_End/SanPham";
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
		// Lấy email và mật khẩu từ đối tượng accountRequest
		String email = accountRequest.getEmail();
		String password = accountRequest.getHashedPassword();

		// Kiểm tra xem tài khoản có tồn tại trong cơ sở dữ liệu không
		Account existingAccount = accountServiceImpl.findByEmail(email);

		// Nếu không tìm thấy tài khoản
		if (existingAccount == null) {
			// Xử lý thông báo lỗi hoặc chuyển hướng đến trang đăng nhập với thông báo lỗi
			model.addAttribute("error", "Tài khoản không tồn tại");
			return "Front_End/pages/sign-in";
		}

		// Kiểm tra tính hợp lệ của mật khẩu
		boolean passwordMatch = PasswordEncoderUtil.verifyPassword(password, existingAccount.getHashedPassword());

		// Nếu mật khẩu không trùng khớp
		if (!passwordMatch) {
			// Xử lý thông báo lỗi hoặc chuyển hướng đến trang đăng nhập với thông báo lỗi
			model.addAttribute("error", "Mật khẩu không đúng");
			return "Front_End/pages/sign-in";
		}

		// Chuyển hướng đến trang chính sau khi đăng nhập thành công
		// Kiểm tra và xử lý vai trò admin
	    // Kiểm tra xem tài khoản có bị vô hiệu hóa không
	    if (accountServiceImpl.isDisable(existingAccount)) {
	        // Nếu tài khoản bị vô hiệu hóa, chuyển hướng đến trang thông báo
	        model.addAttribute("error", "Tài khoản của bạn tạm thời bị vô hiệu hóa");
	        return "Front_End/pages/sign-in";
	    }
	    
	    
		// Lưu thông tin đăng nhập vào session hoặc làm bất kỳ xử lý nào khác cần thiết
		session.setAttribute("loggedInUser", accountRequest.getEmail());
		System.out.println(session.getAttribute("loggedInUser"));
			return "Front_End/TrangChu";
		
		
	}

	@GetMapping("/admin")
	public String logAdmin(Model model) {

		Account accountlog = new Account();
		model.addAttribute("accountlog", accountlog);
		System.out.println(session.getAttribute("loggedInUser"));
		return "Front_End/pages/sign-in";

	}

	





	

	
	
}
