package m2m_phase2.clothing.clothing.data.dto;
import lombok.Data;

@Data
public class PaymentDto {
    private String paymentId;
    private String payerId;
    private String totalAmount;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private Integer orderId;
}
