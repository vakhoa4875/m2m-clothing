package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.constant.OrderStatus;
import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.repository.OrderRepo;
import m2m_phase2.clothing.clothing.service.OrderService;
import m2m_phase2.clothing.clothing.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public byte updatePaymentStatusByOrderId(OrderDto orderDto) {
        String paymentMethod = OrderStatus.PAID.getValue();
        Integer orderId = orderDto.getOrderId();
        return repo.updatePaymentStatusByOrderId(paymentMethod, orderId);
    }

    @Override
    public void inserOder(OrderDto orderDto) {
        repo.inserOder(orderDto.getOrderId(), orderDto.getPhoneNumber(), orderDto.getDeliveryAddress(), orderDto.getPaymentMethod(), orderDto.getTotalAmount(), orderDto.getOrderStatus());
    }
    public Order findOrderByOrderId(Long orderId) {
        var order = repo.findById(orderId);
        return order.orElse(null);
    }

    @Override
    public void paidOrder(Long orderId) {
        String orderStatus = OrderStatus.PAID.getValue();
        var rowsEffected = repo.updateOrderStatus(orderId, orderStatus);
        System.out.println(">> paid order row effected: " + rowsEffected);
    }

    @Override
    public void updateOrderStatusByOrderId(Long orderId, String orderStatus) {
        var rowsEffected = repo.updateOrderStatus(orderId, orderStatus);
        System.out.println(">> update order status row effected: " + rowsEffected);
    }

//    @Override
//    public byte updatePaymentStatusByOrderId(OrderDto orderDto) {
//        String paymentMethod = OrderStatus.PAID.getValue();
//        Long orderId = orderDto.getOrderId();
//        return repo.updatePaymentStatusByOrderId(paymentMethod, orderId);
//    }


}
