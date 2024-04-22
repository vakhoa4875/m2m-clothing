package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.VoucherDto;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import m2m_phase2.clothing.clothing.data.model.VoucherM;
import m2m_phase2.clothing.clothing.repository.VoucherRepo;
import m2m_phase2.clothing.clothing.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
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
        voucherRepo.insertVoucher(voucherDto.getVoucherName(),voucherDto.getReduce(),voucherDto.getStartDay(),voucherDto.getEndDay(),3);
        return 1;
    }

    @Override
    public byte updateVoucher(VoucherDto voucherDto) throws SQLException {
        voucherRepo.updateVoucher(voucherDto.getVoucherName(),voucherDto.getReduce(),voucherDto.getStartDay(),voucherDto.getEndDay(),voucherDto.getVoucherID());
        return 1;
    }
}
