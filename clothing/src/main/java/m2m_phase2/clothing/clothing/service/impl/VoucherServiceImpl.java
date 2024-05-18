package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.VoucherDto;
import m2m_phase2.clothing.clothing.data.model.VoucherM;
import m2m_phase2.clothing.clothing.repository.VoucherRepo;
import m2m_phase2.clothing.clothing.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Configuration
@EnableScheduling
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepo voucherRepo;

    @Override
    public List<VoucherM> findAll() {
        return VoucherM.converListVoucherEToListVoucherM(voucherRepo.findAll());
    }

    @Override
    public VoucherM findVoucherByID(VoucherDto voucherDto) {
        return VoucherM.convertVoucherEToVoucherM(voucherRepo.findVoucherByID(voucherDto.getVoucherID()));
    }

    @Override
    public byte saveVoucher(VoucherDto voucherDto) throws SQLException {
        voucherRepo.insertVoucher(voucherDto.getVoucherName(), voucherDto.getReduce(), voucherDto.getQuantity(), voucherDto.getStartDay(), voucherDto.getEndDay());
        return 1;
    }

    @Override
    public byte updateVoucher(VoucherDto voucherDto) throws SQLException {
        voucherRepo.updateVoucher(voucherDto.getVoucherName(), voucherDto.getReduce(), voucherDto.getQuantity(), voucherDto.getStartDay(), voucherDto.getEndDay(), voucherDto.getVoucherID());
        return 1;
    }

    @Override
    public List<VoucherM> findVouchersInfoByEmail(String email) throws SQLException {
        return VoucherM.converListVoucherEToListVoucherM(voucherRepo.findVouchersInfoByEmail(email));
    }

    @Override
    public byte deleteVoucherByDay(VoucherDto voucherDto) throws SQLException {
        voucherRepo.deleteVoucherByDay(voucherDto.getToDay());
        return 1;
    }

    @Override
    public VoucherM findByVoucherID(Integer voucherId) throws SQLException {
        var voucher = voucherRepo.findByVoucherID(voucherId);
        return voucher == null ? null : VoucherM.convertVoucherEToVoucherM(voucher);
    }

//    @Scheduled(cron = "0 0 0 * * *")
//    public void useDeleteVoucherByDay() throws SQLException, ParseException {
//        VoucherDto voucherDto = new VoucherDto();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date today = new Date();
//        String formattedDate = sdf.format(today);
//        Date toDay = sdf.parse(formattedDate);
//        voucherDto.setToDay(toDay);
//        byte rowEffected = deleteVoucherByDay(voucherDto);
//        if(rowEffected != 1) {
//            System.out.println("Voucher deletion failed");
//        }else{
//            System.out.println("Voucher deleted");
//        }
//    }
}
