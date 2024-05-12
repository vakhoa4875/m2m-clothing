package m2m_phase2.clothing.clothing.api;


import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.CategoryService;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/api/getAccountData")
    public Long AllAccount(){
        return accountService.getTotalAccounts();
    }
    @PostMapping("/api/postAccountDK")
    public Long accountDK(){
        return accountService.getDKAccount();
    }

    @PostMapping("/api/postAccountGG")
    public Long accountGG(){
        return accountService.getGGAccount();
    }

    @GetMapping("/api/getProduct")
    public Long AllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("/api/getCategory")
    public Long AllCategory(){
        return categoryService.Category();
    }
    @GetMapping("/api/getProductCountPerCategory")
    public Map<String, Long> getProductCountPerCategory() {
        return categoryService.getProductCountPerCategory();
    }
}
