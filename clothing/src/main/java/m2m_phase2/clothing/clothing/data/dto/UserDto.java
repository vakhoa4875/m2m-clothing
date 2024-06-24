package m2m_phase2.clothing.clothing.data.dto;

import lombok.Getter;
import lombok.Setter;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String fullname;
    private String gender;
    private String avatar;
    private String dob;
    private String description;
    private String jobTitle;
    private int roleId;
    private String roleName;
    //M2M- 010 TanLoc Begin
    private String GgToken;
    private String HashedPassword;
    //M2M- 010 TanLoc End
    private String sdt;
    private String address;

    public String getHashedPassword() {
        return PasswordEncoderUtil.encodePassword(this.password);
    }
}
