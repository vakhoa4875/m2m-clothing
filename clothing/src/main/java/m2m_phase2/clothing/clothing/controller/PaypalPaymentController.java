package m2m_phase2.clothing.clothing.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.data.dto.PaymentDto;
import m2m_phase2.clothing.clothing.paypal.PaypalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment/paypal")
public class PaypalPaymentController {

    private final PaypalService paypalService;
    @GetMapping("/executing")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(">>>" + payment.getState());
            if (payment.getState().equals("approved")) {
                return "redirect:/payment/paypal/succeed";
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred: ", e);
        }
        return "redirect:/payment/paypal/error";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "swappa/assests/html/paypal/paymentCancel";
    }

    @GetMapping("/succeed")
    public String paymentSucceed() {
        return "swappa/assests/html/paypal/paymentSuccess";
    }

    @GetMapping("/error")
    public String paymentError() {
        return "swappa/assests/html/paypal/paymentError";
    }

    @PostMapping("/create-payment")
    public ResponseEntity<String> createPayment(
            @RequestBody PaymentDto paymentDto
    ) {
        try {
            String cancelUrl = "http://localhost:8083/payment/paypal/cancel";
            String successUrl = "http://localhost:8083/payment/paypal/executing";
            Payment payment = paypalService.createPayment(
                    paymentDto,
                    cancelUrl,
                    successUrl
            );
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    System.out.println(links.getHref());
                    return ResponseEntity.ok(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred: ", e);
        }
        return ResponseEntity.ok("Failed tf out");
    }

}
