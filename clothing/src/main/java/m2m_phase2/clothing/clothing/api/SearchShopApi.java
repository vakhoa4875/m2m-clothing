package m2m_phase2.clothing.clothing.api;

import m2m_phase2.clothing.clothing.data.model.SearchShopM;
import m2m_phase2.clothing.clothing.service.SearchShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchShopApi {
    @Autowired
    private SearchShopService searchShopService;

    @GetMapping("/search-shop")
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
}
