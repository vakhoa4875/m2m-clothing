package m2m_phase2.clothing.clothing.service;

import java.util.List;

import m2m_phase2.clothing.clothing.entity.Product;

public interface ProductService {
	
	List<Product> findAll();
	Product findByproductId(Integer id);
	String findCategoryNameByProductId (Integer productId);
}
