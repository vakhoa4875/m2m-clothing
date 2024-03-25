package m2m_phase2.clothing.clothing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import m2m_phase2.clothing.clothing.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	List<Product> findAll();
	
	Product findByproductId(Integer id);
	
    @Query("SELECT p.category.category_name FROM Product p WHERE p.productId = :productId")
    String findCategoryNameByProductId(Integer productId);

	
}	
