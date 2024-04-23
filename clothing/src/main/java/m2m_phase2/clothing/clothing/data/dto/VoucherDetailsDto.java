package m2m_phase2.clothing.clothing.data.dto;

import lombok.Data;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;

@Data
public class VoucherDetailsDto {
    private int voucherDetailsID;
    private VoucherE voucher;
    private UserE user;
}
