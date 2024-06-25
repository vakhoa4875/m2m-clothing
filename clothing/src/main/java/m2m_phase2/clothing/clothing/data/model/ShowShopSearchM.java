package m2m_phase2.clothing.clothing.data.model;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.ShopE;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowShopSearchM {
    private Integer shopId;
    private String logo;
    private String shopName;
    private Date dateEstablished;

    public static ShowShopSearchM convertShopEToShowShopSearchM(ShopE shopE) {
        return ShowShopSearchM.builder()
                .shopId(shopE.getShopId())
                .logo(shopE.getLogo())
                .shopName(shopE.getNameShop())
                .dateEstablished(shopE.getDateEstablished())
                .build();
    }

    public static List<ShowShopSearchM> converListShopEToListShowShopSearchM(List<ShopE> listShopE) {
        return listShopE.stream()
                .map(e -> convertShopEToShowShopSearchM(e))
                .collect(Collectors.toList());
    }
}
