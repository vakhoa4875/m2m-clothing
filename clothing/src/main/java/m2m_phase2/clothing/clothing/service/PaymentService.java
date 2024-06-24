package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.PaymentDTO;
import m2m_phase2.clothing.clothing.exception.CustomException;

public interface PaymentService {
    Integer savePayment(PaymentDTO paymentDTO) throws CustomException;
    Integer cancelPayment(String paymentId) throws CustomException;
}
