package m2m_phase2.clothing.clothing.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import m2m_phase2.clothing.clothing.entity.Account;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountM {
    private int userId;
    @JsonIgnore
    private String username;
    private String email;
    @JsonIgnore
    private String hashedPassword;
    private Boolean isAdmin;
    private Boolean isDisable;

    public static AccountM convertAccountToAccountM(Account account) {
        return AccountM.builder()
                .userId(account.getUserId())
                .username(account.getUsername())
                .email(account.getEmail())
                .hashedPassword(account.getHashedPassword())
                .isAdmin(account.isAdmin())
                .isDisable(account.isDisable())
                .build();
    }

    public static List<AccountM> convertListAccountToListAccountM(List<Account> listAccount) {
        return listAccount.stream()
                .map(e -> convertAccountToAccountM(e))
                .collect(Collectors.toList());
    }
}
