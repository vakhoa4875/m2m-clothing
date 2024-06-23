package m2m_phase2.clothing.clothing.data.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountGGM {
    private int userIdGG;
    private String accessTokenGG;
    private String subGG;
    private String usernameGG;
    private String emailGG;
    private boolean isDisableGG = false;
    private boolean isAdminGG = false;
}
