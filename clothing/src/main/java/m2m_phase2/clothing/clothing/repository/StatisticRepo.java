package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepo extends JpaRepository<Product, Integer> {
    @Query(nativeQuery = true, value = "exec dbo.getTop10SoldProduct :month, :year")
    List<Integer> getTop10SoldProductByMonthAndYear(@Param("month") int month,@Param("year") int year);

    @Query(nativeQuery = true, value = "exec dbo.getActiveMonths")
    List<String> getActiveMonths();
    
    @Query(nativeQuery = true, value = "exec dbo.getVoucherUsedInMonth")
    List<Object[]> getVoucherUsedInMonth();

    @Query(nativeQuery = true, value = "exec dbo.getTopUsedVoucher :month, :year")
    List<Object[]> getTopUsedVoucher(@Param("month") int month,@Param("year") int year);
}
