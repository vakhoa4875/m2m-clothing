package m2m_phase2.clothing.clothing.data.model;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.FavoriteE;
import m2m_phase2.clothing.clothing.data.entity.Product;
import m2m_phase2.clothing.clothing.data.entity.UserE;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteM {
    private Integer id;
    private UserE user;
    private Product product;

    public static FavoriteM convertFavoriteEToFavoriteM(FavoriteE favoriteE) {
        return FavoriteM.builder()
                .id(favoriteE.getId())
                .user(favoriteE.getUser())
                .product(favoriteE.getProduct())
                .build();
    }

    public static List<FavoriteM> converListFavoriteEToListFavoriteM(List<FavoriteE> favoriteES) {
        return favoriteES.stream()
                .map(e -> convertFavoriteEToFavoriteM(e))
                .collect(Collectors.toList());
    }
}
