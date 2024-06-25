package m2m_phase2.clothing.clothing.data.dto;

import lombok.Getter;
import lombok.Setter;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;

@Getter
@Setter
public class VoucherDetailsDto {
    private int voucherDetailsID;
    private VoucherE voucher;
    private UserE user;
}
