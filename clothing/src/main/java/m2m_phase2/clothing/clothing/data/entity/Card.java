package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Card")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name_product")
    private String name_product;

    @Column(name = "quatity")
    private int quatity;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserE user;
}
