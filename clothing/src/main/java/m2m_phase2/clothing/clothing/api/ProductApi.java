package m2m_phase2.clothing.clothing.api;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.impl.CommentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductApi {
    private final ProductService productService;
    private final ProductRepo productRepo;
    private final CommentServiceImpl commentService;

    @GetMapping("/getAll")
    public List<ProductM> doGetAllProduct() {
        return productService.findAll();
    }

    @GetMapping("/getByProductID")
    public ProductM doGetByProductID(@RequestParam String slug_url) {
        return productService.findByslug_url(slug_url);
    }

    @GetMapping("/getProductsByCategory")
    public ResponseEntity<?> getcategoryType(@RequestParam Integer categoryId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Call Api Success");
            result.put("data", productService.findBycategory(categoryId));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", "Call Api Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getCategoryNameByProductID")
    public Category doGetCategoryNameByProductID(@RequestParam Integer product_id) {
        return productService.findCategoryNameByProductId(product_id);
    }


    @GetMapping("/getProductOnSale")
    public List<Product> productWithSaleInfo() {
        return productService.findProductsWithSaleInfo();
    }

    @PostMapping("/create")
    public ResponseEntity<?> insertProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.insertProduct(productDTO);
            productService.saveImgAndVideo(productDTO);
        } catch (Exception e) {
            System.out.println("Call API Failed: /insertProduct");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Product inserted successfully.");

    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.updateProduct(productDTO);
            productService.saveImgAndVideo(productDTO);
        } catch (Exception e) {
            System.out.println("Call API Failed: /update");
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Product update successfully.");
    }

    @DeleteMapping("/deleteByProductID/{product_id}")
    public void deleteProductById(@PathVariable("product_id") int productId) {
        productService.deleteByProductId(productId);
    }

    @GetMapping("/getCommentsByProductID")
    public ResponseEntity<?> getCommentsByProductID(int productId) {
        List<Object[]> comments = productRepo.findCommentByProductId(productId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/createComment")
    public ResponseEntity<?> doPostCreateComment(@RequestBody CommentDTO commentDTO, HttpSession session) {
        try {
            byte rowEffected = commentService.createComment(commentDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comment created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error calling API: /api/product/createComment");
            throw new RuntimeException(e);
        }

    }
}
