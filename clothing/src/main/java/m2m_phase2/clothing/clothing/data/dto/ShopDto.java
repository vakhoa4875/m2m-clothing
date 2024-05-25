package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.UserE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    private Integer shopId;

    private String logo;

    private String nameShop;

    private String dateEstablished;

    private UserE userE;
}
