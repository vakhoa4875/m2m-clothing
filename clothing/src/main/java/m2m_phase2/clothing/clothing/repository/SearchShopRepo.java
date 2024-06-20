package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.ShopE;
import m2m_phase2.clothing.clothing.data.model.SearchShopM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchShopRepo extends JpaRepository<ShopE,Integer> {
    @Query("SELECT s FROM ShopE s WHERE LOWER(s.nameShop) LIKE %:nameShop%")
    List<ShopE> searchShopsByName(@Param("nameShop") String nameShop);

    ShopE findById(int shopId);
}
