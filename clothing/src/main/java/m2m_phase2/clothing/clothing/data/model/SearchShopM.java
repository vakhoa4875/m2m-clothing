package m2m_phase2.clothing.clothing.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.ShopE;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchShopM {
    private Integer shopId;
    private String nameShop;
    private String logo;

    public static SearchShopM convertShopEToSearchShopM(ShopE shopE){
        return SearchShopM.builder()
                .shopId(shopE.getShopId())
                .nameShop(shopE.getNameShop())
                .logo(shopE.getLogo())
                .build();
    }

    public static List<SearchShopM> converListShopEToSearchShopM(List<ShopE> listShopE) {
        return  listShopE.stream()
                .map(e -> convertShopEToSearchShopM(e))
                .collect(Collectors.toList());
    }
}

