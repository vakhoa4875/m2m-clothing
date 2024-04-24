package m2m_phase2.clothing.clothing.service.impl;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.dto.PaymentDto;
import m2m_phase2.clothing.clothing.repository.PaymentRepo;
import m2m_phase2.clothing.clothing.service.OrderService;
import m2m_phase2.clothing.clothing.service.PaymentService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final OrderService orderService;

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
}
