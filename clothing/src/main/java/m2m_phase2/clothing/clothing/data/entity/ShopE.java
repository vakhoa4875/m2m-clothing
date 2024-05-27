package m2m_phase2.clothing.clothing.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @JoinColumn(name = "id", referencedColumnName = "id", unique = true)
    private UserE userE;

    @OneToMany(mappedBy = "shopE", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<Product> products;
}
