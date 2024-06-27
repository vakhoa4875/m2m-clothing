package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.OrderDetailE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetailE, Integer> {
    @Transactional
    OrderDetailE save(OrderDetailE entity);
}
