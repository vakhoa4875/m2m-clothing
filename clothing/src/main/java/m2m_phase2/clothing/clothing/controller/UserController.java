package m2m_phase2.clothing.clothing.controller;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/login")
    public String doGetAdminLogin(Model model) {
        if (Objects.nonNull(httpSession.getAttribute("adminToken")))
            return "redirect:/admin/home";
        model.addAttribute("loginInfo", new UserDto());// truyền AccountDto qua admin's loginform để hứng data
        return "swappa/assests/html/admin_login";
    }

    @PostMapping("/loginSucceed")
    public String doPostAdminLoginSucceed(@ModelAttribute("loginInfo") UserDto userDto) {
        try {
            userService.saveAdminTokenToSession(httpSession, userDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (Objects.nonNull(httpSession.getAttribute("adminToken")))
            return "redirect:/admin/home";
        return "redirect:/admin/login";
    }

    @GetMapping("/home")
    public String doGetHomeAdmin(Model model) {
        if (Objects.isNull(httpSession.getAttribute("adminToken")))
            return "redirect:/admin/login";
        return "swappa/assests/html/admin";
    }

    @GetMapping("/logout")
    public String doGetLogOut() {
        httpSession.removeAttribute("adminToken");
        return "redirect:/admin/login";
    }

    @GetMapping("/cart")
    public String doGetCart() {
        return "swappa/assests/html/card";
    }

}
