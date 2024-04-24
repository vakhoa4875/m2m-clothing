package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.repository.VoucherDetailsRepo;
import m2m_phase2.clothing.clothing.repository.VoucherRepo;
import m2m_phase2.clothing.clothing.service.VoucherDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class VoucherDetailsServiceImpl implements VoucherDetailsService {
    @Autowired
    private VoucherDetailsRepo voucherDetailsRepo;
    @Override
    public byte saveVoucherDetails(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        voucherDetailsRepo.insertVoucherDetails(voucherDetailsDto.getVoucher().getVoucherID(),voucherDetailsDto.getUser().getId());
        return 1;
    }
}
