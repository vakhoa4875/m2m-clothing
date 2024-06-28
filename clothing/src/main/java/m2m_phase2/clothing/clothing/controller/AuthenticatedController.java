package m2m_phase2.clothing.clothing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.model.ShopM;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.ShopService;
import m2m_phase2.clothing.clothing.service.UserService;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;

@Controller
@RequestMapping("/a")
@RequiredArgsConstructor
public class AuthenticatedController {
    private final AccountService accountService;
    private final UserService userService;
    private final ProductService productService;
    private final AuthService authService;
    private final ShopService shopService;

    @GetMapping("/cart")
    public String doGetCart(Model model) {
        var list = productService.findAll();
        model.addAttribute("listSp", list);
        return "swappa/assests/html/card";
    }

    @GetMapping("/payment")
    public String getThanhToan(HttpSession session, Model model) throws SQLException {
        var currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "swappa/assests/html/payment";
    }

    @GetMapping("/myProfile")
    public String userProfileGet(Model model) throws SQLException {
//        var currentUser = userService.getCurrentUser();
//        model.addAttribute("user", currentUser);
        return "swappa/assests/html/userpage";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        return "swappa/assests/html/change_pass";
    }

    @PostMapping("/submit-change-password")
    public String submitChangePassword(Model model, HttpServletRequest request) {
        var account = authService.getCurrentUser();
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password1.equalsIgnoreCase(password2)) {
            account.setHashedPassword(PasswordEncoderUtil.encodePassword(password2));
            accountService.saveAccount(account);
            return "swappa/assests/html/trangchu";
        } else {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "swappa/assests/html/change_pass";
        }
    }

    @GetMapping("/myShop")
    public String shop() throws SQLException {
        ShopM shopM = shopService.findShopByUser(sessionEmail);
        if(shopM == null) {
            return "redirect:/a/myProfile";
        }
        return "swappa/assests/html/admin_shop";
    }

    @GetMapping("/myShop/otp")
    public String shopOtp() {
        return "swappa/assests/html/admin_shop_OTP";
    }

    @GetMapping("/acc/shop/otp")
    public String accShopOtp() {
        return "swappa/assests/html/acc_register_shop_OTP";
    }
}
