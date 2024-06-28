package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import m2m_phase2.clothing.clothing.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryApi {
    private final CategoryService categoryService;
    private final AuthService authService;

    @GetMapping("/getCategoryByShopUserEmail")
    public ResponseEntity<?> getCategoryByShopUserEmail() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Success");
            System.out.println(authService.getCurrentUserEmail());
            result.put("data", categoryService.findCategoryByShop(authService.getCurrentUserEmail()));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
