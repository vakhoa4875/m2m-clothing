package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.AccountGGDTO;
import m2m_phase2.clothing.clothing.data.entity.AccountGGE;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import m2m_phase2.clothing.clothing.service.*;
import m2m_phase2.clothing.clothing.service.impl.CategoryImpl;
import m2m_phase2.clothing.clothing.service.impl.VoucherServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
@Slf4j
public class PublicAPI {
    private final AccountGGService accountGGService;
    private final AuthService authService;
    private final CategoryService categoryService;
    private final FavoriteService favoriteService;
    private final ProductService productService;
    private final ProductRepo productRepo;
    private final ShopService shopService;
    private final VoucherServiceImpl voucherService;
    private final CategoryImpl categoryimpl;

    @PostMapping("/saveGGAccount")
    public ResponseEntity<?> doPostSaveGGAccount(@RequestBody AccountGGDTO accountGGDTO) {
        try {
            accountGGService.saveAccountGG(accountGGDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("UserGG đã được lưu thành công.");
        } catch (SQLException e) {
            log.error("Đã xảy ra lỗi khi tạo UserGG", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi tạo UserGG.");
        }
    }

    @PostMapping("/findGGAccountByEmail")
    public ResponseEntity<?> doPostFindGGAccountByEmail(@RequestBody AccountGGDTO accountGGDTO) {
        try {
            AccountGGE accountGGE = accountGGService.findByEmailGG(accountGGDTO);
            return ResponseEntity.ok(accountGGE);
        } catch (SQLException e) {
            log.error("Đã xảy ra lỗi khi tim account gg", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi tìm UserGG.");
        }
    }

    @GetMapping("/isAuthenticated")
    public boolean isLoggedIn() {
        return authService.getCurrentUserEmail() != null;
    }

    @GetMapping("/getCategoryByShopUserShopID")
    public ResponseEntity<?> getCategoryByShopUserShopId(@RequestParam int shopId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Fail");
            result.put("data", categoryService.findCategoryByShopId(shopId));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/showFavorite")
    public ResponseEntity<?> showFavorite() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Favorite show successfully");
            result.put("data", favoriteService.getFavoritesByEmail(authService.getCurrentUserEmail()));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Favorite show successfully");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getShopIDByProductID")
    public ResponseEntity<Map<String, Integer>> getShopIdByProductId(@RequestParam("productId") Integer productId) {
        Integer shopId = productRepo.findShopIdByProductId(productId);
        if (shopId != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("shop_id", shopId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getShop")
    public ResponseEntity<List<Object[]>> doGetShopDetails(@RequestParam("shop_id") Integer shop_id) {
        var shop = shopService.getShopDetails(shop_id);
        return ResponseEntity.ok(shop);
    }

    @GetMapping("/getListProductByCategoryAndShopId")
    public ResponseEntity<?> findProductByShopCategoryShopId(@RequestParam Integer categoryId, @RequestParam int shopId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Success");
            result.put("data", productService.findProductByShopCategoryShopId(categoryId, shopId));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllVouchers")
    public ResponseEntity<?> doGetAllVouchers() {
        List<?> listVoucher;
        try {
            listVoucher = voucherService.findAll();
        } catch (Exception e) {
            System.out.println("Call API Failed: /api/public/getAllVouchers");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(listVoucher);
    }

    @GetMapping("/getAllCategories")
    public List<Category> doGetAllCategories() {
        return categoryimpl.findAll();
    }

    @GetMapping("/findTop6ByOrderByGiaBanDesc")
    public List<Product> findTop6ByOrderByGiaBanDesc() {
        return productService.findTop6ByOrderByGiaBanDesc();
    }
}
