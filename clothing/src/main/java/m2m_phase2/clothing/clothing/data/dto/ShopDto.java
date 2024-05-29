package m2m_phase2.clothing.clothing.data.dto;


import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.UserE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    private Integer shopId;

    private String logo;

    private String nameShop;

    private String dateEstablished;

    private UserE userE;

}
