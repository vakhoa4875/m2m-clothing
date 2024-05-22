package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.entity.NotificationE;
import m2m_phase2.clothing.clothing.repository.NotificationRepo;
import m2m_phase2.clothing.clothing.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationImpl implements NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    @Override
    public void insertNotification(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        notificationRepo.insertNotification(voucherDetailsDto.getUser().getId(), voucherDetailsDto.getVoucher().getVoucherName());
    }

    @Override
    public List<NotificationE> finduseridwitchNotification(Integer id) throws SQLException {
        return notificationRepo.finduseridwitchNotification(Long.valueOf(id));
    }

    @Override
    public void deleteNotification(Integer id) throws SQLException {
        notificationRepo.deleteNotification(Long.valueOf(id));
    }
}
