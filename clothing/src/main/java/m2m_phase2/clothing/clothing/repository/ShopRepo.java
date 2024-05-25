package m2m_phase2.clothing.clothing.repository;

<<<<<<< HEAD
import m2m_phase2.clothing.clothing.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends JpaRepository<Product, Integer> {

=======
import m2m_phase2.clothing.clothing.data.entity.ShopE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepo extends JpaRepository<ShopE, Integer> {

    @Query(value = "select s from ShopE s where s.userE.email = :email")
    ShopE findShopByUser(@Param("email") String email);

    List<ShopE> findAll();
>>>>>>> main
}
