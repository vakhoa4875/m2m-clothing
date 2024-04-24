package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.OrderDto;
import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();

    List<Object[]> findOrdersWithUsernameByEmail(String email);

    byte updatePaymentStatusByOrderId(OrderDto orderDto);

    void inserOder(OrderDto orderDto);

}