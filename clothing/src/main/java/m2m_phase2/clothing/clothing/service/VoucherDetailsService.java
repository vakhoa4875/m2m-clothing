package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDto;

import java.sql.SQLException;

public interface VoucherDetailsService {
    byte saveVoucherDetails(VoucherDetailsDto voucherDetailsDto) throws SQLException;

    byte deleteVoucherDetails(VoucherDetailsDto voucherDetailsDto) throws SQLException;

    boolean isVoucherDetailsExist(VoucherDetailsDto voucherDetailsDto) throws SQLException;

    byte saveVoucherForUser(VoucherDetailsDto voucherDetailsDto) throws SQLException;
}
