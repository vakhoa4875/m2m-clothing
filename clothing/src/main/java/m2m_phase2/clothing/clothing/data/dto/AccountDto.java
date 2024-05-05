package m2m_phase2.clothing.clothing.data.dto;

import lombok.Builder;
import lombok.Data;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;

@Data
//@Builder
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
