package m2m_phase2.clothing.clothing.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import m2m_phase2.clothing.clothing.data.dto.PaymentDto;

import java.util.Map;

public interface PaymentService {
    byte createPayment(PaymentDto paymentDto);


    boolean handlePayment(Payment payment, String paymentId, String payerId, PaymentDto paymentDto);
    String createPaypalLink(PaymentDto paymentDto) throws PayPalRESTException;
}
