package m2m_phase2.clothing.clothing.controller;

import m2m_phase2.clothing.clothing.data.dto.CommentDTO;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.model.CommentM;
import m2m_phase2.clothing.clothing.data.model.ProductM;
import m2m_phase2.clothing.clothing.service.impl.CategoryImpl;
import m2m_phase2.clothing.clothing.service.impl.CommentServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductController {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private CommentServiceImpl commentServiceImpl;
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
		List<ProductM> products	= productServiceImpl.findAll();
		model.addAttribute("listProduct",products);
		model.addAttribute("active",7); // hiện bản cateory ở trạng thái trọn All
		return "swappa/assests/html/productAll";
	}

	@Autowired
	private CategoryImpl categoryimpl;
	@GetMapping("/product")
	public String getDetail(@RequestParam String slug_url,Model model) throws SQLException {
		ProductM product = productServiceImpl.findByslug_url(slug_url);
		CommentDTO  commentDTO =  new CommentDTO();
		commentDTO.setProduct(productServiceImpl.findByslug_url(slug_url));
		List<CommentM>  commentM = commentServiceImpl.findByProductId(commentDTO);
		model.addAttribute("listProduct", product);
		model.addAttribute("commentM", commentM);
		return "swappa/assests/html/productDetail";
	}
}
