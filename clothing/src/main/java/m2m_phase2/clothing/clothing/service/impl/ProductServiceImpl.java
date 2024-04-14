package m2m_phase2.clothing.clothing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public List<Product> findTop6ByOrderByGiaBanDesc() {
		Pageable pageable = PageRequest.of(0, 6); //bắt đầu từ 0 và kích thước 6
		return repo.findTop6ByOrderByGiaBanDesc(pageable);
	}

	@Override
	public List<Product> findProductsWithSaleInfo() {
		return repo.findProductsWithSaleInfo();
	}
}
