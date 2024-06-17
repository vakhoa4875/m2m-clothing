package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SearchRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE (LOWER(p.productName) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword%) AND p.category.category_name = :category")
    List<Product> searchProducts(@Param("keyword") String keyword, @Param("category") String category);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword%")
    List<Product> searchProductsFindByName(@Param("keyword") String keyword);

}
