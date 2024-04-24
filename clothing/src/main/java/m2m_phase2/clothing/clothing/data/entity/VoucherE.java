package m2m_phase2.clothing.clothing.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "Voucher")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    private int voucherID;

    @Column(name = "voucher_name")
    private String voucherName;

    @Column(name = "reduce")
    private int reduce;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_day")
    private Date startDay;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_day")
    private Date endDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserE user;
}
