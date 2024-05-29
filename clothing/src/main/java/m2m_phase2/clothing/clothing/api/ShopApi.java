package m2m_phase2.clothing.clothing.api;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.ShopDto;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.service.ShopService;
import m2m_phase2.clothing.clothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;

@RestController
public class ShopApi {

    @Autowired
    HttpSession session;
    @Autowired
    ShopService shopService;
    @Autowired
    UserService userService;

    @GetMapping("/get-shop-by-user-email")
    public ResponseEntity<?> getShopByUserEmail() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Success");
            result.put("data", shopService.findShopByUser(sessionEmail));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/public/shopSignUp")
    public ResponseEntity<?> shopSignUp() {
        Map<String, Object> result = new HashMap<>();
        try {
            ShopDto shopDto = new ShopDto();
            shopDto.setNameShop("Shop");
            shopDto.setDateEstablished(new Date());

            UserDto userDto = new UserDto();
            userDto.setEmail(sessionEmail);

            UserM userM =  userService.getUserByEmail(userDto);

            UserE userE = new UserE();
            userE.setId(userM.getId());

            shopDto.setUserE(userE);

            result.put("status", true);
            result.put("message", "Call Api Success");
            result.put("data", shopService.insertShop(shopDto));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

}
