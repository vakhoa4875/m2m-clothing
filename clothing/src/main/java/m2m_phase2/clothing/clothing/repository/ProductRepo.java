package m2m_phase2.clothing.clothing.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import m2m_phase2.clothing.clothing.entity.Category;
import m2m_phase2.clothing.clothing.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	List<Product> findAll();
	
	Product findByslugUrl(String slugUrl);
	
    @Query("SELECT c  FROM Product p JOIN p.category c WHERE p.productId = :productId")
    Category findCategoryNameByProductId(Integer productId);
    @Query("SELECT p FROM Product p LEFT JOIN p.sale s ORDER BY p.sold DESC")
    List<Product> findTop6ByOrderByGiaBanDesc(Pageable pageable); //Pageable được chuyển vào để lấy 6 sản phẩm có giá bán cao nhất

    @Query("SELECT p, s FROM Product p LEFT JOIN p.sale s")
    List<Product> findProductsWithSaleInfo();
}
