package m2m_phase2.clothing.clothing.api;


import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.service.impl.CategoryImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductApi {
	
	@Autowired
	private ProductServiceImpl productserviceimpl;
	
	@Autowired
	private CategoryImpl categoryimpl;
	
	@GetMapping("/allproductapi")
	public List<ProductM> getAllProduct() {
		return productserviceimpl.findAll();
	}
	
	@GetMapping("/findbyproductidapi")
	public ProductM getfindbyproductid(@RequestParam String slug_url) {
		return productserviceimpl.findByslug_url(slug_url);
	}
	
	@GetMapping("/findcategorynamebyproductidapi")
	public Category getfindcategorynamebyproductid(@RequestParam Integer product_id) {
		return productserviceimpl.findCategoryNameByProductId(product_id);
	}
	
	@GetMapping("/allcategoryapi")
	public List<Category> getallcategoryapi() {
		return categoryimpl.findAll();
	}

	@GetMapping("/findTop6ByOrderByGiaBanDesc")
	public List<Product> findTop6ByOrderByGiaBanDesc(){
		return productserviceimpl.findTop6ByOrderByGiaBanDesc();
	}


	@GetMapping("/sale")
	public List<Product> productWithSaleInfo() {
		return productserviceimpl.findProductsWithSaleInfo();
	}

	@PostMapping("/insertProduct")
	public ResponseEntity<?> insertProduct(@RequestBody ProductDTO productDTO){
		try {
			productserviceimpl.insertProduct(productDTO);
			productserviceimpl.saveImgAndVideo(productDTO);
		} catch (Exception e) {
			System.out.println("Call API Failed: /insertProduct");
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok("Product inserted successfully.");

	}

	@PostMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO){
		try {
			productserviceimpl.updateProduct(productDTO);
			productserviceimpl.saveImgAndVideo(productDTO);
		} catch (Exception e) {
			System.out.println("Call API Failed: /update");
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok("Product update successfully.");
	}

	@DeleteMapping("/products/{product_id}")
	public void deleteProductById(@PathVariable("product_id") int productId) {
		productserviceimpl.deleteByProductId(productId);
	}
}
