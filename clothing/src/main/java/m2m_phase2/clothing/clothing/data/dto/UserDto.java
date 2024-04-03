package m2m_phase2.clothing.clothing.data.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String hashedPassword;
    private String fullname;
    private String gender;
    private String avatar;
    private String dob;
    private String description;
    private String jobTitle;
    private int roleId;
    private String roleName;
}
