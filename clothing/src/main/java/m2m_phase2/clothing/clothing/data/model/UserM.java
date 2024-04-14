package m2m_phase2.clothing.clothing.data.model;

import lombok.*;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.utils.DateUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserM {
    private Long id;
    private String username;
    private String email;
    private String ggToken;
    private String hashedPassword;
    private boolean isAdmin;
    private boolean isDisable;
    private String fullname;
    private String gender;
    private String avatar;
    private String dob;
    private String description;
    private String jobTitle;
    private int roleId;
    private String roleName;
    private boolean processed;

    public static UserM convertUserEToUserM(UserE userE) {
        return UserM.builder()
                .id(userE.getId())
                .username(userE.getUsername())
                .email(userE.getEmail())
                .ggToken(userE.getGgToken())
                .hashedPassword(userE.getHashedPassword())
                .isAdmin(userE.isAdmin())
                .isDisable(userE.isDisable())
                .fullname(userE.getFullname())
                .gender(userE.getGender())
                .avatar(userE.getAvatar())
                .dob(DateUtils.dateToString(userE.getDob()))
                .description(userE.getDescription())
                .jobTitle(userE.getJobTitle())
                .roleId(userE.getRoleId())
                .roleName(userE.getRoleName())
                .processed(userE.isProcessed())
                .build();
    }

    public static List<UserM> converListUserEToListUserM(List<UserE> listUserE) {
        return  listUserE.stream()
                .map(e -> convertUserEToUserM(e))
                .collect(Collectors.toList());
    }
}
