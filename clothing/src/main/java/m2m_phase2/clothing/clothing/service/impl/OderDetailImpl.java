package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.OrderDto;
import m2m_phase2.clothing.clothing.repository.OrderDetailRepo;
import m2m_phase2.clothing.clothing.service.OrderDetailService;
import m2m_phase2.clothing.clothing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OderDetailImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public void UpdateOderDetail(OrderDto orderDto) {
        orderDetailRepo.UpdateOderDetail(orderDto.getOrderId(),orderDto.getUsername(), Integer.valueOf(orderDto.getSoluong()), Float.valueOf("100.0"));
    }
}
