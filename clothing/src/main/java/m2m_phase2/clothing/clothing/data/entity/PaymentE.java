package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double totalAmount;

    @Column(name = "currency", columnDefinition = "nvarchar(10) default 'USD'")
    private String currency = "USD";

    @Column(name = "method", columnDefinition = "nvarchar(20) default 'Paypal'")
    private String method = "Paypal";

    @Column(name = "intent", columnDefinition = "nvarchar(255) default 'Hot sale deal'")
    private String intent = "Hot sale deal";

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    // Constructors, getters, setters
}
