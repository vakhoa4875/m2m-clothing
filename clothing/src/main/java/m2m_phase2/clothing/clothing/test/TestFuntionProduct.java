package m2m_phase2.clothing.clothing.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import m2m_phase2.clothing.clothing.entity.Product;
import m2m_phase2.clothing.clothing.service.ProductService;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestFuntionProduct {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("/testfuntionproduct")
	public String testfuntionproduct(Model model) {
			 
		List<Product> list =   productServiceImpl.findAll();
 		model.addAttribute("products", list) ;
		return "Front_End/SanPham";
	}
	
	@GetMapping("/product/{id}")
	public String getDetail(@PathVariable Integer id, Model model ) {
		
		Product product = productServiceImpl.findByproductId(id);
		model.addAttribute("product",product);

// 		List<String> firstPictures = new ArrayList<>();
//		 
//		 
// 		for (Product product : list) {
// 			String pathPictures = product.getPictures();
//			String[] arrayPictures = pathPictures.split(",");
//			if(arrayPictures.length > 0) {
//				firstPictures.add(arrayPictures[0]);
//			}
// 		}
// 		model.addAttribute("firstimg",firstPictures); 
		
		String pathPitures = product.getPictures();
		
		String[] arrayPictures = pathPitures.split(",");
		model.addAttribute("arraypictures",arrayPictures);
		
		return "Front_End/ChiTietSanPham";
	}
	
	
}
