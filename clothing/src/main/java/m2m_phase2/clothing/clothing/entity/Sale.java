package m2m_phase2.clothing.clothing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Sale", schema = "dbo", catalog = "m2m_clothing")
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

    public List<Product> getProdutss() {
        return produtss;
    }

    public void setProdutss(List<Product> produtss) {
        this.produtss = produtss;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public int getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(int salePercent) {
        this.salePercent = salePercent;
    }

    public Date getSaleStart() {
        return saleStart;
    }

    public void setSaleStart(Date saleStart) {
        this.saleStart = saleStart;
    }

    public Date getSaleEnd() {
        return saleEnd;
    }

    public void setSaleEnd(Date saleEnd) {
        this.saleEnd = saleEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale that = (Sale) o;

        if (saleId != that.saleId) return false;
        if (salePercent != that.salePercent) return false;
        if (saleName != null ? !saleName.equals(that.saleName) : that.saleName != null) return false;
        if (saleStart != null ? !saleStart.equals(that.saleStart) : that.saleStart != null) return false;
        if (saleEnd != null ? !saleEnd.equals(that.saleEnd) : that.saleEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saleId;
        result = 31 * result + (saleName != null ? saleName.hashCode() : 0);
        result = 31 * result + salePercent;
        result = 31 * result + (saleStart != null ? saleStart.hashCode() : 0);
        result = 31 * result + (saleEnd != null ? saleEnd.hashCode() : 0);
        return result;
    }
}
