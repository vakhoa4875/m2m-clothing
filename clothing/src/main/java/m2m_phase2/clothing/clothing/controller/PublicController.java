package m2m_phase2.clothing.clothing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.CommentService;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.UserService;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/p")
@RequiredArgsConstructor
public class PublicController {
    private final AccountService accountService;
    private final UserService userService;
    private final ProductService productService;
    private final AuthService authService;
    private final HttpSession session;
    private final CommentService commentService;

    @GetMapping({"/", "/home"})
    @SneakyThrows
    public String doGetHome(Model model) {
        var currentUser = userService.getCurrentUser();
        var list = productService.findAll();
        model.addAttribute("products", list);
        model.addAttribute("idUser", currentUser == null ? null : currentUser.getId());
        return "swappa/assests/html/trangchu";
    }

    @GetMapping("/login")
    public String doGetLogin() {
        return "swappa/assests/html/acc_login";
    }

    @GetMapping("/searchProduct")
    public String doGetSearchResult(Model model) {
        return "swappa/assests/html/searchProduct";
    }

    @GetMapping("/vouchers")
    public String doGetViewAllVouchers() {
        return "swappa/assests/html/vouchers";
    }

    @GetMapping("/viewSearchShop")
    public String doGetViewSearchedShop() {
        return "swappa/assests/html/searchShop";
    }

    @GetMapping("/search_Product")
    public String doGetSearchResult2(@RequestParam() String key_search, @RequestParam() String type_search) {
        return "swappa/assests/html/productAll";
    }

    @GetMapping("/forgot-password")
    public String doGetForgotPassword() {
        return "swappa/assests/html/acc_forgot_pass";
    }

    @PostMapping("/submit-forgot-password")
    public String doPostForgotPassword(Model model, HttpServletRequest request) {
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        Account findByusername = accountService.findByusername(userName);
        Account findByemail = accountService.findByemail(email);
        if (findByusername != null && findByemail != null) {
            String link = accountService.sendUrl();
            accountService.sendLinkEmail(email, link);
            session.setAttribute("email", email);
            return "/swappa/assests/html/acc_forgot_pass";
        } else {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "/swappa/assests/html/acc_forgot_pass";
        }
    }

    @GetMapping("/ConfirmPassword-Forgot-mk")
    public String doGetForgotLink() {
        return "swappa/assests/html/acc_forgot_pass_xacnhan";
    }

    @PostMapping("/submit-forgot-password-change")
    public String doGetForgotPasswordCheck(HttpServletRequest request, Model model) {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = authService.getCurrentUserEmail();// Lấy email từ session
        Account account = accountService.findByemail(email);
        String hashedPassword = PasswordEncoderUtil.encodePassword(confirmPassword);
        account.setHashedPassword(hashedPassword);
        accountService.saveAccount(account);
        session.removeAttribute("email");
        return "swappa/assests/html/acc_login";
    }

    @GetMapping("/categoryType")
    public String doGetCategoryType(@RequestParam Integer categoryId, Model model) {
        List<ProductM> products = productService.findBycategory(categoryId);
        model.addAttribute("listProduct", products);
        model.addAttribute("active", categoryId);
        model.addAttribute("activeLogin", authService.getCurrentUserEmail());
        return "swappa/assests/html/productAll";
    }

    @GetMapping({"/AllcategoryType/{id}", "/AllcategoryType"})
    public String getAllcategory(Model model) {
        List<ProductM> products = productService.findAll();
        model.addAttribute("listProduct", products);
        model.addAttribute("active", 7); // hiện bản cateory ở trạng thái trọn All
        model.addAttribute("activeLogin", authService.getCurrentUserEmail());
        return "swappa/assests/html/productAll";
    }

    @GetMapping("/product")
    public String getDetail(@RequestParam String slug_url, Model model) throws SQLException {
        ProductM product = productService.findByslug_url(slug_url);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setProduct(productService.findByslug_url(slug_url));
//        List<CommentM> commentM = commentService.findByProductId(commentDTO);
        model.addAttribute("listProduct", product);
//        model.addAttribute("commentM", commentM);
        model.addAttribute("checklogin", authService.getCurrentUserEmail());
        return "swappa/assests/html/productDetail";
    }

    @GetMapping("/register") // hàm phatteacher
    public String getHome(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "swappa/assests/html/acc_register";
    }

    @PostMapping("/submitRegister") // hàm phatteacher
    public String submitRegister(HttpServletRequest req, Model model) {
        Account accountRegister = new Account();
        accountRegister.setUsername(req.getParameter("firstName"));
        accountRegister.setEmail(req.getParameter("email2"));
        accountRegister.setHashedPassword(req.getParameter("password21"));
        session.setAttribute("accountRegister", accountRegister);
        String otp = accountService.generateOTP();
        accountService.sendOTPEmail(accountRegister.getEmail(), otp, "OTP for Registering Account");
        session.setAttribute("otp", otp);
        return "swappa/assests/html/acc_register_OTP";
    }

    @PostMapping("/otp") // hàm phatteacher
    public String otp(Model model, HttpServletRequest request) {
        String otp1 = request.getParameter("otp1");
        String otp2 = request.getParameter("otp2");
        String otp3 = request.getParameter("otp3");
        String otp4 = request.getParameter("otp4");
        String otp5 = request.getParameter("otp5");
        String otp6 = request.getParameter("otp6");
        String otpSuccess = accountService.concatOtp(otp1, otp2, otp3, otp4, otp5, otp6);
        Account accountSession = (Account) session.getAttribute("accountRegister");
        String sessionOTP = (String) session.getAttribute("otp");
        if (otpSuccess.equals(sessionOTP)) {
            String hashCode = accountSession.getHashedPassword();
            accountSession.setHashedPassword(PasswordEncoderUtil.encodePassword(hashCode));
            accountService.saveAccount(accountSession);
            return "swappa/assests/html/acc_login";
        } else {
            return "swappa/assests/html/acc_register_OTP";
        }
    }

    @GetMapping("/shop")
    public String shopUser() {
        return "swappa/assests/html/shopuser";
    }
}
