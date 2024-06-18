package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.*;
import lombok.*;
import m2m_phase2.clothing.clothing.data.dto.FavoriteDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Favorite")
@Entity
public class FavoriteE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserE user;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

}
