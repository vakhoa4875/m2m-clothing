package m2m_phase2.clothing.clothing.entity.model;

import lombok.*;
import m2m_phase2.clothing.clothing.entity.Account;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserM {

    private int userId;
    private String username;
    private String email;
    private String hashedPassword;
    private Boolean isAdmin;
    private Boolean isDisable;
    //User's info
    private String fullname;
    private String gender;
    private String avatar;
    private Date dob;
    private String description;
    private String jobTitle;


    static UserM convertAccountToAccountM(Account account) {
        return UserM.builder()
                .userId(account.getUserId())
                .username(account.getUsername())
                .email(account.getEmail())
                .hashedPassword(account.getHashedPassword())
                .isAdmin(account.isAdmin())
                .isDisable(account.isDisable())
                .fullname(account.getUserinfo().getFullname())
                .gender(account.getUserinfo().getGender())
                .avatar(account.getUserinfo().getAvatar())
                .dob(account.getUserinfo().getDob())
                .description(account.getUserinfo().getDescription())
                .jobTitle(account.getUserinfo().getJobTitle())
                .build();
    }

    public static List<UserM> convertListAccountToListUserM(List<Account> listAccount) {
        return listAccount.stream()
                .map(UserM::convertAccountToAccountM)
                .collect(Collectors.toList());
    }

}
