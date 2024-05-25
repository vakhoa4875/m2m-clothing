package m2m_phase2.clothing.clothing.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.ShopE;
import m2m_phase2.clothing.clothing.data.entity.UserE;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopM {
    private Integer shopId;

    private String logo;

    private String nameShop;

    private String dateEstablished;

    private UserE userE;
    public static ShopM convertShopEToShopM(ShopE ShopE){
        return ShopM.builder()
                .shopId(ShopE.getShopId())
                .logo(ShopE.getLogo())
                .nameShop(ShopE.getNameShop())
                .dateEstablished(ShopE.getDateEstablished())
                .userE(ShopE.getUserE())
                .build();
    }

    public static List<ShopM> converListShopEToListShopM(List<ShopE> listShopE) {
        return  listShopE.stream()
                .map(e -> convertShopEToShopM(e))
                .collect(Collectors.toList());
    }}
