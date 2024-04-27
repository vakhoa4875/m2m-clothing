
package m2m_phase2.clothing.clothing.data.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "[Order]")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private UserE customer;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "delivery_address", length = 255)
    private String deliveryAddress;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "order_status", length = 50)
    private String orderStatus;
}