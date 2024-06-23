package m2m_phase2.clothing.clothing.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VoucherDto {
    private int voucherID;
    private String voucherName;
    private int reduce;
    private int quantity;
    private Date startDay;
    private Date endDay;
    private Date toDay;
}
