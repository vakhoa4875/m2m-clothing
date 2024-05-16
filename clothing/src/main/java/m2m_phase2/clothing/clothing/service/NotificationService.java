package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.entity.NotificationE;

import java.sql.SQLException;
import java.util.List;

public interface NotificationService {
    void insertNotification(VoucherDetailsDto voucherDetailsDto) throws SQLException;

    List<NotificationE> finduseridwitchNotification(Integer id) throws SQLException;

    void deleteNotification(Integer id) throws SQLException;
}
