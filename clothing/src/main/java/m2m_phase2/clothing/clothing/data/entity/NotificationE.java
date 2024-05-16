package m2m_phase2.clothing.clothing.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationE {
    @Id
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "namenotification")
    private String namenotification;

}
