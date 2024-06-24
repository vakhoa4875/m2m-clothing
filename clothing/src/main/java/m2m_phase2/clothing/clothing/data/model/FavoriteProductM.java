package m2m_phase2.clothing.clothing.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteProductM {
    private Integer id;
    private Integer productId;
    private String productName;
    private String pictures;
    private String slugUrl;
}
