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
<<<<<<< Updated upstream
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
=======
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
		return accountServiceImpl.submitLogin(accountRequest, model, session);
	}	
=======
	    // Lấy email và mật khẩu từ đối tượng accountRequest
	    String email = accountRequest.getEmail();
	    String password = accountRequest.getHashedPassword();
	       
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
	    session.setAttribute("loggedInUser", accountRequest.getEmail());
	    System.out.println(session.getAttribute("loggedInUser"));
	    // Chuyển hướng đến trang chính sau khi đăng nhập thành công
	    return "Front_End/TrangChu";
	    
	    
	    
	}
	
	
	
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
		
//		accountRequest.setHashedPassword(PasswordEncoderUtil.encodePassword(accountRequest.getHashedPassword()));
//		accountServiceImpl.saveAccount(accountRequest);
		
	       // Tạo mã OTP ngẫu nhiên gồm 6 chữ số
        String otp = accountServiceImpl.generateOTP();
        
        // Gửi mã OTP qua email
        accountServiceImpl.sendOTPEmail(accountRequest.getEmail(), otp);

        // Lưu mã OTP vào session để kiểm tra xác thực sau này
        session.setAttribute("otp", otp);
        session.setAttribute("email", accountRequest.getEmail());
		
		return "Front_End/pages/sign-in";

	}
	
	@GetMapping("/admin")
	public String getAdmin(Model model) {

		Account account = new Account();
		model.addAttribute("account", account);
		return "Front_End/pages/User(Management)";

	}
	

>>>>>>> Stashed changes
}
