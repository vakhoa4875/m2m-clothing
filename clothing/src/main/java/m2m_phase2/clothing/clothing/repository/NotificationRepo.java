package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.NotificationE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<NotificationE, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Notification (user_id, namenotification) VALUES (:userId, :nameNotification)", nativeQuery = true)
    void insertNotification(@Param("userId") Long userId, @Param("nameNotification") String nameNotification);

    @Modifying
    @Query(value = "select * from Notification where user_id = :user_id", nativeQuery = true)
    List<NotificationE> finduseridwitchNotification(@Param("user_id") Long user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Notification WHERE user_id = :user_id", nativeQuery = true)
    void deleteNotification(@Param("user_id") Long user_id);
}
