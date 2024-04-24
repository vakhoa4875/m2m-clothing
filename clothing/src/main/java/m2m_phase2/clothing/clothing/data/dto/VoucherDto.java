package m2m_phase2.clothing.clothing.data.dto;

import lombok.Data;
import m2m_phase2.clothing.clothing.data.entity.UserE;

import java.util.Date;
@Data
public class VoucherDto {
    private int voucherID;
    private String voucherName;
    private int reduce;
    private Date startDay;
    private Date endDay;
}
