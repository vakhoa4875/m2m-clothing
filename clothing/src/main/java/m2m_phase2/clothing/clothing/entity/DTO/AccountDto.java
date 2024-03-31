package m2m_phase2.clothing.clothing.entity.DTO;

import lombok.Data;

@Data
public class AccountDto {
    private String username;
    private String email;
    private String hashedPassword;
    private Boolean isAdmin;
    private Boolean isDisable;
}
