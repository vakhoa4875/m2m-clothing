package m2m_phase2.clothing.clothing.data.model;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import m2m_phase2.clothing.clothing.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherM {
    private int voucherID;
    private String voucherName;
    private int reduce;
    private Date startDay;
    private Date endDay;

    public static VoucherM convertVoucherEToVoucherM(VoucherE voucherE){
        return VoucherM.builder()
                .voucherID(voucherE.getVoucherID())
                .voucherName(voucherE.getVoucherName())
                .reduce(voucherE.getReduce())
                .startDay(voucherE.getStartDay())
                .endDay(voucherE.getEndDay())
                .build();
    }

    public static List<VoucherM> converListVoucherEToListVoucherM(List<VoucherE> listVoucherE) {
        return  listVoucherE.stream()
                .map(e -> convertVoucherEToVoucherM(e))
                .collect(Collectors.toList());
    }
}
