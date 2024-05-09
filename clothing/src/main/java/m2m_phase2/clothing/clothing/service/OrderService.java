package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.OrderDto;
import m2m_phase2.clothing.clothing.data.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();

    List<Object[]> findOrdersWithUsernameByEmail(String email);

    Order findOrderByOrderId(Integer orderId);


    void paidOrder(Integer orderId);

    void updateOrderStatusByOrderId(Integer orderId, String orderStatus);

//    byte updatePaymentStatusByOrderId(OrderDto orderDto);

    void inserOder(OrderDto orderDto);

    List<Object[]> findAllUser();



}