package m2m_phase2.clothing.clothing.data.dto;

import lombok.Getter;
import lombok.Setter;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Getter
@Setter
public class AccountDto {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean isAdmin;

    public static Account convertAccountDtoToAccount(AccountDto accountDto) {
        return Account.builder()
                .username(accountDto.getUsername())
                .email(accountDto.getEmail())
//                .ggToken(userE.getGgToken())
                .hashedPassword(PasswordEncoderUtil.encodePassword(accountDto.getPassword()))
                .isAdmin(accountDto.isAdmin())
                .isDisable(false)
                .build();
    }
}
