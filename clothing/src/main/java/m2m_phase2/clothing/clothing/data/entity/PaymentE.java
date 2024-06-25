package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    @Column(name = "payer_id")
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
    private LocalDateTime dateCreated;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
