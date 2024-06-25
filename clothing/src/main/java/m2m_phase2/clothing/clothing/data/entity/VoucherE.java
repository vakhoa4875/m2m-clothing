package m2m_phase2.clothing.clothing.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "Voucher")
@Entity
@Getter
@Setter
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

    @Column(name = "quantity")
    private int quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_day")
    private Date startDay;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_day")
    private Date endDay;

    @OneToMany(mappedBy = "voucher",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<VoucherDetailsE> voucherDetailsES;

}
