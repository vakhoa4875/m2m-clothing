package m2m_phase2.clothing.clothing.data.model;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.entity.VoucherDetailsE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherDetailsM {
    private int voucherDetailsID;
    private VoucherE voucher;
    private UserE user;

    public static VoucherDetailsM convertVoucherDetailsEToVoucherDetailsM(VoucherDetailsE voucherDetailsE){
        return VoucherDetailsM.builder()
                .voucherDetailsID(voucherDetailsE.getVoucherDetailsID())
                .voucher(voucherDetailsE.getVoucher())
                .user(voucherDetailsE.getUser())
                .build();
    }

    public static List<VoucherDetailsM> converListVoucherDetailsEToListVoucherDetailsM(List<VoucherDetailsE> voucherDetailsES) {
        return  voucherDetailsES.stream()
                .map(e -> convertVoucherDetailsEToVoucherDetailsM(e))
                .collect(Collectors.toList());
    }
}
