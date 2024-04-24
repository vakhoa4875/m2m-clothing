package m2m_phase2.clothing.clothing.controller;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

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
    public String defaultPage(Model model) {
        List<Product> list = productServiceImpl.findAll();
        model.addAttribute("products", list);
        return "swappa/assests/html/trangchu";
    }

    @GetMapping("/trangchu")
    public String getTrangchu(Model model) {
        List<Product> list = productServiceImpl.findAll();
        model.addAttribute("products", list);
        return "swappa/assests/html/trangchu";
    }


    @GetMapping("/Setting")
    public String getSetting() {
        return "swappa/assests/html/admin";
    }

    @GetMapping("/giohang")
    public String getGioHang(Model model) {
        List<Product> list = productServiceImpl.findAll();
        model.addAttribute("listSp", list);
        return "swappa/assests/html/card";
    }

    @GetMapping("/thanhtoan")
    public String getThanhToan(HttpSession session, Model model) throws SQLException {
        if (accountServiceImpl.isLoggedIn(session)){
            UserDto userDto = new UserDto();
            userDto.setEmail(session.getAttribute("loggedInUser") + "");
            UserM userM = userService.getUserByEmail(userDto);
            model.addAttribute("user",userM);
            return "swappa/assests/html/payment";
        }else {
            return "redirect:/userprofile";
        }
    }

    @GetMapping("/login")
    public String getLog(Model model) {
        Account accountlog = new Account();
        model.addAttribute("accountlog", accountlog);
        return "swappa/assests/html/acc_login";
    }

    @PostMapping("/submitLogin")
    public String submitLogin(@ModelAttribute("accountlog") Account accountRequest, Model model) {
        return accountServiceImpl.submitLogin(accountRequest, model, session);
    }

    @GetMapping("/userprofile")
    public String userProfileGet(Model model, HttpSession session) throws SQLException {
        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (accountServiceImpl.isLoggedIn(session)) {
            // Nếu đã đăng nhập, chuyển hướng đến trang profile của người dùng
            UserDto userDto = new UserDto();
            userDto.setEmail(session.getAttribute("loggedInUser") + "");
            UserM userM = userService.getUserByEmail(userDto);
            model.addAttribute("userM", userM);
            return "swappa/assests/html/userpage";
        } else {
            // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
            return "swappa/assests/html/acc_login";
        }
    }
}
