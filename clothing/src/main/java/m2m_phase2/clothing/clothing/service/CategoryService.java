package m2m_phase2.clothing.clothing.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import m2m_phase2.clothing.clothing.data.entity.Category;

public interface CategoryService {
	List<Category> findAll();
	Long Category();
	Map<String, Long> getProductCountPerCategory();
	List<Category> findCategoryByShop(String email) throws SQLException;
}
