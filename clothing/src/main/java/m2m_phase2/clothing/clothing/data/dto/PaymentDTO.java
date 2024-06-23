package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private int sysPaymentId;
    private String paymentId;
    private String payerId;
    private Float totalAmount;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String paymentStatus;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    private int orderId;

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "sysPaymentId=" + sysPaymentId +
                ", paymentId='" + paymentId + '\'' +
                ", payerId='" + payerId + '\'' +
                ", totalAmount=" + totalAmount +
                ", currency='" + currency + '\'' +
                ", method='" + method + '\'' +
                ", intent='" + intent + '\'' +
                ", description='" + description + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", orderId=" + orderId +
                '}';
    }
}
