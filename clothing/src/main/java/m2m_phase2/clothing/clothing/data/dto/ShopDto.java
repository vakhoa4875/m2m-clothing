package m2m_phase2.clothing.clothing.data.dto;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.UserE;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    private Integer shopId;

    private String logo;

    private String nameShop;

    private Date dateEstablished;

    private UserE userE;
}
