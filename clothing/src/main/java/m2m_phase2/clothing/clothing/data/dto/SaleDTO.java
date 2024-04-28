package m2m_phase2.clothing.clothing.data.dto;

import lombok.Builder;
import lombok.Data;
import m2m_phase2.clothing.clothing.data.entity.Sale;

import java.sql.Date;

@Data

public class SaleDTO {
    private String saleName;
    private int salePersent;
    private Date slateStart;
    private Date saleEnd;

    public static Sale convertSaleDtoToSaleE(SaleDTO saleDTO){
        return Sale.builder()
                .saleName(saleDTO.getSaleName())
                .salePercent(saleDTO.getSalePersent())
                .saleStart(saleDTO.getSlateStart())
                .saleEnd(saleDTO.getSaleEnd())
                .build();
    }
}
