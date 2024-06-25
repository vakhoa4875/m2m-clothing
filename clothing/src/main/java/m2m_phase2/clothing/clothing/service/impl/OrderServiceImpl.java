package m2m_phase2.clothing.clothing.service.impl;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.constant.OrderStatus;
import m2m_phase2.clothing.clothing.data.dto.OrderDto;
import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.repository.OrderRepo;
import m2m_phase2.clothing.clothing.repository.ProductRepo;
import m2m_phase2.clothing.clothing.repository.UserRepo;
import m2m_phase2.clothing.clothing.repository.VoucherRepo;
import m2m_phase2.clothing.clothing.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo repo;
    private final UserRepo userRepo;
    private final VoucherRepo voucherRepo;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

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

    @Override
    public Order saveOrder(OrderDto orderDto) {
        orderDto.setOrderCode();
        orderDto.getOrderDetails().forEach(e -> e.setOrderCode(orderDto.getOrderCode()));
        var order = OrderDto.convertOrderDtoToOrder(orderDto, userRepo, voucherRepo, orderRepo, productRepo);
        return orderRepo.save(order);
    }

    public Order findOrderByOrderId(Integer orderId) {
        var order = repo.findById(orderId);
        return order.orElse(null);
    }

    @Override
    public void paidOrder(Integer orderId) {
        String orderStatus = OrderStatus.DELIVERING.getValue();
        repo.updateOrderStatus(orderId, orderStatus);
    }

    @Override
    public void updateOrderStatusByOrderId(Integer orderId, String orderStatus) {
        repo.updateOrderStatus(orderId, orderStatus);
    }
}
