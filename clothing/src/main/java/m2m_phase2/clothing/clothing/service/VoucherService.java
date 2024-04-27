package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDto;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import m2m_phase2.clothing.clothing.data.model.VoucherM;

import java.sql.SQLException;
import java.util.List;

public interface VoucherService {
    List<VoucherM> findAll() throws SQLException;

    VoucherM findVoucherByID(VoucherDto voucherDto) throws SQLException;

    byte saveVoucher(VoucherDto voucherDto) throws SQLException;

    byte updateVoucher(VoucherDto voucherDto) throws SQLException;

    List<VoucherM> findVouchersInfoByEmail(String email) throws SQLException;
}
