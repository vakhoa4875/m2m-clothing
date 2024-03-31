package m2m_phase2.clothing.clothing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.entity.Category;
import m2m_phase2.clothing.clothing.entity.Product;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo repo;
	
	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}

	@Override
	public Product findByslug_url(String slugUrl) {
		return repo.findByslugUrl( slugUrl);
	}
	
	@Override
	public Category findCategoryNameByProductId(Integer productId) {
		return repo.findCategoryNameByProductId(productId);
	}
	
}
