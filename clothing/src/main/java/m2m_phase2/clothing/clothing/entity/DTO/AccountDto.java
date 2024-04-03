//package m2m_phase2.clothing.clothing.entity.DTO;
//
//import lombok.Data;
//import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
//
//@Data
//public class AccountDto {
//    private String username;
//    private String email;
//    private String password;
//    private String hashedPassword;
//    private Boolean isAdmin;
//    private Boolean isDisable;
//
//    public void setHashedPassword(String password) {
//        this.hashedPassword = PasswordEncoderUtil.encodePassword(password);
//        System.out.println("raw pass: " + password + "\nHashed pass: " + this.hashedPassword);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", hashedPassword='" + hashedPassword + '\'' +
//                ", isAdmin=" + isAdmin +
//                ", isDisable=" + isDisable +
//                '}';
//    }
//
//}
