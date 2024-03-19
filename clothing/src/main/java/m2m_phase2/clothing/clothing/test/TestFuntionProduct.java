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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestFuntionProduct {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("/testfuntionproduct")
	public String testfuntionproduct(Model model) {
		

	

		 
//		 String pictures = list.get(0).getPictures();
//		 System.out.println(pictures);
//		 
//		 String [] ArrayPictures = pictures.split(",");
//		 
//		 for (String string : ArrayPictures) {
//			System.out.println(string);
//		 }
//		 System.out.println(ArrayPictures[0]);
//		 model.addAttribute("pictures",ArrayPictures); 
//		 for (Product product : list) {
//			System.out.println(list.toString()+"\n");
//			System.out.println("-------------------------------");
//		}
		 
 		List<Product> list =   productServiceImpl.findAll();
 		List<String> firstPictures = new ArrayList<>();
		 
		 
 		for (Product product : list) {
 			String pathPictures = product.getPictures();
			String[] arrayPictures = pathPictures.split(",");
			if(arrayPictures.length > 0) {
				firstPictures.add(arrayPictures[0]);
			}
 		}
 		
 		model.addAttribute("products", list) ;
 		model.addAttribute("firstimg",firstPictures);
		
 		
		return "Front_End/SanPham";
	}
	
}
