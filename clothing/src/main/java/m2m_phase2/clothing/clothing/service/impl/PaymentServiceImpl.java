package m2m_phase2.clothing.clothing.service.impl;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.dto.PaymentDTO;
import m2m_phase2.clothing.clothing.data.mapper.PaymentMapper;
import m2m_phase2.clothing.clothing.exception.CustomCause;
import m2m_phase2.clothing.clothing.exception.CustomException;
import m2m_phase2.clothing.clothing.repository.PaymentRepo;
import m2m_phase2.clothing.clothing.service.OrderService;
import m2m_phase2.clothing.clothing.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final OrderService orderService;

    @Override
    public Integer savePayment(PaymentDTO paymentDTO) throws CustomException {
        var existedPayment = paymentRepo.findByPaymentId(paymentDTO.getPaymentId());
        if (existedPayment != null) {
            paymentDTO.setSysPaymentId(existedPayment.getSysPaymentId());
        }
        var payment = PaymentMapper.toEntity(paymentDTO, orderService);
        var result = paymentRepo.save(payment);
        if (result != null) {
            orderService.paidOrder(paymentDTO.getOrderId());
        }
        System.out.println(result);
        return 0;
    }

    @Override
    public Integer cancelPayment(String paymentId) throws CustomException {
        var rowsEffected = paymentRepo.cancelPayment(paymentId);
        if (rowsEffected > 0) {
            return rowsEffected;
        }
        throw new CustomException(CustomCause.BOARD404);
    }
}
