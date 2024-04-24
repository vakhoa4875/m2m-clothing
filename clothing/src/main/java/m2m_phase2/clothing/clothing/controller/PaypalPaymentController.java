package m2m_phase2.clothing.clothing.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.constant.OrderStatus;
import m2m_phase2.clothing.clothing.data.dto.PaymentDto;
import m2m_phase2.clothing.clothing.paypal.PaypalService;
import m2m_phase2.clothing.clothing.service.OrderService;
import m2m_phase2.clothing.clothing.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            this.paymentDto.setPaymentId(paymentId);
            this.paymentDto.setPayerId(payerId);
            System.out.println(">>>" + payment.getState());
            if (payment.getState()
                    .equalsIgnoreCase(OrderStatus.APPROVED.getValue())) {
                this.paymentDto.setPaymentStatus(OrderStatus.PAID.getValue());
                paymentService.createPayment(this.paymentDto);
                orderService.paidOrder(this.paymentDto.getOrderId());
                return "redirect:/payment/paypal/succeed";
            }
        } catch (PayPalRESTException | NullPointerException e) {
            log.error("Error occurred: ", e);
            return "redirect:/payment/paypal/error";
        }
        return "redirect:/payment/paypal/error";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
//        model.addAttribute("paymentDto", this.paymentDto);
        return "swappa/assests/html/paypal/paymentCancel";
    }

    @GetMapping("/succeed")
    public String paymentSucceed(Model model) {
        model.addAttribute("paymentDto", this.paymentDto);
        return "swappa/assests/html/paypal/paymentSuccess";
    }

    @GetMapping("/error")
    public String paymentError() {
//        System.out.println(paymentDto.getPaymentId());
//        model.addAttribute("paymentDto", this.paymentDto);
        return "swappa/assests/html/paypal/paymentError";
    }

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(
            @RequestBody PaymentDto paymentDto
    ) {
        Map<String, Object> map = new HashMap<>();
        var order = orderService.findOrderByOrderId(paymentDto.getOrderId());
        if (!order.getOrderStatus()
                .equals(OrderStatus.NEED_PAYMENT.getValue())) {
            map.put("href", "http://localhost:8083/payment/paypal/error");
            return ResponseEntity.ok(map);
        }
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
                    map.put("href", links.getHref());
                    this.paymentDto = paymentDto;
                    System.out.println(links.getHref());
                    return ResponseEntity.ok(map);
                }
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred: ", e);
        }
        return ResponseEntity.ok("Failed tf out");
    }

}
