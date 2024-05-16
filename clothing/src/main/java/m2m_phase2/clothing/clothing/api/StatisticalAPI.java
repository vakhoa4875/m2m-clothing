package m2m_phase2.clothing.clothing.api;


import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.CategoryService;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatisticalAPI {
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/api/getAccountData")
    public Long AllAccount() {
        return accountService.getTotalAccounts();
    }

    @PostMapping("/api/postAccountDK")
    public Long accountDK() {
        return accountService.getDKAccount();
    }

    @PostMapping("/api/postAccountGG")
    public Long accountGG() {
        return accountService.getGGAccount();
    }

    @GetMapping("/api/getProduct")
    public Long AllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/api/getCategory")
    public Long AllCategory() {
        return categoryService.Category();
    }

    @GetMapping("/api/getProductCountPerCategory")
    public Map<String, Long> getProductCountPerCategory() {
        return categoryService.getProductCountPerCategory();
    }

    // 028_Khoa
    @GetMapping("/api-admin/getTop10SoldProduct")
    public ResponseEntity<?> doGetTop10SoldProduct(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        var top10 = statisticService.getTop10SoldProductByMonthAndYear(month, year);
        return ResponseEntity.ok(top10);
    }
    //
}
