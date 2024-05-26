package m2m_phase2.clothing.clothing.controller;

import m2m_phase2.clothing.clothing.data.model.ShopM;
import m2m_phase2.clothing.clothing.repository.ShopRepo;
import m2m_phase2.clothing.clothing.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @Autowired
    ShopService shopService;
    @Autowired
    ShopRepo shopRepo;

    @GetMapping("/admin/shop")
    public String shop() {
        return "swappa/assests/html/admin_shop";
    }

    @GetMapping("/user/shop")
    public String shopUser() {
        ShopM shopM = shopService.findShopByUser("abc@gmail.com");
        System.out.println(shopM.getNameShop());
        System.out.println(shopRepo.findAll());
        return "swappa/assests/html/shopuser";
    }

}
