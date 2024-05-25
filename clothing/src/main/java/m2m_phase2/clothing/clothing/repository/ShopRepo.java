package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends JpaRepository<Product, Integer> {

}
