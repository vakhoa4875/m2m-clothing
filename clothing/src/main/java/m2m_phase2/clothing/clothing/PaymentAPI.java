package m2m_phase2.clothing.clothing;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import m2m_phase2.clothing.clothing.data.dto.PaymentDTO;
import m2m_phase2.clothing.clothing.data.mgt.ResponseObject;
import m2m_phase2.clothing.clothing.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-user/payment")
@RequiredArgsConstructor
public class PaymentAPI {
    private final PaymentService paymentService;

    @SneakyThrows
    @PostMapping("/save")
    public ResponseObject<?> doPostSavePayment(@RequestBody PaymentDTO paymentDTO) {
        var response = new ResponseObject<>();
        var data = paymentService.savePayment(paymentDTO);
        response.setData(data);
        response.setMessage("Successfully saved the payment");
        response.setStatus("Success");
        return response;
    }
}
