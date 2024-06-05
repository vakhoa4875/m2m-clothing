package m2m_phase2.clothing.clothing.api;

import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class SearchApi {
    @Autowired
    private SearchService searchService;

    @PostMapping("/api/search")
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
}
