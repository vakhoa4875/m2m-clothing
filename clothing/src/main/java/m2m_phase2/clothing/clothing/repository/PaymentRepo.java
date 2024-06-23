package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.PaymentE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentE, Integer> {
    PaymentE findByPaymentId(String paymentId);
}
