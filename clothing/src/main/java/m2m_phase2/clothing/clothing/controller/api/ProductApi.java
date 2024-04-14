package m2m_phase2.clothing.clothing.controller.api;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import m2m_phase2.clothing.clothing.entity.Category;
import m2m_phase2.clothing.clothing.entity.Product;
import m2m_phase2.clothing.clothing.service.impl.CategoryImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProductApi {
	
	@Autowired
	private ProductServiceImpl productserviceimpl;
	
	@Autowired
	private CategoryImpl categoryimpl;
	
	@GetMapping("/allproductapi")
	public List<Product> getAllProduct() {
		return productserviceimpl.findAll();
	}
	
	@GetMapping("/findbyproductidapi")
	public Product getfindbyproductid(@RequestParam String slug_url) {
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
	public List<Product> productWithSaleInfo(){
		return productserviceimpl.findProductsWithSaleInfo();
    
	@GetMapping("/findproductbycategoryid")
	public List<Product> getfindbycategoryid(Category category){
		return productserviceimpl.findBycategory(category);

	}
}
