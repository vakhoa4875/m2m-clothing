package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.PaymentDto;

public interface PaymentService {
    byte createPayment(PaymentDto paymentDto);
}
