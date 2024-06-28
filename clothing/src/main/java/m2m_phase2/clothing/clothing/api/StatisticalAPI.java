package m2m_phase2.clothing.clothing.api;


import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.repository.StatisticRepo;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.CategoryService;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistic")
public class StatisticalAPI {
    private final AccountService accountService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final StatisticService statisticService;
    private final StatisticRepo statisticRepo;

    @GetMapping("/getAccountData")
    public Long AllAccount() {
        return accountService.getTotalAccounts();
    }

    @PostMapping("/postAccountDK")
    public Long accountDK() {
        return accountService.getDKAccount();
    }

    @PostMapping("/postAccountGG")
    public Long accountGG() {
        return accountService.getGGAccount();
    }

    @GetMapping("/getProduct")
    public Long AllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/getCategory")
    public Long AllCategory() {
        return categoryService.Category();
    }

    @GetMapping("/getProductCountPerCategory")
    public Map<String, Long> doGetProductCountPerCategory() {
        return categoryService.getProductCountPerCategory();
    }

    // 028_Khoa
    @GetMapping("/getTop10SoldProduct")
    public ResponseEntity<?> doGetTop10SoldProduct(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        var top10 = statisticService.getTop10SoldProductByMonthAndYear(month, year);
        return ResponseEntity.ok(top10);
    }
    @GetMapping("/getActiveMonths")
    public ResponseEntity<?> doGetActiveMonths() {
        return ResponseEntity.ok(statisticService.getActiveMonths());
    }
    @GetMapping("/getVoucherUsedInMonth")
    public ResponseEntity<?> doGetVoucherUsedInMonth() {
        return ResponseEntity.ok(statisticRepo.getVoucherUsedInMonth());
    }
    @GetMapping("/getTopUsedVoucher")
    public ResponseEntity<?> doGetTopUsedVoucher(@RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        var top10 = statisticService.getTopUsedVoucher(month, year);
        return ResponseEntity.ok(top10);
    }
    //
}
