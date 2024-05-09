package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.PaymentE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentE, Integer> {
    @Override
    <S extends PaymentE> S save(S entity);
}
