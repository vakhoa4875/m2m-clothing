package m2m_phase2.clothing.clothing.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.NotificationE;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationM {

    private Integer user_id;
    private String namenotification;

    public static NotificationM convertNotificationEToNotificationM(NotificationE notificationE) {
        return NotificationM.builder()
                .user_id(Math.toIntExact(notificationE.getUser_id()))
                .namenotification(notificationE.getNamenotification())
                .build();
    }

    public static List<NotificationM> converListNotificationEToListNotificationM(List<NotificationE> notificationE) {
        return  notificationE.stream()
                .map(e -> convertNotificationEToNotificationM(e))
                .collect(Collectors.toList());
    }
}
