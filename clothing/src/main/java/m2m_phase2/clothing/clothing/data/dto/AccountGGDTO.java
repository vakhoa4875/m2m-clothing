package m2m_phase2.clothing.clothing.data.dto;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.AccountGGE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountGGDTO {
    //    private int userIdGG;
    private String accessTokenGG;
    private String subGG;
    private String usernameGG;
    private String emailGG;
    private boolean isDisableGG = false;
    private boolean isAdminGG = false;

    public static AccountGGE converAccountggdtoToAccountgge(AccountGGDTO accountGGDTO) {
        return AccountGGE.builder()
                .accessTokenGG(accountGGDTO.getAccessTokenGG())
                .subGG(accountGGDTO.getSubGG())
                .usernameGG(accountGGDTO.getUsernameGG())
                .emailGG(accountGGDTO.getEmailGG())
                .isDisableGG(accountGGDTO.isDisableGG())
                .isAdminGG(accountGGDTO.isAdminGG())
                .build();
    }
}
