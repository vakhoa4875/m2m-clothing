package m2m_phase2.clothing.clothing.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Sale", schema = "dbo", catalog = "m2m_clothing")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sale_ID")
    private int saleId;
    @Column(name = "sale_Name")
    private String saleName;
    @Column(name = "sale_Percent")
    private int salePercent;
    @Column(name = "sale_Start")
    private Date saleStart;
    @Column(name = "sale_End")
    private Date saleEnd;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore 
    private List<Product> produtss;

}
