package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepo extends JpaRepository<Order, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE [order_detail] SET nameproduct = :nameproduct, quatity = :quatity, toal_product = :toal_product WHERE order_id_detail = :order_id_detail", nativeQuery = true)
    void UpdateOderDetail(@Param("order_id_detail") Integer order_id_detail, @Param("nameproduct") String nameproduct, @Param("quatity") Integer quatity, @Param("toal_product") Float toal_product);
}
