package m2m_phase2.clothing.clothing.api;

import m2m_phase2.clothing.clothing.data.dto.FavoriteDto;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.FavoriteM;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.service.FavoriteService;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteApi {
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @GetMapping("/checkFavorite")
    public ResponseEntity<?> checkFavorite(@RequestParam("slugUrl") String slugUrl) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (favoriteService.checkFavorite(sessionEmail, slugUrl) != null) {
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
            if (sessionEmail == null) {
                // chưa đăng nhập
                response.put("message", "User not found");
                response.put("status", true);
                return ResponseEntity.ok(response);
            }
            FavoriteDto favoriteDto = new FavoriteDto();

            UserDto userDto = new UserDto();
            userDto.setEmail(sessionEmail);
            UserM userM = userService.getUserByEmail(userDto);
            UserE userE = new UserE();
            userE.setId(userM.getId()); // tìm và set id cho user E

            ProductM productM = productService.findByslug_url(slugUrl); // tìm và set id cho product
            Product product = new Product();
            product.setProductId(productM.getProductId());

            favoriteDto.setUser(userE);
            favoriteDto.setProduct(product);

            FavoriteM favoriteM = favoriteService.checkFavorite(sessionEmail, slugUrl);
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


    @GetMapping("/show-favorite")
    public ResponseEntity<?> showFavorite(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Favorite show successfully");
            result.put("data",favoriteService.getFavoritesByEmail(sessionEmail));
        }catch (Exception e){
            result.put("status", false);
            result.put("message", "Favorite show successfully");
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete-favorite")
    public ResponseEntity<?> deleteFavorite(@RequestParam("id") int id,@RequestParam("productId") int productId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Delete successfully");
            result.put("data",favoriteService.deleteFavoriteProduct(id, productId));
        }catch (Exception e){
            result.put("status", false);
            result.put("message", "Delete no successfully");
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}
