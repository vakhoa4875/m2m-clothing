package m2m_phase2.clothing.clothing.service.impl;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.constant.OrderStatus;
import m2m_phase2.clothing.clothing.data.dto.PaymentDto;
import m2m_phase2.clothing.clothing.paypal.PaypalService;
import m2m_phase2.clothing.clothing.repository.PaymentRepo;
import m2m_phase2.clothing.clothing.service.OrderService;
import m2m_phase2.clothing.clothing.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final OrderService orderService;
    private final PaypalService paypalService;

    @Override
    public byte createPayment(PaymentDto paymentDto) {
        var paymentE = PaymentDto.convertPaymentDtoToPaymentE(
                paymentDto,
                orderService.findOrderByOrderId(paymentDto.getOrderId())
        );
        System.out.println(">>paymentE: " + paymentE);
        var savedPayment = paymentRepo.save(paymentE);
        System.out.println(savedPayment.toString());
        return 1;
    }

    @Override
    public boolean handlePayment(Payment payment, String paymentId, String payerId, PaymentDto paymentDto) {
        paymentDto.setPaymentId(paymentId);
        paymentDto.setPayerId(payerId);
        System.out.println(">>>" + payment.getState());
        if (payment.getState()
                .equalsIgnoreCase(OrderStatus.APPROVED.getValue())) {
            paymentDto.setPaymentStatus(OrderStatus.PAID.getValue());
            this.createPayment(paymentDto);
            orderService.paidOrder(paymentDto.getOrderId());
            return true;
        }
        paymentDto = null;
        return false;
    }

    @Override
    public String createPaypalLink(PaymentDto paymentDto) throws PayPalRESTException {
        var order = orderService.findOrderByOrderId(paymentDto.getOrderId());
        String href = "http://localhost:8083/payment/paypal/error";
        if (!order.getOrderStatus()
                .equals(OrderStatus.NEED_PAYMENT.getValue())) {
            return href;
        }
        String cancelUrl = "http://localhost:8083/payment/paypal/cancel";
        String successUrl = "http://localhost:8083/payment/paypal/executing";
        Payment payment = paypalService.createPayment(
                paymentDto,
                cancelUrl,
                successUrl
        );
        for (Links links : payment.getLinks()) {
            if (links.getRel().equals("approval_url")) {
                href = links.getHref();
                System.out.println(links.getHref());
                return href;
            }
        }
        return href;
    }
}
