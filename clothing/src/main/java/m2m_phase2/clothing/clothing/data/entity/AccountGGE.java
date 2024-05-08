package m2m_phase2.clothing.clothing.data.entity;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "AccountGG")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountGGE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id_gg")
    private int userIdGG;

    @Column(name = "access_token_gg", nullable = false, length = 225)
    private String accessTokenGG;

    @Column(name = "sub_gg", nullable = false, unique = true, length = 225)
    private String subGG;

    @Column(name = "username_gg", nullable = false, unique = true, length = 63)
    private String usernameGG;

    @Column(name = "email_gg", nullable = false, unique = true, length = 255)
    private String emailGG;

    @Column(name = "is_disable_gg")
    private boolean isDisableGG = false;

    @Column(name = "is_admin_gg")
    private boolean isAdminGG = false;

//    @OneToOne(mappedBy = "accountgge",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private UserE userE;
}

