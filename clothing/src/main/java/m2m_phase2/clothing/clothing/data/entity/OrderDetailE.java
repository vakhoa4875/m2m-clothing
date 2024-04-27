package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "OrderDetail")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailE {
    @Id
    @Column(name = "order_id_detail")
    private int order_id_detail;

    @Column(name = "nameproduct")
    private String nameproduct;

    @Column(name = "quatity")
    private int quatity;

    @Column(name = "toal_product")
    private Float toal_product;
}
