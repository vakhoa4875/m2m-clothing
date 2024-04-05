package m2m_phase2.clothing.clothing.data.dto;

import lombok.Builder;
import lombok.Data;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Data
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

    public String getHashedPassword() {
        return PasswordEncoderUtil.encodePassword(this.password);
    }
}
