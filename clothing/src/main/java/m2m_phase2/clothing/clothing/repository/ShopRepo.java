package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.ShopE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShopRepo extends JpaRepository<ShopE, Integer> {

    @Query(value = "select s from ShopE s where s.userE.email = :email")
    ShopE findShopByUser(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "insert into Shop (name_shop, date_established, id) " +
            "values (:name_shop, :date_established, :id)", nativeQuery = true)
    int insertShop(@Param("name_shop") String nameShop,
                    @Param("date_established") Date dateEstablished,
                    @Param("id") Long id);
}
