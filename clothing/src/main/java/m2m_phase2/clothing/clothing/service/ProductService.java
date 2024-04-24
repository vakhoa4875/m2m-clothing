package m2m_phase2.clothing.clothing.service;

import java.util.List;

import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;

public interface ProductService {
	
	List<Product> findAll();
	Product findByslug_url(String slug_url);
	Category findCategoryNameByProductId (Integer productId);
	List<Product> findTop6ByOrderByGiaBanDesc();

	List<Product> findProductsWithSaleInfo();
	List<Product> findBycategory(Integer categoryId);
	void insertProduct(ProductDTO productDTO);
	void updateProduct(ProductDTO productDTO);
}
