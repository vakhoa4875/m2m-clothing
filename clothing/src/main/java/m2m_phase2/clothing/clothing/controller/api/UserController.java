package m2m_phase2.clothing.clothing.controller.api;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.DTO.AccountDto;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;
    @GetMapping(path = {"/", "/login"})
    public String doGetAdminLogin(Model model) {
        model.addAttribute("loginInfo", new AccountDto());// truyền AccountDto qua admin's loginform để hứng data
        return "swappa/assests/html/admin_login";
    }
    @PostMapping("/loginSucceed")
    public String doPostAdminLoginSucceed(@ModelAttribute("loginInfo") AccountDto accountDto) {
        userService.saveToSession(httpSession, accountDto);
        if (httpSession.getAttribute("adminToken") != null)
            return "redirect:/admin/home";
        return "redirect:/admin/";
    }
    @GetMapping("/home")
    public String doGetHomeAdmin(Model model) {
//        model.addAttribute("currentAccount", new)
        return "swappa/assests/html/admin";
    }

    @GetMapping("/cart")
    public String doGetCart() {
        return "swappa/assests/html/card";
    }

}
