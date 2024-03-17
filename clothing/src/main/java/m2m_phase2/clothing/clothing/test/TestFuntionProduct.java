package m2m_phase2.clothing.clothing.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public String testfuntionproduct() {
		 List<Product> list =   productServiceImpl.findAll();
		 for (Product product : list) {
			System.out.println(list.toString());
		}
		 return "Front_End/SanPham";
	}
	
}
