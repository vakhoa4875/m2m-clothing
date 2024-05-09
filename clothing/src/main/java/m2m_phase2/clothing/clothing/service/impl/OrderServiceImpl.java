package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.constant.OrderStatus;
import m2m_phase2.clothing.clothing.data.dto.OrderDto;
import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.repository.OrderRepo;
import m2m_phase2.clothing.clothing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo repo;

    @Override
    public List<Order> findAllOrders() {
        return repo.findAll();
    }


    @Override
    public List<Object[]> findOrdersWithUsernameByEmail(String email) {
        return repo.findOrdersWithUsernameByEmail(email);
    }

//    @Override
//    public byte updatePaymentStatusByOrderId(OrderDto orderDto) {
//        String paymentMethod = OrderStatus.PAID.getValue();
//        Integer orderId = orderDto.getOrderId();
//        return repo.(paymentMethod, orderId);
//    }

    @Override
    public void inserOder(OrderDto orderDto) {
        repo.inserOder(orderDto.getOrderId(), orderDto.getPhoneNumber(), orderDto.getDeliveryAddress(), orderDto.getPaymentMethod(), orderDto.getTotalAmount(), orderDto.getOrderStatus());
    }

    @Override
    public List<Object[]> findAllUser() {
        return repo.findAllUser();
    }

    public Order findOrderByOrderId(Integer orderId) {
        var order = repo.findById(orderId);
        return order.orElse(null);
    }

    @Override
    public void paidOrder(Integer orderId) {
        String orderStatus = OrderStatus.DELIVERING.getValue();
        var rowsEffected = repo.updateOrderStatus(orderId, orderStatus);
        System.out.println(">> paid order row effected: " + rowsEffected);
    }

    @Override
    public void updateOrderStatusByOrderId(Integer orderId, String orderStatus) {
        var rowsEffected = repo.updateOrderStatus(orderId, orderStatus);
        System.out.println(">> update order status row effected: " + rowsEffected);
    }

//    @Override
//    public byte updatePaymentStatusByOrderId(OrderDto orderDto) {
//        String paymentMethod = OrderStatus.PAID.getValue();
//        Integer orderId = orderDto.getOrderId();
//        return repo.updatePaymentStatusByOrderId(paymentMethod, orderId);
//    }

}
