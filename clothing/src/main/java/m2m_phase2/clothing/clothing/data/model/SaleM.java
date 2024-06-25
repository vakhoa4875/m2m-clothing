package m2m_phase2.clothing.clothing.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import m2m_phase2.clothing.clothing.data.entity.Sale;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class SaleM {
    private int saleId;
    private String saleName;
    private int salePercent;
    private Date slateStart;
    private Date saleEnd;

    public static SaleM convertSaleEToSaleM(Sale saleE) {
        return SaleM.builder()
                .saleId(saleE.getSaleId())
                .saleName(saleE.getSaleName())
                .salePercent(saleE.getSalePercent())
                .slateStart(saleE.getSaleStart())
                .saleEnd(saleE.getSaleEnd())
                .build();
    }

    public static List<SaleM> convertListSaleEToListSaleM(List<Sale> listSaleE) {
        return listSaleE.stream()
                .map(e -> convertSaleEToSaleM(e))
                .collect(Collectors.toList());
    }
}
