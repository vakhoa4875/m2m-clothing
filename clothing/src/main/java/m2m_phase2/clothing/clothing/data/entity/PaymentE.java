package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Payment")
public class PaymentE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sys_payment_id")
    private int sysPaymentId;

    @Column(name = "payment_id", unique = true)
    private String paymentId;

    @Column(name = "payer_id", nullable = false)
    private String payerId;

    @Column(name = "total_amount", nullable = false)
    private Float totalAmount;

    @Column(name = "currency", columnDefinition = "nvarchar(10) default 'USD'")
    private String currency = "USD";

    @Column(name = "method", columnDefinition = "nvarchar(20) default 'Paypal'")
    private String method = "Paypal";

    @Column(name = "intent", columnDefinition = "nvarchar(255) default 'Hot sale deal'")
    private String intent = "Hot sale deal";

    @Column(name = "description")
    private String description;

    @Column(name = "payment_status", nullable = false, length = 50, columnDefinition = "nvarchar(50) default 'Processing'")
    private String paymentStatus;

    @Column(name = "date_created", columnDefinition = "datetime default getdate()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PaymentE {")
                .append("sysPaymentId=").append(sysPaymentId)
                .append(", paymentId='").append(paymentId).append('\'')
                .append(", payerId='").append(payerId).append('\'')
                .append(", totalAmount=").append(totalAmount)
                .append(", currency='").append(currency).append('\'')
                .append(", method='").append(method).append('\'')
                .append(", intent='").append(intent).append('\'')
                .append(", description='").append(description).append('\'')
                .append(", paymentStatus='").append(paymentStatus).append('\'')
//                .append(", dateCreated=").append(dateFormat.format(dateCreated))
//                .append(", dateUpdated=").append(dateFormat.format(dateUpdated))
                .append(", orderId=").append(order.getOrderId())
                .append('}');
        return stringBuilder.toString();
    }
}
