package m2m_phase2.clothing.clothing.data.mapper;

import m2m_phase2.clothing.clothing.data.dto.PaymentDTO;
import m2m_phase2.clothing.clothing.data.entity.PaymentE;
import m2m_phase2.clothing.clothing.service.OrderService;

public class PaymentMapper {

    public static PaymentDTO toDTO(PaymentE payment) {
        if (payment == null) {
            return null;
        }

        PaymentDTO dto = new PaymentDTO();
        dto.setSysPaymentId(payment.getSysPaymentId());
        dto.setPaymentId(payment.getPaymentId());
        dto.setPayerId(payment.getPayerId());
        dto.setTotalAmount(payment.getTotalAmount());
        dto.setCurrency(payment.getCurrency());
        dto.setMethod(payment.getMethod());
        dto.setIntent(payment.getIntent());
        dto.setDescription(payment.getDescription());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setDateCreated(payment.getDateCreated());
        dto.setDateUpdated(payment.getDateUpdated());
        dto.setOrderId(payment.getOrder().getOrderId());

        return dto;
    }

    public static PaymentE toEntity(PaymentDTO dto, OrderService orderService) {
        if (dto == null) {
            return null;
        }

        PaymentE payment = new PaymentE();
        payment.setSysPaymentId(dto.getSysPaymentId());
        payment.setPaymentId(dto.getPaymentId());
        payment.setPayerId(dto.getPayerId());
        payment.setTotalAmount(dto.getTotalAmount());
        payment.setCurrency(dto.getCurrency());
        payment.setMethod(dto.getMethod());
        payment.setIntent(dto.getIntent());
        payment.setDescription(dto.getDescription());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setDateCreated(dto.getDateCreated());
        payment.setDateUpdated(dto.getDateUpdated());
        // Assuming you have a method to fetch Order entity by ID
         payment.setOrder(orderService.findOrderByOrderId(dto.getOrderId()));

        return payment;
    }
}

