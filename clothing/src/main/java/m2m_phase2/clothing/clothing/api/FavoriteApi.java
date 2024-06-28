package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.dto.FavoriteDto;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.FavoriteM;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import m2m_phase2.clothing.clothing.service.FavoriteService;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;
    private final UserService userService;
    private final ProductService productService;
    private final AuthService authService;

    @GetMapping("/checkFavorite")
    public ResponseEntity<?> checkFavorite(@RequestParam("slugUrl") String slugUrl) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (favoriteService.checkFavorite(authService.getCurrentUserEmail(), slugUrl) != null) {
                response.put("message", "Favorite existed");
                response.put("status", true);
            } else {
                response.put("message", "Favorite not existed");
                response.put("status", true);
            }
        } catch (Exception e) {
            response.put("message", "Call API failed");
            response.put("status", false);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveFavorite")
    public ResponseEntity<?> saveFavorite(@RequestParam("slugUrl") String slugUrl) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (authService.getCurrentUserEmail() == null) {
                // chưa đăng nhập
                response.put("message", "User not found");
                response.put("status", true);
                return ResponseEntity.ok(response);
            }
            FavoriteDto favoriteDto = new FavoriteDto();

            UserDto userDto = new UserDto();
            userDto.setEmail(authService.getCurrentUserEmail());
            UserM userM = userService.getUserByEmail(userDto);
            UserE userE = new UserE();
            userE.setId(userM.getId()); // tìm và set id cho user E

            ProductM productM = productService.findByslug_url(slugUrl); // tìm và set id cho product
            Product product = new Product();
            product.setProductId(productM.getProductId());

            favoriteDto.setUser(userE);
            favoriteDto.setProduct(product);

            FavoriteM favoriteM = favoriteService.checkFavorite(authService.getCurrentUserEmail(), slugUrl);
            if (favoriteM != null) {
                // Nếu đã tồn tại, xóa mục yêu thích
                response.put("message", "Favorite deleted successfully");
                response.put("status", true);
                favoriteService.deleteFavorite(favoriteDto);
            } else {
                // Nếu chưa tồn tại, thêm mục yêu thích
                response.put("message", "Favorite saved successfully");
                response.put("status", true);
                favoriteService.addFavorite(favoriteDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Favorite save failed");
            response.put("status", false);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/removeProduct")
    public ResponseEntity<?> deleteFavorite(@RequestParam("id") int id, @RequestParam("productId") int productId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Delete successfully");
            result.put("data", favoriteService.deleteFavoriteProduct(id, productId));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Delete no successfully");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
