package m2m_phase2.clothing.clothing.data.entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.utils.DateUtils;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Table(name = "[user]")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 63)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 63)
    private String email;

    @Column(name = "gg_token", length = 255)
    private String ggToken;

    @Column(name = "hashed_pass", nullable = false, length = 255)
    private String hashedPassword;

    @Column(name = "is_admin")
    private boolean isAdmin = false;

    @Column(name = "is_disable")
    private boolean isDisable = false;

    @Column(name = "fullname", length = 63)
    private String fullname = "Your full name";

    @Column(name = "gender", length = 10)
    private String gender = "Male";

    @Column(name = "avatar", length = 63)
    private String avatar = "user.jpg";

    @Column(name = "dob")
    private Date dob = new Date();

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "job_title", length = 63)
    private String jobTitle;

    @Column(name = "role_id", nullable = false)
    private int roleId = 3;

    @Column(name = "role_name", nullable = false, length = 63)
    private String roleName = "User";

    @Column(name = "processed")
    private boolean processed = false;

    @Column(name = "sdt" , length = 15)
    private String sdt;
}
