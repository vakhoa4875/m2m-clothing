package m2m_phase2.clothing.clothing.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaypalService {

    private final APIContext apiContext;

    public Payment createPayment(
            PaymentDto paymentDto,
            String cancelUrl,
            String successUrl
    ) throws PayPalRESTException {
        Amount amount = new Amount();
        System.out.println(">>currency: " + paymentDto.getCurrency());
        amount.setCurrency(paymentDto.getCurrency());
        amount.setTotal(String.format(Locale.forLanguageTag(paymentDto.getCurrency()), "%.2f", Double.valueOf(paymentDto.getTotalAmount())));

        Transaction transaction = new Transaction();
        transaction.setDescription(paymentDto.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(paymentDto.getMethod());

        Payment payment = new Payment();
        payment.setIntent(paymentDto.getIntent());
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(
            String paymentId,
            String payerId
    ) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }
}
