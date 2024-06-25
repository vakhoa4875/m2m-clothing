package m2m_phase2.clothing.clothing.api;

import m2m_phase2.clothing.clothing.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;

@RestController
public class CategoryApi {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/get-category-by-shop-user-email")
    public ResponseEntity<?> getCategoryByShopUserEmail() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Success");
            System.out.println(sessionEmail);
            result.put("data", categoryService.findCategoryByShop(sessionEmail));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-category-by-shop-user-shopId")
    public ResponseEntity<?> getCategoryByShopUserShopId(@RequestParam int shopId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Fail");
            result.put("data", categoryService.findCategoryByShopId(shopId));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Call Api Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
