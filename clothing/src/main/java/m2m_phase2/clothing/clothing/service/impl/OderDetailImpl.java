package m2m_phase2.clothing.clothing.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import m2m_phase2.clothing.clothing.data.dto.OrderDto;
import m2m_phase2.clothing.clothing.repository.OrderDetailRepo;
import m2m_phase2.clothing.clothing.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OderDetailImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void UpdateOderDetail(OrderDto orderDto) {
        orderDetailRepo.UpdateOderDetail(orderDto.getOrderId(),orderDto.getUsername(), Integer.valueOf(orderDto.getSoluong()), Float.valueOf("100.0"));
    }

    /**
     * Phương thức này dùng để lấy id của order khi được sinh ra để cập nhật số lượng trong ordertailx
    * */
    @Override
    public int getLastInsertedOrderId(){
        Query query = entityManager.createNativeQuery("SELECT IDENT_CURRENT('Order')");
        int orderId = ((BigDecimal) query.getSingleResult()).intValue();
        System.out.println(orderId);
        return orderId;
    }
}
