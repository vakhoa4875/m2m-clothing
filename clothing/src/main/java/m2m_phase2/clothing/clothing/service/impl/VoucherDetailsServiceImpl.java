package m2m_phase2.clothing.clothing.service.impl;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.entity.VoucherDetailsE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import m2m_phase2.clothing.clothing.repository.UserRepo;
import m2m_phase2.clothing.clothing.repository.VoucherDetailsRepo;
import m2m_phase2.clothing.clothing.repository.VoucherRepo;
import m2m_phase2.clothing.clothing.service.VoucherDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;

@Service
public class VoucherDetailsServiceImpl implements VoucherDetailsService {
    @Autowired
    private VoucherDetailsRepo voucherDetailsRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private HttpSession session;

    @Override
    public byte saveVoucherDetails(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        voucherDetailsRepo.insertVoucherDetails(voucherDetailsDto.getVoucher().getVoucherID(),voucherDetailsDto.getUser().getId());
        return 1;
    }

    @Override
    public byte deleteVoucherDetails(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        voucherDetailsRepo.deleteVoucherDetails(voucherDetailsDto.getVoucher().getVoucherID(),voucherDetailsDto.getUser().getId());
        return 1;
    }

    @Override
    public boolean isVoucherDetailsExist(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        var voucherDetails = voucherDetailsRepo.getVoucherDetailsByVoucherIDAndUserID(voucherDetailsDto.getVoucher().getVoucherID(),voucherDetailsDto.getUser().getId());
        return Objects.nonNull(voucherDetails);
    }

    @Override
    public byte saveVoucherForUser(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        // Lấy thông tin người dùng đang đăng nhập từ session
        String loggedInUserEmail = (String) session.getAttribute("loggedInUser");

        if (loggedInUserEmail == null) {
            // Người dùng chưa đăng nhập, không thể lưu voucher
            return -1;
        }

        // Kiểm tra xem người dùng đã nhận voucher này chưa
        UserE user = userRepo.findByEmail(loggedInUserEmail);
        if (user == null) {
            // Người dùng không tồn tại trong cơ sở dữ liệu
            return -1;
        }

        // Kiểm tra xem người dùng đã nhận voucher chưa
        VoucherDetailsE existingVoucher = voucherDetailsRepo.getVoucherDetailsByVoucherIDAndUserID(voucherDetailsDto.getVoucher().getVoucherID(), user.getId());
        if (existingVoucher != null) {
            // Người dùng đã nhận voucher, không thể nhận lại
            return 0;
        }

        // Lưu voucher cho người dùng
        voucherDetailsRepo.insertVoucherDetails(voucherDetailsDto.getVoucher().getVoucherID(), user.getId());
        return 1;
    }

}
