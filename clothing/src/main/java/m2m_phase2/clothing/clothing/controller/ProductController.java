package m2m_phase2.clothing.clothing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.service.impl.CategoryImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private CategoryImpl category;

	
	@GetMapping("/categoryType")
	public String getcategoryType(@RequestParam Integer categoryId ,Model model) {
		List<Product> products	= productServiceImpl.findBycategory(categoryId);
		model.addAttribute("listProduct",products);
		model.addAttribute("active",categoryId);
		return "swappa/assests/html/productAll";
	}

	@GetMapping("/AllcategoryType")
	public String getAllcategory(Model model) {
		List<Product> products	= productServiceImpl.findAll();
		model.addAttribute("listProduct",products);
		return "swappa/assests/html/productAll";
	}

	@Autowired
	private CategoryImpl categoryimpl;
	@GetMapping("/product")
	public String getDetail(@RequestParam String slug_url,Model model) {
		Product product  = productServiceImpl.findByslug_url(slug_url);
		model.addAttribute("listProduct", product);
		return "swappa/assests/html/productDetail";
	}
}
