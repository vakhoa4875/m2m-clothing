package m2m_phase2.clothing.clothing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import m2m_phase2.clothing.clothing.entity.Category;
import m2m_phase2.clothing.clothing.entity.Product;
import m2m_phase2.clothing.clothing.service.impl.CategoryImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private CategoryImpl category;

	
	@GetMapping("/allproduct")
	public String testfuntionproduct(Model model) {
			 

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
