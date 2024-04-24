package m2m_phase2.clothing.clothing.data.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.Order;
import m2m_phase2.clothing.clothing.data.entity.PaymentE;
import m2m_phase2.clothing.clothing.service.impl.OrderServiceImpl;

@Data
@RequiredArgsConstructor
public class PaymentDto {
    private String paymentId;
    private String payerId;
    private String totalAmount;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String paymentStatus = "Pending";
    private Long orderId;

    public static PaymentE convertPaymentDtoToPaymentE(PaymentDto paymentDto, Order order) {
        PaymentE paymentE = new PaymentE();
        paymentE.setPaymentId(paymentDto.getPaymentId());
        paymentE.setPayerId(paymentDto.getPayerId());
        paymentE.setTotalAmount(Double.parseDouble(paymentDto.getTotalAmount()));
        paymentE.setCurrency(paymentDto.getCurrency());
        paymentE.setMethod(paymentDto.getMethod());
        paymentE.setIntent(paymentDto.getIntent());
        paymentE.setDescription(paymentDto.getDescription());
        paymentE.setPaymentStatus(paymentDto.getPaymentStatus());
        paymentE.setOrder(order); // Liên kết với đơn hàng
        return paymentE;
    }


}
