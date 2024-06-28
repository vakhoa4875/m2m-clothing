package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Slf4j
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationApi {
    private final NotificationService notificationService;

    @GetMapping("/findByUserID")
    public ResponseEntity<?> doGetByUserID(@RequestParam Integer id ) throws SQLException {
        return ResponseEntity.ok(notificationService.finduseridwitchNotification(id));
    }

    @GetMapping("/deleteOne")
    public ResponseEntity<?> deleteNotification(@RequestParam Integer id )throws SQLException{
        notificationService.deleteNotification(id);
        return ResponseEntity.ok("Removed notification successfully");
    }
}
