package m2m_phase2.clothing.clothing.controller;

import m2m_phase2.clothing.clothing.data.model.ShopM;
import m2m_phase2.clothing.clothing.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping("/admin/shop")
    public String shop() throws SQLException {
        if(sessionEmail == null){
            return "redirect:/loginacount";
        }
        ShopM shopM = shopService.findShopByUser(sessionEmail);
        if(shopM == null) {
            return "redirect:/myProfile";
        }
        return "swappa/assests/html/admin_shop";
    }

    @GetMapping("/admin/shop/otp")
    public String shopOtp() {
        return "swappa/assests/html/admin_shop_OTP";
    }
    @GetMapping("/acc/shop/otp")
    public String accShopOtp() {
        return "swappa/assests/html/acc_register_shop_OTP";
    }

    @GetMapping("/user/shop")
    public String shopUser() {
        return "swappa/assests/html/shopuser";
    }

}
