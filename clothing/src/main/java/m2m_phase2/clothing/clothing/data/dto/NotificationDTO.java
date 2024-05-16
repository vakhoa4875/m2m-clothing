package m2m_phase2.clothing.clothing.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.NotificationE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {
    private Integer user_id;
    private String namenotification;

    public static NotificationE convertNotificationDTOToNotificationE(NotificationDTO notificationDTO){
        return NotificationE.builder()
                .user_id(Math.toIntExact(notificationDTO.getUser_id()))
                .namenotification(notificationDTO.getNamenotification())
                .build();
    }
}
