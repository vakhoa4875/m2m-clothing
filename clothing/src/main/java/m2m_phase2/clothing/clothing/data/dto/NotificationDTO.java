package m2m_phase2.clothing.clothing.data.dto;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.NotificationE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {
    private Integer user_id;
    private String namenotification;

    public static NotificationE convertNotificationDTOToNotificationE(NotificationDTO notificationDTO) {
        return NotificationE.builder()
                .user_id(Math.toIntExact(notificationDTO.getUser_id()))
                .namenotification(notificationDTO.getNamenotification())
                .build();
    }
}
