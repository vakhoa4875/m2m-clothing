package m2m_phase2.clothing.clothing.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Shop")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopId;

    @Column(name = "logo")
    private String logo;

    @Column(name = "name_shop")
    private String nameShop;

    @Column(name = "date_established")
    @Temporal(TemporalType.DATE)
    private String dateEstablished;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id",referencedColumnName = "id", unique = true)
    private UserE userE;
}
