package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<Sale,Integer> {
    List<Sale> findAll();

    Sale findBySaleId(int saleId);

    @Transactional
    Sale save(Sale sale);

    @Transactional
    void deleteBySaleId(int saleId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Sale s SET s.saleName = :saleName, s.salePercent = :salePercent, s.saleStart = :saleStart, s.saleEnd = :saleEnd WHERE s.saleId = :saleId")
    void updateSale(
            @Param("saleName") String saleName,
            @Param("salePercent") int salePercent,
            @Param("saleStart") Date saleStart,
            @Param("saleEnd") Date saleEnd,
            @Param("saleId") int saleId);

    @Modifying
    @Transactional
    @Query(value = "update [Product] set sale_ID = :sale_ID where product_id = :product_id", nativeQuery = true)
    void updateProductSaleFromSale(@Param("sale_ID") Integer sale_ID, @Param("product_id") Integer product_id);

}
