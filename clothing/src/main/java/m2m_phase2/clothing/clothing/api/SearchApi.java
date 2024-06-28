package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.data.model.SearchShopM;
import m2m_phase2.clothing.clothing.data.model.ShowShopSearchM;
import m2m_phase2.clothing.clothing.service.SearchService;
import m2m_phase2.clothing.clothing.service.SearchShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchApi {
    private final SearchService searchService;
    private final SearchShopService searchShopService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String keyword, @RequestParam(required = false) String type) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ProductM> products;
            if (type.equalsIgnoreCase("All") || type.isEmpty()) {
                products = searchService.searchProductsByName(keyword);
            } else {
                products = searchService.searchProducts(keyword, type);
            }
            response.put("success", true);
            response.put("message", "Call successful");
            response.put("data", products);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.put("success", false);
            response.put("message", "Call failed");
            response.put("data", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/shop/q")
    public ResponseEntity<?> searchShop(@RequestParam String nameShop) {
        List<SearchShopM> searchShopMList = searchShopService.searchShop(nameShop);
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Call API success !");
            result.put("data", searchShopMList);
            return ResponseEntity.ok(result);
        }catch(Exception e) {
            result.put("success", false);
            result.put("message", "Call API false !");
            result.put("data", null);
            return ResponseEntity.status(500).body(result);
        }
    }

    @GetMapping("/shop")
    public ResponseEntity<?> viewShop(@RequestParam int shopId) {
        ShowShopSearchM showShopSearchM = searchShopService.showShopSearch(shopId);
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Call API success !");
            result.put("data", showShopSearchM);
            return ResponseEntity.ok(result);
        }catch(Exception e) {
            result.put("success", false);
            result.put("message", "Call API false !");
            result.put("data", null);
            return ResponseEntity.status(500).body(result);
        }
    }
}
