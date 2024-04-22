package m2m_phase2.clothing.clothing.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.Card;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.utils.DateUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardM {
    private int id;
    private String name_product;
    private int quatity;
    private int price;


    public static CardM convertCardEToCardM(Card cardE) {
        return CardM.builder()
                .id(cardE.getId())
                .name_product(cardE.getName_product())
                .quatity(cardE.getQuatity())
                .price(cardE.getPrice())
                .build();
    }

    public static List<CardM> converListCardEToListCardM(List<Card> listCardE) {
        return  listCardE.stream()
                .map(e -> convertCardEToCardM(e))
                .collect(Collectors.toList());
    }
}
