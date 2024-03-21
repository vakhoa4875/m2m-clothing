package m2m_phase2.clothing.clothing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import m2m_phase2.clothing.clothing.entity.Product;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
@Controller
public class ProductController {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("/allproduct")
	public String testfuntionproduct(Model model) {
			 
		List<Product> list =   productServiceImpl.findAll();
 		model.addAttribute("products", list) ;
		return "Front_End/SanPham";
	}
	
	@GetMapping("/product/{id}")
	public String getDetail(@PathVariable Integer id, Model model ) {
		
		Product product = productServiceImpl.findByproductId(id);
		model.addAttribute("product",product);
		model.addAttribute("arraypictures",productServiceImpl.getProductPictures(product));
		model.addAttribute("descriptions",productServiceImpl.getProductDescriptions(product));
		model.addAttribute("sold",productServiceImpl.getProductSold(product.getSold()));
		model.addAttribute("average_rate",productServiceImpl.getProductAverage_rate(product.getAverageRate()));
		return "Front_End/ChiTietSanPham";
	}
}
