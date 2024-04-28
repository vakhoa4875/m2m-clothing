
package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "orderDetail", fetch = FetchType.LAZY)
    List<OrderDetailE> listOrder;
}