package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2m_phase2.clothing.clothing.data.entity.Sale;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private int saleid;
    private String saleName;
    private int salePersent;
    private Date slateStart;
    private Date saleEnd;

    public static Sale convertSaleDtoToSaleE(SaleDTO saleDTO) {
        return Sale.builder()
                .saleId(saleDTO.getSaleid())
                .saleName(saleDTO.getSaleName())
                .salePercent(saleDTO.getSalePersent())
                .saleStart(saleDTO.getSlateStart())
                .saleEnd(saleDTO.getSaleEnd())
                .build();
    }

}
