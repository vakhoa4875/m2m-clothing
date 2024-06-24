package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.PaymentE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentE, Integer> {
    PaymentE findByPaymentId(String paymentId);
    @Transactional
    @Modifying
    @Query(nativeQuery = true,
    value = "update payment " +
            "set payment_status = 'CANCELLED' " +
            "where payment_id = :paymentId")
    Integer cancelPayment(@Param("paymentId") String paymentId);
}
