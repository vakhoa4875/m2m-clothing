package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "[Order]")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private UserE customer;

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate = new Date();

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

    @Column(name = "count_sp")
    private Integer countSp;

    @Column(name = "order_code", unique = true, length = 127)
    private String orderCode;

    @ManyToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "voucher_id")
    private VoucherE voucher;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = OrderDetailE.class)
    private List<OrderDetailE> orderDetails;
}
