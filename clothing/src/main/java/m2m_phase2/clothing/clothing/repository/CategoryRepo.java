package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	List<Category> findAll();

	@Query(value = "select c from Category c join Product p on p.category.category_id = c.category_id where p.shopE.userE.email = :email")
	List<Category> findCategoryByShop(@Param("email") String email);
}
