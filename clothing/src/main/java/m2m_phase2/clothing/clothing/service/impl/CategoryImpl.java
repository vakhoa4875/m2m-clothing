package m2m_phase2.clothing.clothing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m2m_phase2.clothing.clothing.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.repository.CategoryRepo;
import m2m_phase2.clothing.clothing.service.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;
	@Autowired
	private ProductRepo productRepo;
	
	
	@Override
	public List<Category> findAll() {
		return repo.findAll();
	}

	@Override
	public Long Category() {
		long count = repo.count();
		return count;
	}

	@Override
	public Map<String, Long> getProductCountPerCategory() {
		List<Category> categories = repo.findAll();
		Map<String, Long> productCountMap = new HashMap<>();

		for (Category category : categories) {
			Long productCount = productRepo.countByCategory(category.getCategory_id());
			productCountMap.put(category.getCategory_name(), productCount);
		}

		return productCountMap;
	}

	@Override
	public List<Category> findCategoryByShop(String email) {
		return repo.findCategoryByShop(email);
	}

	@Override
	public List<Category> findCategoryByShopId(int shopId) {
		return repo.findCategoryByShopId(shopId);
	}

}
