package m2m_phase2.clothing.clothing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ShopController {

    @GetMapping("/shop")
    public String shop() {
        return "swappa/assests/html/admin_shop";
    }

}
