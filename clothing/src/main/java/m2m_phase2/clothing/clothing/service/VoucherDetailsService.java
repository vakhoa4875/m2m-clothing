package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDto;

import java.sql.SQLException;

public interface VoucherDetailsService {
    byte saveVoucherDetails(VoucherDetailsDto voucherDetailsDto) throws SQLException;
}
