package m2m_phase2.clothing.clothing.controller;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.PaymentDto;
import m2m_phase2.clothing.clothing.paypal.PaypalService;
import m2m_phase2.clothing.clothing.service.OrderService;
import m2m_phase2.clothing.clothing.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment/paypal")
public class PaypalPaymentController {

    private final PaypalService paypalService;
    private final PaymentService paymentService;
    private final OrderService orderService;
    private PaymentDto paymentDto = null;

    @GetMapping("/executing")
    public String executePayment(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            var succeed = paymentService.handlePayment(payment, paymentId, payerId, this.paymentDto);
            if (succeed)
                return "redirect:/payment/paypal/succeed";
        } catch (PayPalRESTException | NullPointerException e) {
            log.error("Error occurred: ", e);
            return "redirect:/payment/paypal/error";
        }
        return "redirect:/payment/paypal/error";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "swappa/assests/html/paypal/paymentCancel";
    }

    @GetMapping("/succeed")
    public String paymentSucceed(Model model) {
        model.addAttribute("paymentDto", this.paymentDto);
        this.paymentDto = null;
        return "swappa/assests/html/paypal/paymentSuccess";
    }

    @GetMapping("/error")
    public String paymentError() {
        return "swappa/assests/html/paypal/paymentError";
    }

    @GetMapping("/test")
    public String paymentTest() {
        return "swappa/assests/html/paypal/testPaypal";
    }

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(
            @RequestBody PaymentDto paymentDto
    ) {
        String url = "http://localhost:8083/payment/paypal/error";
        try {
            url = paymentService.createPaypalLink(paymentDto);
            if (!url.contains("error")) {
                this.paymentDto = paymentDto;
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred: ", e);
        }
        return ResponseEntity.ok(url);
    }
}
