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
	
	@GetMapping("/login")
	public String getLog(Model model) {

		Account accountlog = new Account();
		model.addAttribute("accountlog", accountlog);
		System.out.println(session.getAttribute("loggedInUser"));
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

		// Lưu thông tin đăng nhập vào session hoặc làm bất kỳ xử lý nào khác cần thiết
		session.setAttribute("loggedInUser", accountRequest.getEmail());
		System.out.println(session.getAttribute("loggedInUser"));
		// Chuyển hướng đến trang chính sau khi đăng nhập thành công
		// Kiểm tra và xử lý vai trò admin
		if (accountServiceImpl.isAdmin(existingAccount)) {
			// Nếu là admin, chuyển hướng đến trang quản trị
			return "Front_End/pages/User(Management)"; // Điều hướng đến trang quản trị
		} else {
			// Nếu không phải admin, chuyển hướng đến trang chính
			return "Front_End/TrangChu"; // Điều hướng đến trang người dùng
		}
	}

	@GetMapping("/admin")
	public String logAdmin(Model model) {

		Account accountlog = new Account();
		model.addAttribute("accountlog", accountlog);
		System.out.println(session.getAttribute("loggedInUser"));
		return "Front_End/pages/sign-in";

	}

	@GetMapping("/register") // hàm phatteacher
	public String getHome(Model model) {

		Account account = new Account();
		model.addAttribute("account", account);
		return "Front_End/pages/sign-up";

	}

	@PostMapping("/submitRegister") // hàm phatteacher
	public String submitRegister(@ModelAttribute("account") Account accountRequest, Model model) {

		session.setAttribute("acc", accountRequest);

		// Tạo mã OTP ngẫu nhiên gồm 6 chữ số
		String otp = accountServiceImpl.generateOTP();

		// Gửi mã OTP qua email
		accountServiceImpl.sendOTPEmail(accountRequest.getEmail(), otp);

		// Lưu mã OTP vào session để kiểm tra xác thực sau này
		session.setAttribute("otp", otp);
		session.setAttribute("email", accountRequest.getEmail());

		// tạo entity otp
		Otp otpNhap = new Otp();
		model.addAttribute("otpNhap", otpNhap);

		return "Front_End/pages/ConfirmPassword-signup";

	}

	@PostMapping("/otp") // hàm phatteacher
	public String otp(@ModelAttribute("otp") Otp otp, Model model) {

		// lấy account người dùng nhập ở trang đăng kí
		Account accountSession = (Account) session.getAttribute("acc");

		// lấy otp người dùng nhập vào
		String enteredOTP = otp.getOtp1() + otp.getOtp2() + otp.getOtp3() + otp.getOtp4() + otp.getOtp5()
				+ otp.getOtp6();
		// lấy otp ở trong session
		String sessionOTP = (String) session.getAttribute("otp");

		// so sánh hai otp nếu giống thì return về trang sign in khác thì return về
		// trang sign up
		if (enteredOTP.equals(sessionOTP)) {
			// Mã OTP hợp lệ, thực hiện các hành động tiếp theo
			String hashCode = accountSession.getHashedPassword();
			accountSession.setHashedPassword(PasswordEncoderUtil.encodePassword(hashCode));
			accountServiceImpl.saveAccount(accountSession);

			return "Front_End/pages/sign-in";

		} else {
			return "Front_End/pages/sign-up";
		}

	}

	@GetMapping("/admin/login")
	public String adminLogin(Model model) {

		Account account = new Account();
		model.addAttribute("accadmin", account);

		return "Front_End/pages/sign-in-admin";
	}

	@PostMapping("/admin/loginPost")
	public String adminLoginPost(@ModelAttribute("accadmin") Account account){


//		Account account = accountServiceImpl.findByEmail((email));
//		System.out.println(account.getUsername());
//		if (account.isAdmin() == true){
//			return "Front_End/pages/User(Management)";
//		}else {
//			model.addAttribute("thongbao", "Tài khoản của bạn không đủ quyền để truy cập vào trang này");
//			model.addAttribute("authorities",true);
//
//		}
		return "Front_End/pages/User(Management)";
	}

	@GetMapping("/admin/user-management")
	public String toUserManagement() {

		if (!userService.isAdminAuth())
			return "Front_End/pages/sign-in";

		return "Front_End/pages/User(Management)";
	}

	// function of Tài
	@GetMapping("/change-password")
	public String changePassword(Model model) {
		Password password = new Password();
		model.addAttribute("passwordChange", password);

		return "Front_End/pages/changePassword";
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
					String errorChangePass = "Mật khẩu không chính xác";
					System.out.println("Mật khẩu không chính xác");
					model.addAttribute("errorChangePass", errorChangePass);
					
					return "Front_End/pages/changePassword";
				}
			} catch (Exception e) {
				String errorChangePass = "Mật khẩu hiện tại không chính xác";
				System.out.println("Mật khẩu hiện tại không chính xác");
				model.addAttribute("errorChangePass", errorChangePass);
				return "Front_End/pages/changePassword";
			}

		} else {
			return "Front_End/pages/changePassword";
		}

	}

	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
		Account account = new Account();
		model.addAttribute("accountForgot", account);
		return "Front_End/pages/Forgotpassword";
	}

	@PostMapping("/submit-forgot-password")
	public String ForgotPassword(@ModelAttribute("accountForgot") Account accountRequest, Model model) {

		System.out.println(accountRequest.getUsername());
		System.out.println(accountRequest.getEmail());
		Otp otpForgot = new Otp();
		model.addAttribute("otpForgot", otpForgot);
		// Tạo email đưa vào session

		if ((accountServiceImpl.findByemail(accountRequest.getEmail())) != null
				&& accountServiceImpl.findByusername(accountRequest.getUsername()) != null) {

			// Lưu email vào session
			session.setAttribute("email", accountRequest.getEmail());
			// tạo otp
			String otp = accountServiceImpl.generateOTP();
			// gửi otp đến email
			accountServiceImpl.sendOTPEmail(accountRequest.getEmail(), otp);

			session.setAttribute("otp", otp);

			return "Front_End/pages/ConfirmPassword-Forgot";

		} else {

			return "Front_End/pages/Forgotpassword";

		}
	}

	@PostMapping("/submit-forgot-password-otp")
	public String ForgotPasswordOtp(Otp otp, Model model) {
		Password password = new Password();
		model.addAttribute("password", password);
		String otpOne = otp.getOtp1() + otp.getOtp2() + otp.getOtp3() + otp.getOtp4() + otp.getOtp5() + otp.getOtp6();
		String otpTwo = (String) session.getAttribute("otp");

		if (otpOne.equals(otpTwo)) {
			return "Front_End/pages/ConfirmPassword-Forgot-mk";
		} else {
			return "Front_End/pages/Forgotpassword";
		}

	}

	@PostMapping("/submit-forgot-password-change")
	public String ForgotPasswordCheck(@ModelAttribute("password") Password password, Model model) {

		String passwordOne = password.getPasswordOne();
		String passwordTwo = password.getPasswordTwo();

		// Lấy email từ session
		String email = (String) session.getAttribute("email");
		Account account = accountServiceImpl.findByemail(email);

		if (passwordOne.equalsIgnoreCase(passwordTwo) && account != null) {

			String hashedPassword = PasswordEncoderUtil.encodePassword(passwordTwo);
			account.setHashedPassword(hashedPassword);
			accountServiceImpl.saveAccount(account);
			System.out.println(hashedPassword);

			return "Front_End/pages/sign-in";
		} else {
			return "Front_End/pages/ConfirmPassword-Forgot-mk";
		}

	}
	

	
	@GetMapping("/testfuntionproduct")
	public String testfuntionproduct(Model model) {
			 
		List<Product> list =   productServiceImpl.findAll();
 		model.addAttribute("products", list) ;
		return "Front_End/SanPham";
	}
	
	@GetMapping("/product/{id}")
	public String getDetail(@PathVariable Integer id, Model model ) {
		
		Product product = productServiceImpl.findByproductId(id);
		model.addAttribute("product",product);
		
		String pathPitures = product.getPictures();
		String[] arrayPictures = pathPitures.split(",");
		model.addAttribute("arraypictures",arrayPictures);
		
		String description = product.getDescription();
		String[] arrayDescription = description.split("\\.");
		for(int i = 0 ; i < arrayDescription.length;i++) {
			arrayDescription[i] = arrayDescription[i].replace("\"", "");
		}
		model.addAttribute("descriptions",arrayDescription);
	
		return "Front_End/ChiTietSanPham";
	}

}
