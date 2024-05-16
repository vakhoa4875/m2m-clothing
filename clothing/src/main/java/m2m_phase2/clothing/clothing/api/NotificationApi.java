package m2m_phase2.clothing.clothing.api;

import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Slf4j
public class NotificationApi {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/finduseridwitchNotification")
    public ResponseEntity<?> finduseridwitchNotification(@RequestParam Integer id ) throws SQLException {
        return ResponseEntity.ok(notificationService.finduseridwitchNotification(id));
    }

    @GetMapping("/deleteNotification")
    public ResponseEntity<?> deleteNotification(@RequestParam Integer id )throws SQLException{
        notificationService.deleteNotification(id);
        return ResponseEntity.ok("xóa thành công");
    }
}
