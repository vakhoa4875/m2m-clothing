package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopAdminRepo extends JpaRepository<Product, Integer> {
    Product save(Product product);
    List<Product> findByShopE_ShopId(int shopId);


}
