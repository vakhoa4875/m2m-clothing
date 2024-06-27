package m2m_phase2.clothing.clothing.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/p")
@RequiredArgsConstructor
public class PublicController {
    private final AccountService accountService;
    private final HttpSession session;
    private final UserServiceImpl userService;
    private final ProductService productService;
    private final AuthService authService;

    @GetMapping({"/", "/home"})
    public String doGetHome(Model model) {
        var currentUser = authService.getCurrentUser();
        var list = productService.findAll();
        model.addAttribute("products", list);
        model.addAttribute("idUser", currentUser.getUserId());
        return "swappa/assests/html/trangchu";
    }

    @GetMapping("/auth/login")
    public String getLoginUI() {
        return "swappa/assests/html/acc_login";
    }

    @GetMapping("/cart")
    public String getGioHang(Model model) {
            var list = productService.findAll();
            model.addAttribute("listSp", list);
            return "swappa/assests/html/card";
    }
}
