package m2m_phase2.clothing.clothing.data.model;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.PaymentE;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentM {
    private int sysPaymentId;
    private String paymentId;
    private String payerId;
    private Float totalAmount;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String paymentStatus;
    private Date dateCreated;
    private Date dateUpdated;
    private Long orderId;

    public static PaymentM convertPaymentEToPaymentM(PaymentE paymentE) {
        return PaymentM.builder()
                .sysPaymentId(paymentE.getSysPaymentId())
                .paymentId(paymentE.getPaymentId())
                .payerId(paymentE.getPayerId())
                .totalAmount(paymentE.getTotalAmount())
                .currency(paymentE.getCurrency())
                .method(paymentE.getMethod())
                .intent(paymentE.getIntent())
                .description(paymentE.getDescription())
                .paymentStatus(paymentE.getPaymentStatus())
                .dateCreated(paymentE.getDateCreated())
                .dateUpdated(paymentE.getDateUpdated())
                .orderId(paymentE.getOrder().getOrderId())
                .build();
    }

    public static List<PaymentM> converListPaymentEToListPaymentM(List<PaymentE> listPaymentE) {
        return  listPaymentE.stream()
                .map(e -> convertPaymentEToPaymentM(e))
                .collect(Collectors.toList());
    }
}
