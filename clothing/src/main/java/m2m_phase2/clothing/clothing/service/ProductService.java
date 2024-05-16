package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.ProductDTO;
import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.model.ProductM;

import java.util.List;

public interface ProductService {
	
	List<ProductM> findAll();
	ProductM findByslug_url(String slug_url);
	Category findCategoryNameByProductId (Integer productId);
	List<Product> findTop6ByOrderByGiaBanDesc();

	List<Product> findProductsWithSaleInfo();
	List<ProductM> findBycategory(Integer categoryId);
	void insertProduct(ProductDTO productDTO);
	void updateProduct(ProductDTO productDTO);
	void saveImgAndVideo(ProductDTO productDTO);
	void deleteByProductId(int product_id) ;
	Long getAllProducts();
}
